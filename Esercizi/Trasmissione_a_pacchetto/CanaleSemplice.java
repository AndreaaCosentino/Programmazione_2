import java.util.*;


/**
 * OVERVIEW: {@code CanaleSemplice} è una classe concreta che modella un canale semplice, ovvero un canale che riceve dei pacchetti in ordine e li restituisce in ordine,
 * garantendo che non vengano corrotti.
 * Le istanze di questa classe sono immutabili.
 * */

public class CanaleSemplice implements Canale{
	private final DestinatarioSemplice dest;

	/*
		ABS FUN: AF(dest): un CanaleSemplice è rappresentato dal destinatario a cui inoltra i messaggi.

		IR: dest diverso da null.
	*/


	/**
	 * Costruisce un canale semplice dato il destinatario.
	 * 
	 * @param dest Il destinatario.
	 * @throws NullPointerException Se il destinatario è {@code null}.
	 * */
	public CanaleSemplice(DestinatarioSemplice dest){
		this.dest = Objects.requireNonNull(dest,"Il destinatario non può essere null");
	}

	@Override
	public Parametri parametri(){
		return dest.parametri();
	}

	@Override
	public boolean inoltra(Pacchetto pacchetto){
		Objects.requireNonNull(pacchetto,"Il pacchetto non può essere null");
		return dest.ricevi(pacchetto);
	}
}