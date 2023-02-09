import java.util.*;

/**
 * OVERVIEW: {@code TavolaPeriodica} è una classe concreta che modella una tavola periodica, ovvero una tavola che contiene gli elementi chimici. 
 * La tavola periodica permette di costruire delle molecole a paritre dagli elementi chimici che contiene. 
 * Le istanze di questa classe sono immutabili. 
 * 
 * 
 */

public class TavolaPeriodica {
    
    private final List<Elemento> elem = new ArrayList<>();

    /*
     * ABS FUN : AF(elem) = Una tavola periodica è rappresentata dagli elementi che contiene. L'ordine in cui sono inseriti gli elementi nella lista è
     *                      caratterizzante.
     * 
     * IR: elem != null ed ogni elemento presente in elem deve essere diverso da null.
     * 
     */

    /**
     * Costruisce una tavola periodica a partire da una lista data di elementi.
     * @param elementi La lista.
     * @throws NullPointerException Se la lista è {@code null} o se contiene elementi {@null}.
     */
    public TavolaPeriodica(List<Elemento> elementi){
        Objects.requireNonNull(elementi,"La lista non può essere null");
        for(Elemento e: elementi){
            Objects.requireNonNull(e,"La lista non può conteneere elementi null");
            elem.add(e);
        }
    }


    // Se la formula data contiene elementi che non esistono allora lancio un'eccezione unchecked. 
    // Mi aspetto che chi utilizzi il metodo conosca gli elementi chimici e non inserisca elementi inesistenti.

    /**
     * Restituisce la molecola corrispondente alla formula data. 
     * @param formula La formula.
     * @return La molecola corrispondente alla formula data. 
     * @throws NullPointerException Se la formula data è null.
     * @throws IllegalArgumentException Se la formula data contiene degli elementi che non esistono.
     */
    public Molecola costruisciMolecola(String formula){
        Objects.requireNonNull(formula,"La formula non può essere null");
        String[] f = Helpers.parseFormula(formula);
        ArrayList<Elemento> el = new ArrayList<>();
        ArrayList<Integer> molteplicità = new ArrayList<>();
        for(int i = 0; i < f.length; ){
            Elemento e = trova(f[i]);
            el.add(e);
            molteplicità.add(Integer.parseInt(f[i+1]));
            i += 2;
        }

        if(el.size() == 1){
            return new MolecolaSemplice(el.get(0),molteplicità.get(0));
        }else{
            return new MolecolaComposta(el, molteplicità);
        }
    }

    /**
     * Restituisce l'elemento con il dato simbolo.
     * @param simbolo Il simbolo.
     * @return l'elemento con il dato simbolo. 
     * @throws NullPointerException Se il simbolo dato è {@code null}.
     * @throws NoSuchElementException Se il simbolo dato non corrisponde ad alcun elemento.
     */
    public Elemento trova(String simbolo){
        Objects.requireNonNull(simbolo,"Il simbolo non può essere null");
        for(Elemento e : elem){
            if(e.getSimbolo().equals(simbolo)){
                return e;
            }
        }
        throw new NoSuchElementException("Non esiste nessun elemento con simbolo " + simbolo);
    }

    /**
     * Restituisce vero se l'elemento dato è presente, falso altrimenti.
     * @param e L'elemento.
     * @return Restituisce vero se l'elemento dato è presente, falso altrimenti.
     * @throws NullPointerException Se l'elemento dato è {@code null}.
     */
    public boolean èPresente(Elemento e){
       trova(e.getSimbolo());
       return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Tavola periodica:\n");
        for(Elemento e: elem){
            sb.append(e+"\n");
        }
        return sb.toString();
    }
}
