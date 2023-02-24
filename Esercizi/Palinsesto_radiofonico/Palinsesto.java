/**
 * OVERVIEW: {@code Palinsesto} è una classe concreta che modella un palinsesto giornaliero. Un palinsesto è un insieme di trasmissioni
 * che non si intersecano tra di loro.
 * */

import java.util.*;

public class Palinsesto{
	private final List<Trasmissione> trasmissioni;

	/*
		ABS FUN: AF(trasmissioni) = Un palinsesto è rappresentato dalle trasmissioni che lo compongono ordinate in base al betaorario.

		IR: trasmissioni != null e le trasmissioni non si devono sovrapporre.
	*/

	/**
	 * Costruisce un palinsesto data una lista di trasmissioni.
	 * @param trasmissioni La lista di trasmissioni.
	 * @throws NullPointerException Se la lista è {@code null} o se contiene elementi {@code null}.
	 * @throws IllegalArgumentException Se delle trasmissioni nella lista si sovrappongono.
	 * */
	public Palinsesto(List<Trasmissione> trasmissioni){
		Objects.requireNonNull(trasmissioni,"La lista non può essere null");
		List<Trasmissione> temp = new ArrayList<>();
		for(Trasmissione t : trasmissioni){
			Objects.requireNonNull(t,"Le trasmissioni non possono essere null");
			for(Trasmissione d : temp){
				if(t != d)
				if(t.interseca(d)) throw new IllegalArgumentException("Le trasmissioni non possono sovrapporsi");
			}
			temp.add(t);
		}

		this.trasmissioni = temp;
	}

	@Override
	public String toString(){
		List<String> temp = new ArrayList<>();
		for(int i = 0; i < trasmissioni.size(); i++){
			String e = trasmissioni.get(i).toString();
			String[] d = e.split("\n");

			for(String j : d){
				temp.add(j);
			}
		}
		Collections.sort(temp);

		StringBuilder sb = new StringBuilder("");

		for(String s : temp){
			sb.append(s+"\n");
		}

		return sb.toString();
	}
}