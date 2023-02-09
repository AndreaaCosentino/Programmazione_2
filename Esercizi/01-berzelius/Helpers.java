import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe di utilità contenente alcuni metodi per la validazione ed il parsing
 * di elementi, formule ed equazioni chimiche.
 */
public class Helpers {
  
  private Helpers() {} // impedisce di istanziare questa classe, che ha solo metodi statici di utilità

  // Alcune espressioni regolari utili al processo di validazione e parsing
  private static final Pattern ELEMENT_PATTERN = Pattern.compile("\\p{Upper}\\p{Lower}*");
  private static final Pattern ELEMENT_NUM_PATTERN = Pattern.compile("\\s*(?<symbol>\\p{Upper}\\p{Lower}*)\\s*(?<num>\\d+)?\\s*");
  private static final Pattern SC_FORMULA_PATTERN = Pattern.compile("\\s*(?<sc>\\d+)?\\s*(?<formula>\\p{Upper}(\\p{Lower}|\\p{Upper}|\\d|\\s)*)\\+?");

  /**
   * Consente di stabilire se una stringa può denotare un elemento chimico,
   * ovvero se inizia con una lettera maiuscola e prosegue eventualmente con
   * altre lettere minuscole (senza contenere spazi, o altri caratteri).
   *
   * @param simbolo la stringa da valutare.
   * @return {@code true} se e solo se la stringa ha il formato corretto.
   * @throws NullPointerException se la stringa è {@code null}.
   */
  public static boolean elementoValido(final String simbolo) {
    return ELEMENT_PATTERN.matcher(Objects.requireNonNull(simbolo)).matches();
  }

  /**
   * Consente di ottenere un array di stringhe che rappresentano, a coppie, i
   * simboli e le relative numerosità (come stringhe corripsondenti a numeri
   * interi) in una formula chimica.
   *
   * <p> Ad esempio, sse invocata sulla stringa <samp>"H2O"</samp> restituisce 
   * l'array <samp>["H", "2", "O", "1"]</samp> (si noti la stringa <samp>"1"</samp> 
   * presente nell'array anche se omesso nella formula.
   *
   * @param formula la formula.
   * @return l'array di stringhe (di lunghezza pari, contenenti a coppie simboli
   * e numeri).
   * @throws NullPointerException se la formula è {@code null}.
   * @throws IllegalArgumentException se la formula non è nel formato corretto.
   */
  public static String[] parseFormula(final String formula) {
    final List<String> result = new ArrayList<>();
    ConsecutiveMatcher it = consecutiveFinder(ELEMENT_NUM_PATTERN, Objects.requireNonNull(formula));
    while (it.hasNext()) {
      final Matcher m = it.next();
      result.add(m.group("symbol"));
      final String num = m.group("num");
      if (num != null) try {
        Integer.parseInt(num);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Impossibile convertire ad intero il numero " + num);
      }
      result.add(num == null ? "1" : num);
    }
    it.completed();
    return result.toArray(new String[0]);
  }

  /**
   * Consente di ottenere un array di stringhe che rappresentano, a coppie, i
   * coefficienti stechiometrici (come stringhe corripsondenti a numeri interi)
   * e le formule nella parte di reagenti o prodotti di una equazione chimica.
   *
   * <p> Ad esempio, sse invocata sulla stringa <samp>"2 H2O + NaCl + 3O"</samp> 
   * restituisce l'array <samp>["2", "H2O", "1", "NaCl", "3", "O"]</samp> 
   * (si noti la stringa <samp>"1"</samp> presente nell'array anche se omesso 
   * nell'elenco di reagenti, o prodotti).
   *
   * @param somma la somma.
   * @return l'array di stringhe (di lunghezza pari, contenenti a coppie
   * coefficienti e formule). e numeri).
   * @throws NullPointerException se la somma è {@code null}.
   * @throws IllegalArgumentException se la somma non è nel formato corretto.
   */
  public static String[] parseSommaStechiometrica(final String somma) {
    final List<String> result = new ArrayList<>();
    ConsecutiveMatcher it = consecutiveFinder(SC_FORMULA_PATTERN, Objects.requireNonNull(somma));
    while (it.hasNext()) {
      final Matcher m = it.next();
      final String sc = m.group("sc");
      if (sc != null) try {
        Integer.parseInt(sc);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Impossibile convertire ad intero il numero " + sc);
      }
      result.add(sc == null ? "1" : sc);
      result.add(m.group("formula").trim());
    }
    it.completed();
    return result.toArray(new String[0]);
  }

  // L'interfaccia ed il metodo seguenti sono ad uso interno delle precedenti
  // funzioni e non è necessario comprenderne il funzionamento, o invocarle
  // direttamente.

  private interface ConsecutiveMatcher extends Iterator<Matcher> {
    public boolean completed();
  }

  private static ConsecutiveMatcher consecutiveFinder(final Pattern pattern, final String source) {
    return new ConsecutiveMatcher() {
      private final Matcher m = Objects.requireNonNull(pattern).matcher(Objects.requireNonNull(source));
      private Matcher last = null;
      private int lastEnd = 0;
      @Override
      public boolean hasNext() {
        if (last != null) return true;
        if (!m.find()) return false;
        if (m.start() != lastEnd) throw new IllegalArgumentException("La stringa contiene una parte che non corrisponde al formato cercato a partire da \"" + source.substring(lastEnd) + "\"");
        lastEnd = m.end();
        last = m;
        return true;
      }
      @Override
      public Matcher next() {
        if (!hasNext()) throw new NoSuchElementException();  
        final Matcher ret = last;
        last = null;
        return ret;
      }
      @Override
      public boolean completed() {
        if (hasNext()) return false;
        if (lastEnd != source.length()) throw new IllegalArgumentException("La stringa contiene una parte che non corrisponde al formato cercato a partire da \"" + source.substring(lastEnd) + "\"");
        return true;
      }
    };
  }

}

