import java.util.*;

/**
 * OVERVIEW: {@code MolecolaComposta} è una classe concreta che implementa il comportamento descritto dall'interfaccia {@link Molecola} e che modella 
 * una molecola composta, ovvero una molecola composta da diversi elementi.
 * Le istanze di questa classe sono immutabili.
 */

public class MolecolaComposta implements Molecola{
    private final List<MolecolaSemplice> molecole;
    private Peso peso;

    /*
     * ABS FUN: AF(molecole,peso,molteplicità) = Una molecola composta è rappresentata dalla sua formula, come specificato (ho specificato cos'è una formula) 
     *                          nell'Overview dell'interfaccia Molecola.
     * IR: molecole deve essere diverso da null e così anche ogni elemento che contiene. Stessa cosa per molteplicità. peso deve essere diverso da null.
     */

    /**
     * Costruisce una molecola composta a partire da una lista data di elementi e da una lista di molteplicità. 
     * Le liste sono parallele.
     * @param el La lista degli elementi. 
     * @param n La lista delle molteplicità.
     * @throws NullPointerException Se uno dei parametri dati è {@code null} o se uno degli elementi che contengono le liste date è {@code null}. 
     * @throws IllegalArgumentException Se le liste date hanno una dimensione diversa o se hanno dimensione 0.
     */
    public MolecolaComposta(List<Elemento> el, List<Integer> n ){
        Objects.requireNonNull(el,"La lista di elementi non può essere null");
        Objects.requireNonNull(n,"La lista delle molteplicità non può essere null");
        if(el.size() != n.size() ) throw new IllegalArgumentException("Le liste date devono avere la stessa dimensione");
        if(el.size() == 0) throw new IllegalArgumentException("Le liste date devono avere dimensione  positiva");
        molecole = new ArrayList<>();
        for(int i = 0; i < el.size(); i++){
            MolecolaSemplice temp = new MolecolaSemplice(el.get(i),n.get(i));
            molecole.add(temp);
            if(peso == null){
                peso = temp.peso();
            }else{peso = peso.sommaPesi(temp.peso());}
        }
    }
    //Package-private , non voglio esporre la rappresentazione.

    /**
     * Costruisce una molecola composta a partire da una lista data di molecole semplici.
     * @param el La lista di molecole semplici. 
     * @throws NullPointerException Se la lista o uno dei suoi elementi è null.
     */
    MolecolaComposta(List<MolecolaSemplice> el){
        Objects.requireNonNull(el,"La lista data non può essere null");
        this.molecole = new ArrayList<>(el);
        for(MolecolaSemplice e: el){
            Objects.requireNonNull(e,"Gli elementi della lista non possono essere null");
            if(peso == null){
                peso = e.peso();
            }else{peso = peso.sommaPesi(e.peso());}
        }
    }

    @Override
    public String formula() {
        HashMap<String,MolecolaSemplice> s = new HashMap<>();

        List<String> simboli = new ArrayList<>();

        for(MolecolaSemplice m : molecole){
            simboli.add(m.getElemento().getSimbolo());
        }

        Collections.sort(simboli);

        StringBuilder sb = new StringBuilder();
        if(s.get("C") != null){
            sb.append(s.get("C").formula());
        }

        if(s.get("H") != null){
            sb.append(s.get("H").formula());
        }

        if(s.get("N") != null){
            sb.append(s.get("N").formula());
        }

        if(s.get("O") != null){
            sb.append(s.get("O").formula());
        }

        for(String temp : simboli){
            if(!(temp.equals("H") || temp.equals("C") || temp.equals("N") || temp.equals("O"))){
                sb.append(s.get(temp).formula());
            }
        }

        return null;
    }

    @Override
    public Peso peso() {
       return this.peso;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    @Override
    public Molecola aggregaMolecola(Molecola other) {
        Objects.requireNonNull(other,"La molecola data non può essere null");
        ArrayList<MolecolaSemplice> tempMolecole = new ArrayList<>(molecole);
        if(other instanceof MolecolaSemplice){
            MolecolaSemplice e = (MolecolaSemplice) other;
            int i = tempMolecole.indexOf(e);
            if(i == -1){
                tempMolecole.add(e);
            }else{
                tempMolecole.set(i,(MolecolaSemplice)tempMolecole.get(i).aggregaMolecola(e));
            }
            return new MolecolaComposta(tempMolecole);
        }else{
            MolecolaComposta e = (MolecolaComposta) other;
            for(MolecolaSemplice m :  e.molecole){
                int i = tempMolecole.indexOf(m);
                if(i == -1){
                    tempMolecole.add(m);
                }else{
                 tempMolecole.set(i,(MolecolaSemplice)tempMolecole.get(i).aggregaMolecola(m));
                }
            }
            return new MolecolaComposta(tempMolecole);
        }

    }

}
