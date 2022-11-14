import java.util.*;

/**
 * {@code IntSet} sono mutabili, insieme di interi non limitato.
 * 
 * 
 * */

public class IntSet{

	//OVERVIEW: le istanze di questa classe rappresentano insiemi di interi.
	//			Un intero non può essere duplicato all'interno di un insieme.
	//			Un esempio di insieme è {1,2,...,n}.

	List<Integer> set;


	//AF(set)  = {set[0],set[1],...,set[n-1],set[n]}
	//			  Se n == 0 ==> "Insieme vuoto"

	//RI(set) = tutti gli elementi di set devono essere numeri interi. Non possono essere duplicati. 
	//			Se un intero è già parte di set, non può essere inserito un'altra volta.

	/**
	 * Inizializza questo insieme a vuoto.
	 * 
	 * */
	public IntSet(){
		set = new LinkedList<>();
	}

	/**
	 * Aggiunge un elemento a questo insieme. //Modifies
	 * 
	 * Questo metodo modifica l'oggetto, ovvero aggiunge un elemento all'insieme.
	 * Se l'elemento è già presente nell'insieme, non viene fatto nulla. 
	 * 
	 * @param x Elemento da aggiungere
	 * */
	public void insert(int x){
	    boolean isContained = this.set.contains(x);
	    if(!isContained)
			this.set.add(x);
	}

	/**
	 * Elimina un elemento a questo insieme.
	 * 
	 * Questo metodo modifica l'oggetto, ovvero elimina un elemento dall'insieme.
	 * 
	 * @param x Elemento da eliminare
	 * */
	public void removeElement(int x){
		boolean removed = this.set.remove(Integer.valueOf(x));
	}

	// METODI OSSERVAZIONALI
	/**
	 * Dice se un dato elemento è all'interno di questo insieme.
	 * 
	 * @param x l'elemento che cerchiamo. // I prerequisiti li metto qua.
	 * @return Se l'intero appartiene all'insieme o meno // effects
	 * 
	 * */
	public boolean contains(int x){
		return false;
	}

	/**
	 * Restituisce la cardinalità di QUESTO insieme.
	 * 
	 * @return la grandezza di questo insieme. 
	 * */
	public int size(){
		return this.set.size();
	}

	/**
	 * Restituisce un elemento da questo insieme.
	 * 
	 * @return Un elemento arbitrario di questo insieme.
	 * @throws EmptyException Se l'insieme è vuoto
	 * */
	public int choose() {
			Random rand = new Random();
			if(this.set.size() == 0) throw new EmptyException("L'insieme è vuoto");
			int i = rand.nextInt(this.set.size());
			return this.set.get(i);
	}

	@Override
	public String toString(){
	 	String sol = "{";
	 	for(int i = 0; i < this.set.size(); i++){
	 		if(i != this.set.size() - 1)
	 			sol += i + ",";
	 		else sol += i;
		}
		sol += "}";
		return sol;
	}
}