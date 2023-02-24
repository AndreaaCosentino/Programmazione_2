/**
 * OVERVIEW: {@code Betaorario} è una classe concreta che modella un betaorario. 
 * Un betaorario è un orario sul pianeta Betazed. Sul pianea Betazed i giorni durano 31 ore (dette betaore)
 * ed ogni ora è scandita da 80 minuti (detti betaminuti).
 * 
 * La classe permette di confrontare degli orari tra di loro. Un orario è maggiore di un altro
 * se è maggiore in betaore o, nel caso le betaore fossero uguali, è maggiore in betaminuti.
 * 
 * E.g. Se abbiamo un orario alpha 17:81 e un altro orario beta 19:76, beta è maggiore di alpha.
 * 
 * 
 * Le istanze di questa classe sono immutabili.
 * */

import java.util.*;

public class Betaorario implements Comparable<Betaorario>{
	private final Integer betaminuti;
	private final Integer betaore;

	/*
		ABS FUN: AF(betaminuti,betaore) = un betaorario è rappresentato dalle betaore e dai betaminuti.
										  Può essere scritto nella forma betaore:betaminuti.
		IR: betaminuti e betaore devono essere diversi da null. betaore deve essere compreso tra 0 e 30 inlcusi, e betaminuti compreso tra 0 e 79 inclusi.
	*/

	/**
	 * Costruisce un Betaorario date le betaore e i betaminuti.
	 * 
	 * @param betaore Le betaore.
	 * @param betaminuti I Betaminuti.
	 * 
	 * @throws NullPointerException Se le betaore o i betaminuti sono {@code null}.
	 * @throws IllegalArgumentException Se le betaore non sono nel range 0-30 (estremi inclusi) o se i betaminuti non sono nel range
	 * 0-79 (estremi inclusi).
	 * */
	public Betaorario(Integer betaore, Integer betaminuti){
		Objects.requireNonNull(betaminuti,"I betaminuti non possono essere null");
		Objects.requireNonNull(betaore,"Le betaore non possono essere null");

		if(betaore < 0 || betaore > 30) throw new IllegalArgumentException("Le betaore devono essere comprese tra 0 e 30 inclusi");
		if(betaminuti < 0 || betaminuti > 79) throw new IllegalArgumentException("I betaminuti devono essere compresi tra 0 e 79 inclusi");
		this.betaminuti = betaminuti;
		this.betaore = betaore;
	}	

	/**
	 * Crea un betaorario dati i betaminuti.
	 * 
	 * @param betaminuti I betaminuti.
	 * 
	 * @throws NullPointerException Se i betaminuti sono {@code null}.
	 * @throws IllegalArgumentException Se i betaminuti sono minori di 0 o se sono maggiori od uguali di 31*80
	 * */
	public Betaorario(Integer betaminuti){
		Objects.requireNonNull(betaminuti,"I betaminuti non possono essere null");
		if(betaminuti < 0  || betaminuti >= 31*80) throw new IllegalArgumentException("I betaminuti non possono essere negativi o maggiori della durata di un betagiorno");
		this.betaore = betaminuti/80;
		this.betaminuti = betaminuti%80;
	}

	@Override
	public int compareTo(Betaorario o){
		Objects.requireNonNull(o,"Il betaorario non può essere null");
		if(o.betaore < this.betaore) return 1;
		if(o.betaore > this.betaore) return -1;
		if(o.betaminuti < this.betaminuti) return 1;
		if(o.betaminuti > this.betaminuti) return -1;
		return 0;
	}

	public Integer getBetaore(){return betaore;}
	public Integer getBetaminuti(){return betaminuti;}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		if(betaore < 10) sb.append("0");
		sb.append(betaore + ":");
		if(betaminuti < 10) sb.append("0");
		sb.append(betaminuti);
		return sb.toString();
	}
}