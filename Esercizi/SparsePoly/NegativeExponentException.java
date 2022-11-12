import java.util.*;

public class NegativeExponentException extends RuntimeException{ // Estende RuntimeException perchè è figlia di questa.
	public NegativeExponentException(){
		super(); // Si comporta come il padre. Richiama il costruttore del genitore.
	}

	public NegativeExponentException(String msg){
		super(msg);
	}
}