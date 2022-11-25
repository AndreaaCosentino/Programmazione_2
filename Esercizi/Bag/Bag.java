import java.util.*;

/**
 * OVERVIEW: {@code Bag} modella un bag che memorizza elementi generici ed il numero volte che sono stati inseritI.
 * */



public class Bag<E> implements Iterable<E>{


	HashMap<E,Integer> map;

	//COSTRUTTORI

	/**
	 * Inizializzazione di un bag. 
	 * */
	public Bag(){
		map = new HashMap<>();
	}

	//METODI

	/**
	 * @param elem Elemento che verrà aggiunto.
	 * @throws illegalArgumentException Se l'elemento è null
	 * */
	public void insert(E elem) throws IllegalArgumentException{
		if(elem == null) throw new IllegalArgumentException("Elemento nullo");

		Integer count = map.putIfAbsent(elem,1);

		if(count != null)
			map.replace(elem, ++count);
	}

	/**
	 * @param elem Elemento che verrà rimosso.
	 * @throws illegalArgumentException Se l'elemento è null.
	 * */
	public void remove(E elem) throws IllegalArgumentException{
		if(elem == null) throw new IllegalArgumentException("Elemento nullo");
		Integer  count = map.get(elem);

		if(count != null){
			if(count == 1)
				map.remove(elem);
			else map.replace(elem,--count);
		}
	}

	/**
	 * @return Oggetto iteratore su E.
	 * */
	@Override
	public Iterator<E> iterator(){
		return new Iterator<E>(){
			E curr = null;
			int counter = 1;
			Iterator<E> keys = map.keySet().iterator();
			/**
			 * @return Se  ci sono ancora elementi nel bag e se il loro contatore è inferiore al numero degli elementi da restituire, restituisce true. Altrimenti false
			 * */
			@Override
			public boolean hasNext(){
				if(curr != null && map.get(curr) > counter)
					return true;
				else if(keys.hasNext())
					return true;
				return false;
			}
			/**
			 * Recupera l'elemento successivo. Aumenta il contatore degli elementi di 1 ed aggiorna l'elemento corrente.
			 * Se il contatore dell'elemento supera la disponibilità, passo il prossimo elemento
			 * 
			 * @return Il prossimo elemento.
			 * @throws NoSuchElementException Se non ci sono elementi.
			 * */
			@Override
			public E next() throws NoSuchElementException{
				if(!(this.hasNext())) throw new NoSuchElementException("Non ci sono più elementi");

				if(curr != null && map.get(curr) > counter)
					counter++;
				else{
					curr = keys.next();
					counter = 1;
				}
				return curr;
			}
		};
	}

	@Override
	public String toString(){
		String rappr = "Bag - elements [ ";

		for(E i : this)
			rappr  += i + " ";

		rappr += "]";
		return rappr;
	}
	public static void main(String[] args){
		Bag<Object> b = new Bag<>();
		Scanner reader = new Scanner(System.in);
		switch(args[0]){
		case "Integer":
			System.out.println("Inserisci numeri Interi (CTRL+D per terminare)");
			while(reader.hasNextInt()){
				b.insert(reader.nextInt());
			}
			break;
		case "String":
			System.out.println("Inserisci Stringhe (CTRL+D per terminare)");
			while(reader.hasNext()){
				b.insert(reader.next());
			}
			break;
		case "Double":
			System.out.println("Inserisci numeri double (CTRL+D per terminare)");
			while(reader.hasNextDouble()){
				b.insert(reader.nextDouble());
			}
			break;
		}

		System.out.println(b);
	}
}