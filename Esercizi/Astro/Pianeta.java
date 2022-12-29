import java.util.*;
/**
 * OVERVIEW: {@code Pianeta} modella un Pianeta, ovvero uno speciale tipo di Corpo celeste che ha una velocità associata. 
 * Le istanze di questa classe sono mutabili
 * 
 * */
class Pianeta extends CorpoCeleste{
	private Punto v = new Punto(0,0,0);
	protected Punto pos;

	Pianeta(String nome,Punto pos){
		super(nome);
		this.pos = pos;
	}


	/**
	 * {@inheritdoc}
	 * */
	int energia(){
		return this.v.norma() * this.pos.norma();
	}

	/**
	 * Aggiorna la posizione del pianeta secondo la sua velocità.
	 * */
	void aggiorna(){
		this.pos = this.pos.somma(v);
	}

	/**
	 * Simula l'attrazione gravitazionale di this con il corpo celeste dato.
	 * This subisce modifica in posizione e velocità.
	 * @param c Corpo celeste con cui simulare attrazione. 
	 * */
	void attrazione(Sistema s){
		Iterator<CorpoCeleste> i = s.iterator();
		CorpoCeleste d;
		while (i.hasNext()){
			d = i.next();
			if (this.equals(d)){continue;}
			if (d.getPos().x < pos.x){
				v = this.v.sottrai(new Punto(1,0,0));
			}
			if (d.getPos().x > pos.x){
				v = this.v.somma(new Punto(1,0,0));
			}

			if (d.getPos().y < pos.y){
				v = this.v.sottrai(new Punto(0,1,0));
			}
			if (d.getPos().y > pos.y){
				v = this.v.somma(new Punto(0,1,0));
			}

			if (d.getPos().z < pos.z){
				v = this.v.sottrai(new Punto(0,0,1));
			}
			if (d.getPos().z > pos.z){
				v = this.v.somma(new Punto(0,0,1));
			}
		}	
	}

	Punto getPos(){
		return pos;
	}

	@Override
	public String toString(){
		return "Pianeta, nome: " + this.nome +
					", pos: " + this.pos +
						", vel: " + this.v;
	}
}