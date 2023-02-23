import java.util.*;

/**
 * OVERVIEW: {@code SorgenteSemplice} è una classe concreta che modella una sorgente semplice, ovvero una sorgente (quindi implementa i comportamenti di {@link Sorgente})
 * e invia i pacchetti in ordine di sequenza.
 * Le istanze di questa classe sono immutabili.
 * */

public class SorgenteSemplice implements Sorgente{
	private final CanaleSemplice canale;

	/*
		ABS FUN: AF(canale) = è rappresentato dal canale a cui si riferisce.
		IR: canale diverso da null
	*/

	/**
	 * Costruisce una sorgente semplice a partire da un canale dato.
	 * @param canale Il canale.
	 * @throws NullPointerException Se il canale è {@code null}.
	 * */
	public SorgenteSemplice(CanaleSemplice canale){
		this.canale = Objects.requireNonNull(canale,"Il canale non può essere null");
	}

	@Override
	public void trasmetti(String messaggio){
		Byte[] temp = Encoder.codifica(messaggio,canale.parametri().getCharset());
		int j = 1;
		for(int i = 0; i < temp.length; i += canale.parametri().getDim()){
			canale.inoltra(new Pacchetto(j,0,Arrays.copyOfRange(temp,i,i+canale.parametri().getDim())));
			j++;
		}
	}	
}