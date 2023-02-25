### Domande orale

Qui sotto sono riportate le domande fatte agli orali di *programmazione II* e le risposte.
Le risposte non devono essere imparate a memoria, servono solo come aiuto a comprendere i diversi aspetti di *Java*.

---
##### Classi innestate

Per rispondere a questa domanda è bene leggere l'item 24 di *Effective Java*.
Le classi innestate sono delle classi definite all'interno di altre classi. Le classi innestate dovrebbero esistere solamente per servire la classe contenitrice, altrimenti, se servono a più classi, può essere che la scelta di pensarla come classe innestata sia da rivedere.
Le classi innestate sono quattro:
- La classe membro statica 
- La classe membro non statica
- La classe anonima
- La classe locale

Tutte tranne la prima vengono dette classi **interne**. Si noti che la classe membro statica **NON** è una classe interna. Classe interna e classe innestata hanno due significati differenti.
La classe membro statica può essere pensata come una classe che potrebbe vivere all'esterno ma è dichiarata all'interno di un'altra classe ed ha accesso ai membri di quest'ultima, anche quelli dichiarati privati. E' un membro statico della classe, quindi non può utilizzare this, ed obbedisce a tutte le regole a cui obbediscono le istanze di classe. 
E' chiaro che una classe statica non è interna ad un'altra solo per ragioni di naming o per comidità logica, ma bensì perchè può accedere ai membri privati della classe contenitrice. Si prenda questo pezzo di codice come esempio:

```java
public class Biblioteca{
     private final String nome;
     private final String città;
     private final int capienzaPosti;
     ...
     
     public static class Libro{
         private final Biblioteca biblioteca;
         public Libro(Biblioteca biblioteca){
             ...
             this.biblioteca = biblioteca;
         }
         
         public String doveSiTrova(){
             return biblioteca.città;
         }
     }
}
```
L'esempio qui sopra riportato non ha molto senso dal punto di vista logico, ma permette di capire un punto di forza di una classe innestata. Data un'istanza della classe esterna, noi siamo in grado di accedere a tutti i membri, anche se privati.
Non saremmo stati in grado di farlo qualora la classe vivesse all'esterno. 
In un tema d'esame è quindi **sbagliato** utilizzare delle classi innestate che non facciano alcun accesso ai membri della classe contenitrice. Unica eccezione è per ragioni di denominazione, dato che noi ci riferiamo ad una classe innestata con davanti il nome della classe contenitrice.
Si noti come, essendo statica la classe non ha alcun riferimento nascosto ad un'istanza della classe esterna. Questo è importante perchè se non si fa attenzione e si utilizzano classi non statiche si rischia un memory leak catastrofico.

La classe membro non statica è una classe interna, ed ogni istanza di questa è associata implicitamente ad un'istanza della classe esterna. In altre parole, non può esistere un'istanza della classe interna se non esiste un'istanza della classe esterna, perchè la prima si riferisce alla seconda. All'interno di questa classe possiamo utilizzare il construtto *this*, e possiamo accedere a tutti i membri della classe esterna, anche quelli privati.
Come scegliamo tra queste due tipologie? Le due domande da porsi sono:
- La classe innestata mi può servire senza un'istanza della classe esterna?
- La classe innestata ha bisogno di accedere ad un'istanza della classe esterna?

Se la classe innestata che definiamo deve poter esistere in modo isolato dalla classe esterna, è meglio se dichiarata static. Se però abbiamo bisogno che si riferisca ad un'istanza, allora può essere che la scelta migliore sia non statica.

La classe membro non statica viene utilizzata, per esempio, per implementare le **viste** di una collezione. Una vista è una collezione che si basa su un'altra collezione ed usa memoria costante. Una vista è un modo per vedere la collezione.
Per esempio una mappa noi possiamo avere il Keyset, che ci permette di vedere tutte le chiavi interne alla mappa. Ma anche l'entryset, che permette di vedere tutte le entry.
E' **sbagliato** usare una classe non statica se non accediamo ad alcun membro dell'istanza della classe contenitrice.

Delle altre due classi rimanenti ne abbiamo parlato poco durante il corso, e la domanda verte perlopiù sulle precedenti due descritte. Ciò nonostante, la classe anonima (di cui abbiamo fatto uso per iteratori e comparatori) è una classe senza nome.
Esempio:
```java
public Iterator<E> iterator(){
    return new Iterator<E>(){
        @Override public boolean hasNext(){
            return false;
        }
        @Override public E next(){
            return null;
        }
    };
}
```
La classe definita dentro il metodo iterator, in questo caso, è detta classe anonima.
Una classe anonima **non** è un membro della classe contenitrice. Non è dichiarata insieme agli membri della classe ma simultaneamente dichiarata ed instaziata nello stesso punto d'utilizzo. Cioè, il momento in cui utilizziamo la classe è lo stesso momento in cui la dichiriamo ed instanziamo. Possiamo vedere la classi anonima come delle variabili all'interno di un metodo, con la differenza che non possiamo riutilizzarle più volte nello stesso blocco. Quindi, proprio come delle variabili, possiamo scriverle in qualunque punto dove un'espressione è legale.
Le classi anonime possono trovarsi in un contesto statico ma anche non statico. L'esempio fatto precedentemente è un contesto non statico. Nei contesti non statici, all'interno della classe anonima possiamo riferici all'istanza della classe esterna.
Si faccia attenzione al fatto che anche se una classe anonima si trova in un contesto statico **NON** può avere membri statici, fatta eccezione per le variabili costanti (che sono tipi primitivi final o stringhe inizializzate a espressioni costanti).
Uno svantaggio della classe anonima è l'impossibilità di fare instanceof, non può implementare più interfacce (può estenderne una al massimo, il motivo di questa scelta è puramente sintattica) e non può implementare un'interfaccia ed estendere una classe nello stesso momento.

L'ultima classe è la classe locale. La classe locale è identica alla classe anonima con la differenza che ha un nome e quindi può essere utilizzata in più punti del codice. 