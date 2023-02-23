import java.util.*;

/**
 * OVERVIEW: {@code Pacchetto} è una classe concreta che modella un pacchetto, ovvero una parte di un messaggio.
 * Le istanze di questa classe sono immutabili.
 * */

public class Pacchetto{
	private final int sequenza;
	private final long checksum;
	private final Byte[] contenuto;

	/*
		ABS FUN : AF(sequenza,checksum,contenuto) = il Pacchetto è rappresentato dal suo numero di sequenza, dal checksum e dal suo contenuto.

		IR: sequenza >= 0 e contenuto diverso da null come ogni suo elemento.
	*/

	/**
	 * Costruisce un oggetto pacchetto a partire da un dato numero di sequenza, un checksum e da un vettore di byte (che è l'informazione che conterrà il pacchetto).
	 * 
	 * @param sequenza La sequenza.
	 * @param checksum Il valore di checksum.
	 * @param contenuto Il vettore di byte.
	 * 
	 * @throws IllegalArgumentException Se sequenza è negativa.
	 * @throws NullPointerException Se l'array è {@code null} o se contiene elementi {@code null}.
	 * */
	public Pacchetto(int sequenza, long checksum, Byte[] contenuto){
		if(sequenza < 0) throw new IllegalArgumentException("Il numero di sequenza non può essere negativo");

		this.contenuto = new Byte[contenuto.length];
		Objects.requireNonNull(contenuto,"Il vettore contenuto non può essere null");

		for(int i = 0; i < contenuto.length; i++){
			this.contenuto[i] = Objects.requireNonNull(contenuto[i],"Il vettore contenuto non può contenere elementi null");
		}
		this.sequenza = sequenza;
		this.checksum = checksum;
	}

	//getters
	public int getSequenza(){return sequenza;}
	public long checksum(){return checksum;}
	public Byte[] getByte(){return contenuto;}
}