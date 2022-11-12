import java.util.*;

public class SparsePoly{

	// OVERVIEW : le istanze di questa classe rappresentano polinomi sparsi a una variabile.
	//			con grado non negativo. Un polinomio tipico c0x^d0 + ... + cnx^dx
	private record Monomio(int degree, int coeff){ // Figlio di Record.

		/**
		 * 
		 * @param degree Grado del monomio. This.degree viene posto uguale a degree.
		 * @param coeff Coefficiente del monomio. This.coeff viene posto uguale a coeff.
		 * 
		 * */
		public Monomio(int degree, int coeff){
			if(degree < 0 ) throw new NegativeExponentException("Il grado non può essere negativo");
			this.degree = degree;
			this.coeff = coeff;
		}
		/**
		 * @return Il coefficiente del monomio
		 * */
		public int getCoefficient(){
			return this.coeff;
		}

		@Override
		public String toString(){
			return coeff + "x^" + degree;
		}
	}	

	List<Monomio> terms;
	
	// AF(terms) = terms[0].coeff * x ^ terms[0].degree + ... +  terms[n].coeff * x ^ terms[n].degree
	// 				if n == 0 ==>  0

	// RI(terms) = tutti gli elementi di terms devono essere monomi. Ordinati in ordine di grado <--Se vogliamo evitarlo, scriviamo che non può essere NULL. 

	/**
	 * Costruisce il polinomio zero.
	 * */
	public SparsePoly(){
		terms = new LinkedList<>();
	}
	/**
	 * Costruisce il polinomio coef*x*degree
	 * @param coeff Coefficiente del monomio. Deve essere != 0.
	 * @param degree Grado del monomio. Deve essere >= 0.
	 * @throws NegativeExponentException Se l'esponente è negativo.
	 * */
	public SparsePoly(int coeff, int degree){
		this(); // Richiamo costruttore. Si deve fare PER FORZA come prima linea del costruttore. Sia con this() che con super()
		terms.add(new Monomio(degree,coeff));
	}

	/**
	 * @return Il grado del polinomio.
	 * */
	public int degree(){
		if(terms.size() == 0) return -1;
		return terms.get(terms.size()-1).degree;
	}	
	/**
	 * Ricerca di un termine di un certo grado all'interno del polinomio
	 * @param degree Grado interessato dalla ricerca
	 * @return L'indice all'interno di terms del monomio il cui grado è degree, altrimenti -IP (insertion point) - 1.
	 * */
	private int findBydegree(int degree){
		int min = 0, max = terms.size()-1;
		while(min < max){
			int mid = (min+max) >>> 1; // Non rischio overflow.
			int midDegree = terms.get(mid).degree; // Se avessi overflow accedo a qualcosa di negativo !
			if(midDegree > degree) min = mid + 1;
			else if(midDegree < degree) max = mid -1;
			else return mid;
		}
		return -(min+1);
	}
	/**
	 * 
	 * @param degree Il grado del monomio di cui vogliamo sapere il coefficiente. Deve essere >=0
	 * @return Il coefficiente del monomio di grado passato per parametro.
	 * */
	public int coeffByDegree(int degree){
		int indice = findBydegree(degree);
		if (indice >= 0 ) return terms.get(indice).coeff; 
		return 0;
	}

	/**
	 * @param other Polinomio da sommare a  this . Non  può essere null
	 * @return Polinomio risultante dalla somma di this + other.
	 * */
	public SparsePoly sum(SparsePoly other) throws IllegalArgumentException{
		if(other == null) throw new IllegalArgumentException("Polinomio da sommare è nullo.");
		SparsePoly newPoly = new SparsePoly(); 
		int i = 0, j = 0;
		while(i < this.terms.size() && j < other.terms.size()){
			if(other.terms.get(j).degree > this.terms.get(i).degree){
				newPoly.terms.add(this.terms.get(i));
				i++;
			}
			else if (other.terms.get(j).degree < this.terms.get(i).degree) {
				newPoly.terms.add(other.terms.get(j));
				j++;
			}else{
				int degr = this.terms.get(i).degree;
				int coefficient = this.terms.get(i).coeff + other.terms.get(j).coeff;
				Monomio temp = new Monomio(degr,coefficient);
				newPoly.terms.add(temp);
				i++;
				j++;
			}
		}

		for(int d = i; d < this.terms.size(); d++){newPoly.terms.add(this.terms.get(d));}
		for(int d = j; d < other.terms.size(); d++){newPoly.terms.add(other.terms.get(d));}

		return newPoly;
	}

	/**
	 * @param other Polinomio da moltiplicare a  this . Other non può essere null
	 * @return Polinomio risultante dal prodotto di this e other.
	 * */
	public SparsePoly mul(SparsePoly other) throws IllegalArgumentException{
		if(other == null) throw new IllegalArgumentException("Polinomio da moltiplicare è nullo.");
		SparsePoly newPoly = new SparsePoly();
		for(int i = 0; i < this.terms.size(); i++){
			for(int j = 0; j < other.terms.size();j++){
				int degreee = this.terms.get(i).degree + other.terms.get(j).degree;
				int coefficient = this.terms.get(i).coeff * other.terms.get(j).coeff;
				newPoly.terms.add(new Monomio(degreee,coefficient));
			}
		}
		return newPoly;
	}

	/**
	 * @param poly Polinomio da negare
	 * @return -this
	 * */
	public SparsePoly minus(SparsePoly poly) throws IllegalArgumentException{
		if(poly == null) throw new IllegalArgumentException("Polinomio da negare è nullo");
		SparsePoly newPoly = new SparsePoly();

		for(int i = 0; i < poly.terms.size(); i++){
			int degreee = poly.terms.get(i).degree;
			int coefficient = -poly.terms.get(i).coeff;
			newPoly.terms.add(new Monomio(coefficient,degreee));
		}
		return newPoly;
	}
	/**
	 * @param other Polinomio da sottrarre a  this. Non può essere null
	 * @return Polinomio risultato della sottrazione tra this e other.
	 * */
	public SparsePoly sub(SparsePoly other) throws IllegalArgumentException{
		if(other == null) throw new IllegalArgumentException("Polinomio da sottrarre è nullo");
		SparsePoly negativeOther = minus(other);
		return sum(negativeOther);
	}

	@Override
	public String toString(){
		String res = "";
		for( Monomio d : this.terms ){
			if(d.getCoefficient() > 0)
				res += " +";
			else res += " ";
			res += d.toString();
		}
		return res;
	}

}