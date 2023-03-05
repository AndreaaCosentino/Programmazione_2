import java.util.*;

/**
 * OVERVIEW: {@code UnaPassata} è una classe concreta che implementa i comportamenti di
 * {@link Statistica} e calcola la media e la varianza in una passata.
 * Per una passata si intende che la media e la varianza vengono calcolata andando a consultare le 
 * osservazioni una sola volta.
 * Il termine osservazioni va inteso nel senso dato in statistica osservazionale.
 * Le istanze di questa classe sono mutabili.
 */
public class UnaPassata implements Statistica{
    private double somma  = 0;
    private double sommaQuadrata = 0;
    private int numeroOsservazioni;

    /*
     * ABS FUN: AF(media(),varianza()) = la classe è rappresentata dalla media e dalla varianza.
     * IR : somma deve essere uguale alla somma delle osservazioni e sommaQuadrata alla somma dei
     * quadarti delle osservazioni (ne consegue che sommaQuadrata è sempre >= 0). n deve essere maggiore di 1.
     */

    /**
     * Costruisce un oggetto UnaPassata data una lista di osservazioni.
     * Il termine osservazioni va inteso nel senso dato in statistica osservazionale.
     * @param osservazioni La lista.
     * @throws NullPointerException Se la lista è {@code null} o 
     * se contiene elementi {@code null}.
     * @throws IllegalArgumentException Se lista è vuota o contiene solo un elemento.
     */
    public UnaPassata(List<Double> osservazioni){
        Objects.requireNonNull(osservazioni,"La lista non può essere nulla");
        if(osservazioni.size() == 0) throw new IllegalArgumentException("La lista non può essere vuota");
        numeroOsservazioni = osservazioni.size();
        for(Double o: osservazioni){
            Objects.requireNonNull(o,"La lista non può contenere osservazioni null");
            somma += o;
            sommaQuadrata += o*o;
        }
    }

    @Override
    public double media() {
        return somma/numeroOsservazioni;
    }

    @Override
    public double varianza() {
        return ((numeroOsservazioni*(sommaQuadrata)-somma*somma)/(numeroOsservazioni*(numeroOsservazioni-1)));
    }

    public double getSomma(){return this.somma;}
    public double getSommaQuadarta(){return this.sommaQuadrata;}
    public int getNumeroOsservazioni(){return numeroOsservazioni;}

    @Override
    public String toString(){
        return "Numero osservazioni: " + numeroOsservazioni + ", media: " + media() + " , varianza : " + varianza();
    }

    @Override
    public void aggiungiOsservazione(double o) {
        numeroOsservazioni++;
        somma += o;
        sommaQuadrata += o*o;
    }
}
