import java.util.*;

public class Cane extends Pet{
	public Cane(String nome){
		this.nome = nome;
	}
	@Override
	void verso(){
		System.out.Println(this.nome + " dice bau");
	}
}