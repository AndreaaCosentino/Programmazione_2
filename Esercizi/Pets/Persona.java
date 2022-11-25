import java.util.*;

/**
 * OVERVIEW: {@code Persona} ...
 * */

public class Persona implements Iterable<Pet>{

	String nome;
	List<Pet> pets;
	//COSTRUTTORI
	/**
	 * 
	 * */
	public Persona(String nome){
		this.nome = nome;
		pets = new ArrayList<>();
	}

	//metodi
	public String getNome(){
		return this.nome;
	}

	public void add(Pet pet){
		pets.add(pet);
	}

	public void coro(){
		System.out.println("Il coro degli animali di " + this.nome + ": " );
		for(Pet p: this.pets)
			p.verso();
	}	

	@Override
	public boolean equals(Object obj){
		if(obj instanceof Persona)
			return this.nome.equals(((Persona)obj).nome);
		return false;
	}

	public Iterator<Pet> iterator(){
		return new Iterator<Pet>(){
			Iterator<Pet> ip = pets.iterator();
			@Override
			public boolean hasNext(){
				return ip.hasNext();
			}
			@Override
			public Pet next(){
				return ip.next();
			}
		};
	}

}