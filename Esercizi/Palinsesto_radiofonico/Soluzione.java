import java.util.*;

public class Soluzione{

	//Non ho fatto la sua classe di soluzione, troppo tempo.
	//Piccolo esempietto per mostrare che ciò che è stato realizzato funziona.
	public static void main(String[] args){
		Betaorario uno = new Betaorario(15,69);
		Fascia unoFascia = new Fascia(uno,1);
		TrasmissioneSemplice ts = new TrasmissioneSemplice(unoFascia,"La mia trasmissione");
		Betaorario due = new Betaorario(23,41);
		Fascia dueFascia = new Fascia(due,1);
		TrasmissioneSemplice ds = new TrasmissioneSemplice(dueFascia,"Che bella trasmissione!");

		Radiogiornale l = new Radiogiornale("Che bel radiogiornale", 3);

		Palinsesto ilMioPalinsesto = new Palinsesto(List.of(ds,ts,l));
		System.out.println(ilMioPalinsesto);
	}
}