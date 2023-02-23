import java.util.*;
import java.nio.charset.*;
/**
 * OVERVIEW: {@code Decoder} è una classe concreta che modella un decoder, ovvero un decodificatore di byte.
 * 
 * Decoder è una classe di utilità.
 * 
 * */

public class Decoder{

	private Decoder(){}

	/**
	 * Restituisce l'array di byte dato decodificato in stringa, rispetto al charset dato.
	 * 
	 * @param bytes L'array di byte.
	 * @param charset Il charset.
	 * 
	 * @return La stringa risultante dalla decodifica.
	 * @throws NullPointerException Se l'array è {@code null} o se contiene elementi {@code null}, se il charset è {@code null}.
	 * @throws IllegalArgumentException Se l'array dato è vuoto.
	 * */
	public static String decodifica(Byte[] bytes, Charset charset){
		Objects.requireNonNull(bytes,"L'array non può essere null");
		Objects.requireNonNull(charset,"Il charset non può essere null");

		if(bytes.length == 0) throw new IllegalArgumentException("L'array non può essere vuoto");

		byte[] temp = new byte[bytes.length];
		for(int i = 0; i < bytes.length; i++){
			temp[i] = (Objects.requireNonNull(bytes[i],"L'array non può contenere elementi null")).byteValue();
		}
 
		return new String(temp,charset);
	}
}