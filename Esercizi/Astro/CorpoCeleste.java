/**
 * 
 * Overview: {@code CorpoCeleste} modella un corpo celeste. Le istanze di questa classe sono immutabili.
 * Due corpi sono uguali se hanno lo stesso nome.
 * */
class CorpoCeleste{
	protected final String nome;

	CorpoCeleste(String nome){
		this.nome = nome;
	}

	/**
	 * Aggiorna la posizione del pianeta secondo la sua velocità.
	 * */
	void aggiorna(){}

	String getNome(){
		return this.nome;
	}
	/**
	 * Restituisce il valore d'energia per this.
	 * @return Energia corpo celeste
	 * */
	int energia(){return 0;}
	/**
	 * Simula l'attrazione gravitazionale di this con il corpo celeste dato.
	 * This subisce modifica in posizione e velocità.
	 * @param c Corpo celeste con cui simulare attrazione. 
	 * */
	void attrazione(Sistema s){}

	Punto getPos(){return null;}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof CorpoCeleste)) return false;
		CorpoCeleste c = (CorpoCeleste)o;

		if (this.nome.equals(c.getNome())){return true;}
		return false;
	}
}