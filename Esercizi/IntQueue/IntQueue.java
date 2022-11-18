//Testo del problema a https://github.com/prog2-unimi/esercitazioni/tree/aa2223/testi/e04/01-intqueue
import java.util.*;

/*Non vogliamo esporre la rappresentazione perchè:
	1- Se domani cambiamo rappresentazione NON VOGLIAMO che i programmi si rompano
	2- Motivi di sicurezza.
	*/

// ILLEGALSTATEEXCEPTION: SOLO SE L'IR NON è OKAY. 

public class IntQueue2{
	/*
		OVERVIEW: Le istanze di questo tipo sono mutabili
	*/

	/*	AF  head == -1 -> []
			[elements[i] | i = head,...,tail-1] se tail > head
			[elements[i] | i = ehad,(head+1)%el.length,...,(tail-1)%el.length]	altrimenti
	*/

	/* RI -1 <= head < elements.length()
		  0  <= tail < elements.length()
		  elements array di int
		  head == -1 => tail  == 0
	*/

	// CAMPI
	private int head,tail;
	private final int[] elements;

	// COSTRUTTORI
	/**
	 * Costruisce una coda di dimensione n
	 * @throws IllegalArgumentException Se n è <= 0
	 * */
	public IntQueue(int n){
		if(n <= 0) throw new IllegalArgumentException("Impossibile ...");
		elements = new int[n];
		head = -1;
		tail = 0;
		assert repOk();
	}

	/**
	 * Modifica this aggiungendo un elemento in coda alla coda se non è piena.
	 * @param n Elemento aggiunto alla coda.
	 * @throws FullQueueException Se la coda è piena
	 * */
	public void enqueue(int n){
		if(isFull()) throw new FullQueueException("Impossibile inserire elemento. Coda piena.");
		elements[tail] = n;
		tail = (tail+1)%elements.length;
		assert repOk();
	}

	/**
	 * Modifica this elimianndo l'elemento in testa alla coda.
	 * @return L'elemento in cima alla coda.
	 * @throws EmptyQueueException Se la coda è vuota.
	 * */

	public int dequeue(){
			if(isEMpty()) throw new EmptyQueueException("Impossibile rimuovere elemento. Coda vuota");
			int result = elements[head];
			head = (head+1)%elements.length;
			if(head == tail){
				head = -1;
				tail = 0;
			}
			assert repOk();
			return result;
	}

	public int size(){
		if(head == -1) return 0;
		return (tail-head + elements.length)%elements.length();
	}
	/**
	 * @return True se la coda è vuota, false altrimenti.
	 * */
	public boolean isEMpty(){
		return head == -1;
	}
	/**
	 * @return True se la coda è piena, false altrimenti.
	 * */
	public boolean isFull(){
		return head == tail;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("IntQueue: [");
		if(size() > 0 ){
			for(int i = 0; i < size()-1; i++)
				sb.append(elements[(head+i)%elements.length] + ", ");
			sb.append(elements[(head+i)%elements.length] );
		}
		sb.append(']');
		//sb.append("Capienza massima: " +elements.length);
		return sb.toString();
	}
	@Override
	public boolean equals(Objects o){
		if(!(o  instanceof IntQueue2)) return false;

		IntQueue2 other = (IntQueue2)o;
		if(elements.length !=  other.elements.length) return false;
		if(size() != other.size()) return false;
		for(int i = 0; i < size(); i++)
			if(elements[(head+1)%elements.length] !=  other.elements[ (other.head+1)%other.elements.length])
				return false;
		return true;
	} // Negli oggetti mutabili non ci sono equals. Ci sono "similiars".
	@Override
	public int hashCode(){
		int result =  Integer.hashCode(elements.length);

		for(int i = 0;  i < size(); i++)
			result = 31*result  + Integer.hashCode(elements[ (head+1)%elements.length]);
			// 31 è un numero primo. Non è divisibile per due, quindi non può essere semplificata con degli shift.
			// 31 è abbastanza grande, così gli hashCode sono distribuiti. (31 è il numero di bit per la rappresentazione di numeri)
			// Se non applicassimo la moltiplicazione, perderemmo delle informazioni sull'ordine.
		return result;
	}

	private boolean repOk(){
		/* RI -1 <= head < elements.length()
		  0  <= tail < elements.length()
		  elements array di int
		  head == -1 => tail  == 0
		*/
		return -1 <= head
			&& head < elements.length // Non possiamo usare size(), perchè non sappiamo com'è implementata. Potrebbe rompere l'invariante.
			&& head 0 <= tail
			&& (head != -1  || tail == 0);
	}
}

// JSHELL COMANDO.