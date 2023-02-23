import java.util.*;

/**
 * OVERVIEW: {@code Destinatario} è un interfaccia che modella il comportamento di un destinatario.
 * Un destinatario può ricevere dei messaggi.
 **/

public interface Destinatario{

	
	Parametri parametri();
	/**
	 * Riceve il pacchetto dato aggiungendolo a quelli già ricevuti.
	 * Se il pacchetto dato è {@code null} stampa il messaggio contenuto nei pacchetti ricevuti e cancella le informazioni sui pacchetti ricevuti.
	 * @param pacchetto Il pacchetto.
	 * @return True se riceve il pacchetto correttamente, ovvero se il pacchetto non è stato corrotto nell'invio, altrimenti false.
	 * */
	boolean ricevi(Pacchetto pacchetto);
}