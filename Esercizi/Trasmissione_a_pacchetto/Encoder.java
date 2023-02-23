import java.util.*;
import java.nio.charset.*;

/**
 * OVERVIEW: {@code Encoder} è una classe concreta che modella un Encoder, ovvero un codificatore che codifica una stringa in byte.
 * 
 * E' una classe di utilità.
 * */

public class Encoder{

	private Encoder(){};


	/**
	 * Codifica il messaggio dato in un array di Byte, secondo il charset dato.
	 * 
	 * @param messaggio Il messaggio.
	 * @param charset Il charset.
	 * 
	 * @return L'array di byte risultante dalla codifica del messaggio.
	 * 
	 * @throws IllegalArgumentException Se il messaggio è vuoto.
	 * @throws NullPointerException Se il messaggio è {@code null} o se il charset è {@code null}.
	 * */
	public static Byte[] codifica(String messaggio, Charset charset){
		Objects.requireNonNull(messaggio,"Il messaggio non può essere null");
		Objects.requireNonNull(charset,"Il charset non può essere null");

		if(messaggio.length() == 0) throw new IllegalArgumentException("Il messaggio non può essere vuoto");

		byte[] temp = messaggio.getBytes(charset);
		Byte[] result = new Byte[temp.length];

		for(int i = 0; i < temp.length; i++){
			result[i] = Byte.valueOf(temp[i]);
		}
		return result;
	}
}