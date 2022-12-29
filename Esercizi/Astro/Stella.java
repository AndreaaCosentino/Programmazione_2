
/**
 * OVERVIEW: {@code Stella} modella una stella fissa, ovvero uno speciale tipo di Corpo celeste che non si muove. 
 * Le istanze di questa classe sono immutabili.
 * */
class Stella extends CorpoCeleste{
	private final Punto v = new Punto(0,0,0);
	protected final Punto pos;
	/*
		ABS FUN: AF(nome,pos):
				 = nome, pos.
	*/

	Stella(String nome,Punto pos){
		super(nome);
		this.pos = pos;
	}
	@Override
	Punto getPos(){
		return pos;
	}

	int energia(){return 0;}

	@Override
	public String toString(){
		return "Stella fissa, nome: " + this.nome +
					", pos: " + this.pos;
	}
}