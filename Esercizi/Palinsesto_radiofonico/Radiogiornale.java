/**
 * OVERVIEW: {@code Radiogiornale} è una classe concreta che modella un radiogiornale, un tipo di trasmissione ({@link Trasmissione}).
 * Un radiogiornale ha una durata inferiore all'ora e viene trasmesso sempre alle ore 09:40, 11:40, 19:40, 21:40, 22:40 e 25:40.
 * 
 * Le istanze di questa classe sono immutabili.
 * */

import java.util.*;

public class Radiogiornale extends Trasmissione{
	private final Fascia[] fasce = new Fascia[6];

	/*
            ABS FUN: AF(fasce,super.titolo) = La trasmissione periodica è rappresentata dalle fasce in cui va in onda e dal suo titolo.

            IR: fasce diverso da null, elementi di fasce diversi da null e le fasce devono essere tutte nello stesso giorno. Titolo diverso da null e non vuoto.
            La prima fascia deve iniziare alle 9:40, la seconda alle 11:40, la terza alle 19:40, la quarta alle 21:40, la quinta alle 22:40 e la sesta ed ultima alle 25:40.
            Le fasce non si devono sovrapporre tra loro.
            La durata delle fasce deve essere uguale ed inferiore alla betaora.
    */
 	
 	/**
 	 * Costruisce un radiogiornale dato il titolo e la sua durata.
 	 * 
 	 * @param durata La durata.
 	 * @param titolo Il titolo.
 	 * 
 	 * @throws NullPointerException Se il titolo è {@code null}.
 	 * @throws IllegalArgumentException Se il titolo è vuoto, se la durata è non positiva o se la durata non è inferiore alla betaora.
 	 * */
	public Radiogiornale(String titolo, int durata){
		super(titolo);
		if(durata <= 0 || durata > 30) throw new IllegalArgumentException("La durata deve essere compresa tra 1 e 30 inclusi");

		fasce[0] = new Fascia(new Betaorario(9,40),durata);
		fasce[1] = new Fascia(new Betaorario(11,40),durata);
		fasce[2] = new Fascia(new Betaorario(19,40),durata);
		fasce[3] = new Fascia(new Betaorario(21,40),durata);
		fasce[4] = new Fascia(new Betaorario(22,40),durata);
		fasce[5] = new Fascia(new Betaorario(25,40),durata);

	}

	@Override
    public String toString(){
    	StringBuilder sb = new StringBuilder("");
    	for(Fascia f: this){
    		sb.append(f.getInizio()+" -  "+super.getTitolo()+"\n");
    	}
    	return sb.toString();
    }

    @Override
    public Iterator<Fascia> iterator(){
    	return Collections.unmodifiableList(List.of(fasce)).iterator();
    }

}