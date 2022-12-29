import java.util.*;
/**
 * OVERVIEW: {@code Punto} modella un punto, tridimensionale, nello spazio.
 * Le istanze di questa classe sono immutabili.
 * */
public class Punto{

	/*
		ABS FUN:  AF(x,y,z)
				 = x, y, z

		REP INV: //
	*/

	int x,y,z;

	// COSTRUTTORE
	/**
	 * Costruisce un oggetto di tipo Punto con le coordinate specificate.
	 * @param x Valore coordinata x
	 * @param y Valore coordinata y
	 * @param z Valore coordinata z
	 * */
	public Punto(int x,int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// METODI DI PRODUZIONE

	/**
	 * Restituisce un nuovo punto sommando this ad un punto dato.\n
	 * La somma viene fatta coordinata per coordinata.
	 * @param q Punto da sommare a this.
	 * @return Punto risultante dalla somma.
	 * @throws IllegalArgumentException Se il punto passato è null.
	 * */
	public Punto somma(Punto q){
		if (q == null) throw new IllegalArgumentException("Punto.somma");
		return new Punto(this.x+q.x,this.y+q.y,this.z+q.z);
	}
	/**
	 * Restituisce un nuovo punto sottraendo a this un punto dato.\n
	 * La sottrazione viene fatta coordinata per coordinata.
	 * @param q Punto da sommare a this.
	 * @return Punto risultante dalla somma.
	 * @throws IllegalArgumentException Se il punto passato è null.
	 * */
	public Punto sottrai(Punto q){
		if (q == null) throw new IllegalArgumentException("Punto.sottrai");
		return new Punto(this.x-q.x,this.y-q.y,this.z-q.z);
	}
	/**
	 * Restituisce la somma dei valori assoluti delle coordinate di this.
	 * @return La somma dei valori assoluti delle coordinate.
	 * */
	public int norma(){
		return Math.abs(this.x)+
				Math.abs(this.y)+
				 Math.abs(this.z);
	}
	//METODI OSSERVAZIONALI
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("(x: "
							+ x + " y: "+y+" z: "+z + ")");
		return sb.toString();
	}
}