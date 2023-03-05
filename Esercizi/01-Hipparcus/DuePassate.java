import java.util.*;

/**
 * OVERVIEW: {@code DuePassate} è una classe concreta che estende la classe {@code StatisticaAbs}
 * e modella il calcolo della media e della varianza in "due passate".
 * Per due passata si intende che la media e la varianza vengono calcolata andando a consultare le 
 * osservazioni due volte.
 * Il termine osservazioni va inteso nel senso dato in statistica osservazionale.
 * Le istanze di questa classe sono mutabili.
 */

public class DuePassate extends StatisticaAbs{

    /*
     * ABS FUN: AF(media(),varianza()) = la classe è rappresentata dalla media e dalla varianza.
     * IR: // 
     */

     /**
     * Costruisce un oggetto DuePassate data una lista di osservazioni.
     * Il termine osservazioni va inteso nel senso dato in statistica osservazionale.
     * @param osservazioni La lista.
     * @throws NullPointerException Se la lista è {@code null} o 
     * se contiene elementi {@code null}.
     * @throws IllegalArgumentException Se lista è vuota o contiene solo un elemento.
     */
    public DuePassate(List<Double> osservazioni) {
        super(osservazioni);
    }

    @Override
    public double media() {
        double result = 0;
        int i = 0;
        for(Double o : this){
            result += o;
            i++;
        }
        return result/i;
    }

    @Override
    public double varianza() {
        double media = media();
        int i = 0;
        double somma = 0;
        for(Double o : this){
            somma += (o -media)*(o-media);
            i++;
        }
        return somma/(i-1);
    }
    
}
