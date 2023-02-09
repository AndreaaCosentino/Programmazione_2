import java.util.*;

/**
 * OVERVIEW: {@code Peso} è una classe concreta che modella il peso atomico.
 * Il peso è espresso in Dalton. 
 * Le istanze di questa classe sono immutabili.
 */
public class Peso {
    private final float peso;

    /*
     * ABS FUN: AF(peso): un'istanze della classe Peso è rappresentato dal peso.
     * 
     * IR: il peso deve essere positivo. 
     */

    /**
     * Costruisce un Peso a partire dal peso dato.
     * @param peso Il peso. 
     * @throws IllegalArgumentException Se il peso è non positivo.
     */
    public Peso(float peso){
        if(peso <= 0) throw new IllegalArgumentException("Il peso deve essere positivo");
        this.peso = peso;
    }

    @Override
    public String toString() {
        return Float.valueOf(peso).toString();
    }

    /**
     * Somma this con il peso dato.
     * @param c Il peso.
     * @return Il risultato della somma di this con il peso dato. 
     * @throws NullPointerException Se il peso dato è {@code null}.
     */
    public Peso sommaPesi(Peso c){
        Objects.requireNonNull(c,"Il peso dato non può essere null");
        return new Peso(this.peso+c.peso);
    }

    /**
     * Restituisce il peso di this moltiplicato per il numero di volte dato. 
     * @param i Il numero di volte. 
     * @return Il peso di this moltiplicato per il numero di volte dato. 
     * @throws IllegalArgumentException Se il numero di volte dato è non positivo.
     */
    public Peso moltiplicaPeso(int i){
        if(i <= 0) throw new IllegalArgumentException("Il numero di volte per cui moltiplicare il peso deve essere positivo");
        return new Peso(this.peso*i);
    }



}
