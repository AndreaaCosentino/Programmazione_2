import java.util.*;

/**
 * OVERVIEW: {@code Sorgente} è un'interfaccia che modella una sorgente che può inviare dei messaggi.
 * Una sorgente è legata ad un canale {@link Canale} e può inviare messaggi a quel canale.
 * */

public interface Sorgente{	
		
	/**
	 * Invia il messaggio al canale a cui la sorgente è legata.
	 * @param messaggio Il messaggio.
	 * @throws IllegalArgumentException Se il messaggio è vuoto.
	 * @throws NullPointerException Se il messaggio è {@code null}.
	 * */
	public void trasmetti(String messaggio);
}