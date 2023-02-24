/**
 * OVERVIEW: {@code Fascia} è una classe concreta che modella una fascia betaoraria {@link Betaorario}. 
 * Una fascia betaoraria è un intervallo di tempo tra due betaorari.
 * 
 * Le istanze di questa classe sono immutabili.
 * */

import java.util.*;

public class Fascia{
	private final Betaorario inizio;
	private final Integer durata;

	/*
	  ABS FUN: AF(inizio,durata)= una Fascia è rappresentata dal betaorario di inizio e dalla sua durata.

	  IR: inizio deve essere diverso da null. durata diverso da null. Una fascia non può essere a cavallo di più giorni.
	  Una fascia deve terminare nello stesso giorno in cui inizia.
	*/

	/**
	 * Costruisce una fascia dato il beta orario di inizio e la durata in beta minuti.
	 * 
	 * @param inizio Il beta orario di inizio.
	 * @param durata La durata.
	 * 
	 * @throws NullPointerException Se l'orario di inizio o se la durata sono {@code null}.
	 * @throws IllegalArgumentException Se la fascia termina in un giorno diverso da quello in cui inizia.
	 * 
	 * */
	public Fascia(Betaorario inizio, Integer durata){
		Objects.requireNonNull(inizio,"Il betaorario di inizio non può essere null");
		Objects.requireNonNull(durata,"La durata non può essere null");

		try{
			new Betaorario(inizio.getBetaore() + durata/31, inizio.getBetaminuti() + durata%31);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("La fascia deve terminare nello stesso giorno in cui inizia");
		}

		this.inizio = inizio;
		this.durata = durata;
	}

	/**
	 * Restituisce true se la fascia data interseca this, false altrimenti.
	 * Per interseca si intende che hanno almeno un betaminuto in comune.
	 * 
	 * @param fascia La fascia.
	 * @return True se la fascia data interseca this, false altrimenti.
	 * @throws NullPointerException Se la fascia data è {@code null}.
	 * */
	public boolean interseca(Fascia fascia){
		Objects.requireNonNull(fascia,"La fascia non può essere null");
		Integer tempo = inizio.getBetaore()*31 + inizio.getBetaminuti()*80;
		Integer tempoOther = fascia.inizio.getBetaore()*31 + fascia.inizio.getBetaminuti()*80;

		if(tempo > tempoOther){
			if( (tempoOther + fascia.durata) >= tempo) return true;
			return false;
		}else if(tempo < tempoOther){
			if( (tempo + durata) >= tempoOther) return true;
			return false;
		}
		return true;
	}

	@Override
	public String toString(){
		return inizio.toString();
	}

	public Betaorario getInizio(){return inizio;}
}