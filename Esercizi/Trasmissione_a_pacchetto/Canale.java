import java.util.*;

/**
 * OVERVIEW: {@code Canale} è un'interfaccia che modella il comportamento di un canale, ovvero di inoltro messaggi ad una destinazione.
 * Un canale si riferisce sempre ad un destiantario.
 * */

public interface Canale{

	public Parametri parametri();
	/**
	 * Invia il pacchetto dato al destinatario a cui si riferisce il canale.
	 * Se il pacchetto dato è {@code null} stampa il messaggio contenuto nei pacchetti inviate precedentemente.
	 * @param pacchetto Il pacchetto.
	 * @return True se invia il pacchetto correttamente, ovvero se il pacchetto non è stato corrotto nell'invio, altrimenti false.
	 * @throws NullPointerException Se il pacchetto è {@code null}.
	 * */
	public boolean inoltra(Pacchetto pacchetto);
}