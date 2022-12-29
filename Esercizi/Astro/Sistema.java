import java.util.*;
/**
 * OVERVIEW: {@code Sistema} Modella un sistema astronomico. 
 * Istanze di questa classe sono mutabili.
 * */
public class Sistema implements Iterable{

	/*
		ABS FUN:  AF(corpi) = 
				  = [] se corpi.length = 0
				  = [corpi[i] |  0 <=  i < corpi.length ] altrimenti

		RI: 	[corpi[i].nome != corpi[j].nome | i != j && 0 <= i < corpi.length && 0 <= j < corpi.length]
		
	*/

	private final List<CorpoCeleste> corpi;

	public Sistema(){
		corpi = new ArrayList<CorpoCeleste>();
	}

	public Sistema(CorpoCeleste c){
		corpi = new ArrayList<CorpoCeleste>();
		corpi.add(c);
	}
	/**
	 * Aggiunge un corpo celeste dato a this.
	 * @param c Corpo celeste aggiunto
	 * @throws IllegalArgumentException Se il corpo è già presente
	 * */
	public void add(char id, String nome, int x , int y, int z){
		CorpoCeleste c;
		if (id == 'P'){
			c = new Pianeta(nome,new Punto(x,y,z));
		}else{	
			c = new Stella(nome,new Punto(x,y,z));
		}
		for( CorpoCeleste e : corpi){
			if (e.equals(c)) throw new IllegalArgumentException("Sistema.add");
		}
		corpi.add(c);
		assert repOk();
	}
	/**
	 * Restituisce l'energia totale del sistema astronomico this.
	 * @return Energia totale
	 * */
	public int energiaSistema(){
		int energiaTotale = 0;

		for(CorpoCeleste e: corpi){
			energiaTotale += e.energia();
		}
		return energiaTotale;
	}

	/**
	 * Simula l'evoluzione del sistema astronomico per un quanto di tempo.
	 * La posizione dei pianeti e la loro velocità viene modificata.
	 * */
	public void simula(){
		for(CorpoCeleste e: corpi){
			e.attrazione(this);
		}
		for(CorpoCeleste e: corpi){
			e.aggiorna();
		}
		assert repOk();
	}	

	@Override
	public Iterator<CorpoCeleste> iterator(){
		return new Iterator<CorpoCeleste>(){
			int counter = 0;

			@Override
			public boolean hasNext(){
				if (corpi.size() > counter){
					return true;
				}
				return false;
			}

			@Override
			public CorpoCeleste next() throws NoSuchElementException{
				if(!this.hasNext()) throw new NoSuchElementException("Corpi.iterator.Next");
				CorpoCeleste e = corpi.get(counter);
				counter++;
				return e;
			}	
		};
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		for(CorpoCeleste e : corpi){	
			sb.append(e);
			sb.append("\n");
		}
		sb.append("Energia totale: " + this.energiaSistema());
		return sb.toString();
	}

	public boolean repOk(){
		for(CorpoCeleste e: corpi){
			for(CorpoCeleste e1: corpi){
				if(!(e == e1)){
					if (e.equals(e1))
						return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] Args){
		Scanner s = new Scanner(System.in);

		Sistema sistema = new Sistema();

		while(s.hasNext()){
			char pOrS = s.next().charAt(0); // can be P or S
     		String name = s.next();
      		int x = s.nextInt();
     		int y = s.nextInt();
     		int z = s.nextInt();
     		sistema.add(pOrS,name,x,y,z);
		}
		int simulazioni = Integer.parseInt(Args[0]);
		for (int i = 0; i < simulazioni; i++)
			sistema.simula();
		System.out.println(sistema);
	}
}