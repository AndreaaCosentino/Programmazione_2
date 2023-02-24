/**
 * OVERVIEW: {@code TrasmissioneSemplice} è una classe concreta che estende {@code Trasmissione} e modella una trasmissione di tipo semplice.
 * Una trasmissione semplice è una trasmissione a cui corrispone una sola fascia betaoraria.
 * */

import java.util.*;

public class TrasmissioneSemplice extends Trasmissione{
	private final Fascia fascia;

	/*
	  ABS FUN: AF(fascia,super.titolo) = una TrasmissioneSemplice è rappresentata dal tiolo e dalla fascia in cui viene trasmessa.
	  
	  IR:  fascia != null.
	 */

	/**
	 * Costruisce una Trasmissione semplice data la Fascia ed il titolo.
	 * @param fascia La fascia.
	 * @param titolo Il titolo.
	 * @throws IllegalArgumentException Se il titolo è vuoto.
	 * @throws NullPointerException Se la fascia od il titolo sono {@code null}.
	 * */
	public TrasmissioneSemplice(Fascia fascia, String titolo){
		super(titolo);
		this.fascia = Objects.requireNonNull(fascia);
	}

	@Override
	public String toString(){
		return fascia.getInizio().toString() + " -  "+ super.getTitolo(); 
	}

	@Override
	public Iterator<Fascia> iterator(){
		return new Iterator<Fascia>(){
			private boolean hasnext = true;

			@Override
			public boolean hasNext(){
				return hasnext;
			}

			public Fascia next(){
				if(!hasNext()) throw new NoSuchElementException("L'iteratore non ha altri elementi su cui iterare");
				hasnext = false;
				return fascia;
			}
		};
	}

}