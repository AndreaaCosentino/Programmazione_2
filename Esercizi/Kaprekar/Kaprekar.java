import java.util.*;


// Esercizio Kaprekar

public class Kaprekar{
	private static final int COSTANTE_KAPREKAR = 6174;
	public static void main(String[] args){
		Scanner s  = new Scanner(System.in);
		int x = s.nextInt();
		stampaSequenzaKaprekar(x);
		s.close();
	}
	/**
	 * Stampa la sequenza di Kaprekar ottenuta a partire da n.
	 * @param n Punto di partenza della sequenza di Kaprekar. 
	 * */
	public static void stampaSequenzaKaprekar(int n){
		while(true){
			int x = succSequenza(n);
			System.out.println(x);
			if(x == COSTANTE_KAPREKAR || x == 0)
				return;
			n = x;
		}
	}
	/**
	 * Calcola il numero successivo della sequenza.
	 * @param n Numero da cui calcolare il successivo.
	 * @return Numero della sequenza di kaprekar calcolato.
	 * */
	public static int succSequenza(int n){
		int p1 = vettoreToNum(ordinaCrescente(numToVettore(n)));
		int p2 = vettoreToNum(ordinaDecrescente(numToVettore(n)));
		return p1-p2;
	}

	/**
	 * Converte un numero in vettore.
	 * <p>
	 * L'elemento in posizione 0 del vettore sarà la cifra meno significativa.
	 * @param n Numero da convertire in vettore 
	 * @return Vettore contenente il numero "spezzettato"
	 * */
	public static int[] numToVettore(int n){
		int[] v = new int[]{0,0,0,0};
		for(int i = 0;n != 0; i++){
			v[i] = n%10;
			n /= 10;
		}
		return v;
	}
	/**
	 * Converte un vettore in numero. 
	 * <p>
	 * L'elemento in posizione 0 del vettore sarà la cifra meno significativa del numero.
	 * Gli elementi del vettore devono essere comrpesi tra 0 e 9, inclusi. 
	 * Il riferimento passato deve essere diverso da NULL.
	 * @param v Vettore contenente le cifre del numero
	 * @return Numero ottenuto mettendo insieme le cifre contenute nel vettore.
	 * */
	public static int vettoreToNum(int[] v){
		int c = 0,n = 1;
		for(int i = 0; i < 4 ; i++){
				c += v[i]*n;
				n *= 10;
		}
		return c;
	}
	/**
	 * Ordina il vettore in modo crescente.
	 * @param v Vettore da ordinare
	 * */
	public static int[] ordinaCrescente(int[] v){
		for(int i = 0; i < v.length ; i++){
			for(int k = 0; k < v.length -1;k++){
				if(v[k] > v[k+1]){
					int temp = v[k];
					v[k] = v[k+1];
					v[k+1] = temp;
				}
			}
		}
		return v;
	}
	/**
	 * Ordina il vettore in modo decrescente.
	 * @param v Vettore da ordinare
	 * */
	public static int[] ordinaDecrescente(int[] v){
		for(int i = 0; i < v.length; i++){
			for(int k = 0; k < v.length -1;k++){
				if(v[k] < v[k+1]){
					int temp = v[k];
					v[k] = v[k+1];
					v[k+1] = temp;
				}
			}
		}
		return v;
	}
}