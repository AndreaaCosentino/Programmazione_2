/**
 * 
 * Overview: {@code CorpoCeleste} modella un corpo celeste. Le istanze di questa classe sono immutabili.
 * Due corpi sono uguali se hanno lo stesso nome.
 * */
abstract class CorpoCeleste implements Comparable{
	protected final String nome;

	CorpoCeleste(String nome){
		this.nome = nome;
	}

	String getNome(){
		return this.nome;
	}
	/**
	 * Restituisce il valore d'energia per this.
	 * @return Energia corpo celeste
	 * */
	abstract int energia();

	abstract Punto getPos();

	public int compareTo(CorpoCeleste o){
		return nome.compareTo(o.getNome());
	}
	@Override
	public boolean equals(Object o){
		if(!(o instanceof CorpoCeleste)) return false;
		CorpoCeleste c = (CorpoCeleste)o;

		if (this.nome.equals(c.getNome())){return true;}
		return false;
	}
}