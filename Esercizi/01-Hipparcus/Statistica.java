/**
 * OVERVIEW: {@code Statistica} è un'interfaccia che modella alcuni comportamenti della 
 * statistica descrittiva, ovvero il calcolo della media e della varianza.
 * 
 */

public interface Statistica{
    /**
     * Restituisce il valore della media sulle n osservazioni fatte.
     * @return Il valore della media.
     */
    double media();
    /**
     * Restituisce il valore della varianza sulle n osservazioni fatte.
     * @return Il valore della varianza.
     */
    double varianza();

    //Permette di aggiungere delle osservazioni senza andare a ricostruire una nuova classe
    //Che contiene esattamente tutte le osservazioni precedenti più quella da aggiungere.

    /**
     * Aggiunge un'osservazione a this.
     * Il termine osservazioni va inteso nel senso dato in statistica osservazionale.
     * @param o L'osservazione.
     */
    void aggiungiOsservazione(double o);
}