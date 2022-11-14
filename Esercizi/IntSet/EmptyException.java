import java.util.*;

public class EmptyException extends RuntimeException{
	public EmptyException(){
		super();
	}

	public EmptyException(String msg){
		super(msg);
	}
}