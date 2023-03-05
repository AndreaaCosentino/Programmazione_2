/**
 * OVERVIEW: {@code StatisticaAbs} è una classe astratta che implementa il comportamento 
 * descritto da {@link Statistica}. 
 * {@code StatisticaAbs} supporta il calcolo della media e della varianza o tramite il metodo
 * a "due passate" o tramite il metodo "induttivo".
 * 
 * Implementa l'interfaccia {@link Iterable}.
 * Le istanze di questa classe sono mutabili.
 */
import java.util.*;

public abstract class StatisticaAbs implements Statistica, Iterable<Double>{
    private final List<Double> osservazioni;

    /*
     * IR: osservazioni != null e gli elementi contenuti in osservazioni != null e non può essere vuota 
     * e deve contenere più di 1 elemento.
     */

    /**
     * Costruisce un oggetto StatisticaAbs data una lista di osservazioni.
     * Il termine osservazioni va inteso nel senso dato in statistica osservazionale.
     * @param osservazioni La lista.
     * @throws NullPointerException Se la lista è {@code null} o 
     * se contiene elementi {@code null}.
     * @throws IllegalArgumentException Se lista è vuota o contiene solo un elemento.
     */
    public StatisticaAbs(List<Double> osservazioni){
        Objects.requireNonNull(osservazioni,"La lista delle osservazioni non può essere null");
        if(osservazioni.size() < 1) throw new IllegalArgumentException("La lista deve contenere più di un elemento");
        this.osservazioni = new ArrayList<>();
        for(Double d: osservazioni){
            Objects.requireNonNull(d,"La lista non può contenere osservazioni null");
            this.osservazioni.add(d);
        }
    }

    public Iterator<Double> iterator(){
        return Collections.unmodifiableList(osservazioni).iterator();
    }

    @Override
    abstract public double media();

    @Override
    abstract public double varianza();
    
    /**
     * Restituisce il numero di osservazioni prese in considerazione da this.
     * @return Il numero di osservazioni prese in considerazione da this.
     */
    public int numeroOsservazioni(){return osservazioni.size();}

    @Override
    public String toString(){
        return "Numero osservazioni: "+ osservazioni.size() + ", media: " + media() + " , varianza : " + varianza();
    }
    @Override
    public void aggiungiOsservazione(double o){
        osservazioni.add(o);
    }
}
