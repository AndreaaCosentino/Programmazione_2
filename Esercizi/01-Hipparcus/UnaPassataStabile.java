import java.util.*;

/**
 * OVERVIEW: {@code UnaPassataStabile} è una classe concreta che implementa i comportamenti di
 * {@link Statistica} e calcola la media e la varianza in una passata, assicurando la stabilità numerica.
 * Per una passata si intende che la media e la varianza vengono calcolata andando a consultare le 
 * osservazioni una sola volta.
 * Per stabilità numerica si intende il calcolo della media e della varianza prendendo in considerazione
 * la riformulazione del calcolo dovuta a Welford.
 * Il termine osservazioni va inteso nel senso dato in statistica osservazionale.
 * Le istanze di questa classe sono mutabili.
 */
public class UnaPassataStabile extends StatisticaAbs{

    /**
     * Costruisce un oggetto UnaPassataStabile data una lista di osservazioni.
     * Il termine osservazioni va inteso nel senso dato in statistica osservazionale.
     * @param osservazioni La lista.
     * @throws NullPointerException Se la lista è {@code null} o 
     * se contiene elementi {@code null}.
     * @throws IllegalArgumentException Se lista è vuota o contiene solo un elemento.
     */
    public UnaPassataStabile(List<Double> osservazioni) {
        super(osservazioni);
    }
    /**
     * Restituisce la media i-esima, data la media i-1-esima, l'indice corrente e l'osservazione corrente.
     * @param tempMedia La media delle prime i-1 osservazioni;
     * @param i L'indice.
     * @param osservazioneCorrente L'osservazione.
     * @return La media i-esima.
     * @throws IllegalArgumentException Se l'indice è non positivo.
     */
    private double aggiornaMedia(double tempMedia, int i, double osservazioneCorrente){
        if(i < 1) throw new IllegalArgumentException("L'indice deve essere positivo");
        return tempMedia + (osservazioneCorrente-tempMedia)/i;
    }
    @Override
    public double media() {
        double tempMedia = 0;
        int i = 0;
        for(Double o : this){
            i++;
            tempMedia = aggiornaMedia(tempMedia, i, o);
        }
        return tempMedia;
    }

    @Override
    public double varianza() {
        double tempVarianza = 0;
        double tempMedia = 0;
        int i = 0;
        for(Double o : this){
            i++;
            Double tempMediaAttuale = aggiornaMedia(tempMedia, i, o);
            tempVarianza = tempVarianza + (o-tempMediaAttuale) * (o - tempMedia);
            tempMedia = tempMediaAttuale;
        }
        return tempVarianza/(super.numeroOsservazioni()-1);
    }
    
}
