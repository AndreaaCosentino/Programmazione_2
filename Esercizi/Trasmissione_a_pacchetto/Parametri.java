import java.util.*;
import java.nio.charset.*;
import java.util.zip.*;
/**
 * OVERVIEW: {@code Parametri} è una classe concreta che modella i parametri di trasmissione dei messaggi.
 * Le istanze di questa classe sono immutabili.
 * */

public class Parametri{
	private final int dim;
	private final Checksum algoritmo;
	private final Charset charset;

	/*
		ABS FUN : AF(dim,algoritmo,charset) = è rappresentato dalla dimensione, dall'algoritmo che utilizza per il checksum e dal tipo di charset.

		IR: dim > 0, algoritmo != null e charset != null.

	*/

	/**
	 * Costruisce un oggetto parametri data la dimensione del messaggio, l'algoritmo di checksum e il charset.
	 * 
	 * @param dim La dimensione.
	 * @param algoritmo L'algoritmo.
	 * @param charset Il charset.
	 * 
	 * @throws IllegalArgumentException Se dim è non positivo.
	 * @throws NullPointerException Se algoritmo o charset è {@code null}.
	 * */
	public Parametri(int dim, Checksum algoritmo, Charset charset){
		this.algoritmo = Objects.requireNonNull(algoritmo,"L'algoritmo non può essere null");
		this.charset = Objects.requireNonNull(charset,"Il charset non può essere null");

		if(dim <= 0) throw new IllegalArgumentException("La dimensione deve essere positiva");

		this.dim = dim;
	}

	public Checksum getChecksum(){return algoritmo;}
	public Charset getCharset(){return charset;}
	public int getDim(){return dim;}
}