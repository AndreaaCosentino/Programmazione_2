//Testo del problema a https://github.com/UnimiDiComdigProg2/Lab_22-23/blob/main/Laboratorio5/6.Pretendenti/README.md
import java.util.*;


public class Pretendenti implements Iterable<Integer> {

		ArrayList<Integer> pretendenti;


		/**
		 * Istanzia this con il numero di pretendenti.
		 * 
		 * @param n Numero pretendenti.
		 * @throws IllegalArgumentException Se il parametro n è minore od uguale a 0.
		 * */
		public Pretendenti(int n) throws IllegalArgumentException{
			//Il costruttore modifica sempre l'oggetto istanziato
			if(n <= 0) throw new IllegalArgumentException("Ci devono essere dei pretendenti");

			this.pretendenti = new ArrayList<Integer>();

			for(int index = 1; index <= n; index++){ // Da 1 perchè non abbiamo pretendente 0.
				this.pretendenti.add(index);
			}

		}

		/**
		 *  
		 * @return Il pretendente vincitore
		 * */
		public int getPrimo(){
			return this.pretendenti.get(0);
		}

		@Override
		public String toString(){
			String ret = "Pretendenti: ";
			for(int i : this.pretendenti){
				ret += i + " ";
			}
			return ret;
		}
		/**
		 * 	L'iteratore può modificare this eliminando l'ultimo pretendente selezionato.
		 *	@return Iteratore che seleziona un nuovo pretendente a distanza 3 dall'ultimo restituito.
		 * */
		public Iterator<Integer> iterator(){

			return new Iterator<Integer>(){ // Detta classe anonima, assomiglia ad una "classe lambda".

				boolean direction = true; 
				boolean removed = false;
				int current = 0;
				/**
				 * @return True se ci sono pretendenti eliminabili. Se rimane solo un pretendente restituisce false.
				 * */
				@Override
				public boolean hasNext(){

					return (Pretendenti.this.pretendenti.size() > 1);
				}

				/**
				 * Aggiorna l'elemento corrente di this cambiando la direzione di ricerca ed impostando removed a true.
				 * @return Il prossimo pretendente da scartare.
				 * @throws NoSuchElementException Se non ci sono più pretendenti eliminabili
				 * */
				@Override
				public Integer next() throws NoSuchElementException{ 

					if(!hasNext()) throw new NoSuchElementException("Non ci sono più pretendenti eliminabili");

					this.removed = false;

					if(this.direction)
						this.current +=2;
					else
						this.current -= 2;

					if(this.current >= Pretendenti.this.pretendenti.size()-1){
						this.current = (Pretendenti.this.pretendenti.size() - 1) - ( this.current - (Pretendenti.this.pretendenti.size()-1));
						this.direction = false;
					}

					if(this.current <= 0){
						this.current *= -1;
						this.direction = true;
					}

					return Pretendenti.this.pretendenti.get(this.current);
				}

				/**
				 * Rimuove (da Pretendente.this) il pretendente selezionato da next().
				 * Aggiorna l'elemento corrente di this cambiando la direzione di ricerca ed impostando removed a false.
				 * @throws IllegalStateException Se è già stato invocato un remove senza aver fatto il next()
				 * */
				@Override
				public void remove() throws IllegalStateException{
					if(this.removed) throw new IllegalStateException("Elemento già rimosso");
					Pretendenti.this.pretendenti.remove(this.current);
					this.removed = true;
					if(!this.direction)
						this.current--;
					if(this.current > Pretendenti.this.pretendenti.size() - 1)
						this.current--;
					if(this.current  == Pretendenti.this.pretendenti.size() - 1)
						this.direction = false;
					if(this.current == 0)
						this.direction = true;
				}

				@Override
				public String toString(){
					if(this.current == 0)
						return "Si conta da " + Pretendenti.this.pretendenti.get(this.current) + " in direzione avanti";
					if(this.direction)
						return "Si conta da " + Pretendenti.this.pretendenti.get(this.current) + " in direzione avanti";

						return "Si conta da " + Pretendenti.this.pretendenti.get(this.current) + " in direzione indietro";
				}
			};
		}

	public static void main(String[] args){
		int n = Integer.parseInt(args[0]);
		Pretendenti p = new Pretendenti(n);

		Iterator<Integer> i = p.iterator();

		while(i.hasNext()){
			System.out.println(p);
			System.out.println(i);
			System.out.println("Eliminato: " + i.next());
			i.remove();
		}

		System.out.println("Il vincitore è " + p.getPrimo());
	}
}
