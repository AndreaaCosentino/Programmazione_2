/**
 * OVERVIEW: {@code Trasmissione} è una classe astratta che modella una trasmissione.
 * Una trasmissione è caratterizzata da un titolo e dalle fasce betaorarie in cui viene trasmessa.
 * */

 import java.util.*;

public abstract class Trasmissione implements Iterable<Fascia>{
	private final String titolo;

	// IR: titolo non può essere null né vuoto.


	/**
	 * Costruisce una trasmissione a partire dal titolo dato.
	 * 
	 * @param titolo Il titolo.
	 * @throws NullPointerException Se il titolo è {@code null}.
	 * @throws IllegalArgumentException Se il titolo è vuoto.
	 * */
	public Trasmissione(String titolo){
		Objects.requireNonNull(titolo,"Il titolo non può essere null");
		if(titolo.length() == 0) throw new IllegalArgumentException("Il titolo non può essere vuoto");
		this.titolo = titolo;
	}
	/**
	 * Restituisce true se la trasmissione data si interseca con this, false altrimenti.
	 * Per interseca si intende che una delle fasce betaorarie di this si interseca con una delle fasce della trasmissione data.
	 * 
	 * @param t La trasmissione.
	 * @return True se la trasmissione data si interseca con this, false altrimenti.
	 * @throws NullPointerException Se la trasmissione data è {@code null}.
	 * */
	public boolean interseca(Trasmissione t){
		Objects.requireNonNull(t,"La trasmissione non può essere null");

		for(Fascia f : t){
			for(Fascia d : this)
			{
				if(f.interseca(d)){
					return true;
				}
			}
		}
		return false;
	}

	public String getTitolo(){return titolo;}

}
