
/**
 * OVERVIEW: {@code Molecola} è un'interfaccia che descrive i comportamenti di una molecola.
 * Questa deve poter esibire la propria formula ed il proprio peso. 
 * Una formula è definita come la sequenza (come definita da Hill) dei simboli degli atomi che la costituiscono seguiti 
 * dal numero di atomi di quell'elemento presenti nella molecola.
 * 
 * E.g. Un esempio di formula è : C55H72N4O5Mg. 
 * 
 */

public interface Molecola {

    /**
     * Restituisce la formula di this.
     * @return La formula.
     */
    String formula();


    /**
     * Restituisce il peso di this.
     * @return Il peso.
     */
    Peso peso();

    /**
     * Aggrega this alla molecola data.
     * @param other La molecola.
     * @return La molecola risultante dall'aggregazione. 
     * @throws NullPointerException Se la molecola data è {@code null}.
     */
    Molecola aggregaMolecola(Molecola other);
}
