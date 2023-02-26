### Domande orale

Qui sotto sono riportate le domande fatte agli orali di *programmazione II* e le risposte.
Le risposte non devono essere imparate a memoria, servono solo come aiuto a comprendere i diversi aspetti di *Java*.

E' bene usare un linguaggio accurato durante l'esame orale. Non rispondete subito, prendetevi del tempo per organizzare le idee e preparare il discorso. Ricordatevi sempre di supportare la vostra narrazione con degli esempi, vanno bene anche quelli fatti a lezione/dal libro ma sono molto apprezzati esempi propri.

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

---
##### Procedura sottodeterminata
Una procedura si dice sottodeterminata se alcuni dettagli di che cosa la procedura fa sono lasciati indefiniti. Ciò vuol dire che per alcuni input la procedura ha un set di output possibili, e ne può produrre uno qualunque. 
Per esempio, supponiamo di avere una procedura che ricerca un valore all'interno di un array e restituisce la posizione di quel valore, se presente.
Cosa succede se ci sono più posizioni dell'array che contengono il valore dato? Qual è la posizione che viene restituita?. Si prenda questo frammento di codice come esempio:
```java
    /**
    * Restituisce la posizione del valore dato nell'array dato.
    * @param v Il vettore.
    * @param valore Il valore.
    * @return La posizione del valore dato nell'array dato.
    * @throws NullPointerException Se il vettore è {@code null}
    */
    public int cercaElemento(int[] v, int valore){
        ...
    }
```
Questo frammento di codice è un esempio di specifica per la procedura che cerca un elemento all'interno di un vettore.
Se l'input è il seguente
```
V = [0,3,4,1,5,9], valore = 3
```
senza dubbio la procedura restituirà 1, infatti 3 si trova in posizione 1 dell'array V. Ma cosa succede se invece l'input è il seguente:
```
V = [1,3,3,3,5]
```
La procedura restituisce 1,2 o 3? Questo non è un problema di lana caprina, o meglio, forse lo è nel caso di numeri ma non nel caso di oggetti. Restituire un oggetto invece che un altro può essere influente.
Non è per forza detto che una procedura sottodeterminata sia errata, ma potrebbe esserlo.

Di solito un'astrazione sottoderminata ha un'implementazione deterministica, ovvero se chiamata su due input uguali restituisce sempre lo stesso output. Nel caso della ricerca in un vettore potremmo aspettarci che restituica sempre il primo elemento che incontra nella ricerca, ovvero quello con indice più basso. 
Un'astrazione sottodeterminata potrebbe avere anche un'implementazione non deterministica, questa si ottiene attraverso l'uso di variabili globali, del clock, di variabili statiche etc... .

---

##### Effetti collaterali benevoli

Gli effetti collaterali benevoli sono delle modifiche non visibili al di fuori dell'implementazione. Gli effetti collaterali benevoli si riferiscono ad un metodo. Sono collaterali perchè non è ciò per cui il metodo è realizzato. Il metodo può esibire un comportamento, il suo comportamento proprio descritto nella specifica, e oltre a quel comportamento modificare l'implementazione. Nelle specifiche scriviamo i comportamenti che hanno i metodi, ma gli effetti collaterali benevoli non fanno parte del comportamento e vanno quindi esclusi. 
Sono benevoli **NON** perchè portano un vantaggio, ma bensì perchè non cambiano la rappresentazione astratta dell'oggetto. Per convincersi dell'assurdità della prima definizione basti pensare che, se considerata valida la definizione per cui un effetto è benevolo perchè porta un vantaggio, per definire un effetto collaterale come benevolo dovremmo ragionare sulla sua effettiva convenienza, aprendo un dibattito che non ha una risposta definitiva.
Gli effetti collaterali benevoli hanno senso di esistere in virtù della definizione della funzione d'astrazione (per la risposta a che cos'è la funzione d'astrazione si guardi la relativa domanda). Sicché la funzione d'astrazione è many-to-one (molti ad uno, o  biettiva) esistono più implementazioni concrete che corrispondo allo stesso oggetto astratto. E' evidente che se implementiamo delle astrazioni dati per cui la funzione è uno ad uno gli effetti collaterali non possono esistere. 
A cosa servono gli effetti collaterali benevoli? Servono ad ottenere una rappresentazione più efficiente, quindi più facile da utilizzare. Questa non è la definizione di effetti collaterali benevoli, pensì il motivo per cui li adoperiamo e come li adoperiamo. Si noti che è effetto collaterale benevolo anche la modifica della rappresentazione interna che peggiora, da un punto di vista dell'efficienza, la stessa.
Un esempio classico è quello dei numeri irrazionali espressi come frazioni. 
Il numero $$\frac{50}{100}$$ rappresenta a livello astratto $$0.5$$ . Se internamente decidessimo di cambiare la rappresentazione in $$\frac{1}{2}$$ a livello astratto non ci sarebbero ripercussioni. Quindi, un metodo come
```java
    public void moltiplica(Irrazionale c){
        ...
        reduce(this)
    }
```
Che svolge la moltiplicazione tra *c* e *this* ha come effetto collaterale benevolo la riduzione ai minimi termini del risultato. *reduce* è un metodo che si occupa dalla ridzione ai minimi termini di un irrazionale. Ma anche questo metodo presenta degli effetti collaterali benevoli:
```java
    public void moltiplica(Irrazionale c){
        ...
        this.numeratore *= 100;
        this.denominatore *= 100;
    }
```
Anche se peggiora l'efficienza della rappresentazione concreta è pur sempre un effeto collaterale **benevolo**. 
In sintesi, non fatevi ingannare dal significato di benevolo.

---

##### Funzione d'astrazione e invariante di rappresentazione

La funzione d'astrazione permette di catturare le intenzioni del progettista nello scegliere un'implementazione. E' la prima cosa che decidiamo quando inventiamo una nuova rappresentazione. Ci permette di capire come le variabili di istanza sono legate all'oggetto astratto e come cercano di rappresentarlo a livello concreto.
A livello formale possiamo definire la funzione d'astrazione come $$AF: C \to A$$, dove $$C$$, il dominio, rappresenta gli stati concreti ed $$A$$, il codominio (detto range in inglese), rappresenta l'oggetto astratto che rappresenta un dato stato concreto. 
La funzione d'astrazione può essere una funzione many-to-one, ma non è vero che lo sia sempre. E' many-to-one quando ci sono più possibili rappresentazioni concrete di un oggetto astratto. 
Un esempio di una funzione d'astrazione che non è biettiva è quella relativa all'astrazione dati introdotta come wrapper per l'int, ovvero la classe *Integer*. Dentro la classe Integer è presente un campo che è l'intero stesso che rappresenta. Un Integer non può rappresenare un intero in due diversi modi. Quindi la sua funzione d'astrazione è one-to-one, ovvero una funzione identità.
Un altro esempio è per i record. Quando costruiamo dei record non abbiamo bisogno di specificare la funzione d'astrazione, perchè questa è la funzione identità. 
I record non forniscono alcuna astrazione sulla propria rappresentazione.
```java
public Record Libro(String titolo, String autore,Int numeropagine){
    ....
}
```
La rappresentazione del record è la collezione dei suoi campi, ma così è anche l'oggetto astratto. Quando parliamo di un libro, questo è identificato e rappresentato dal suo titolo, da chi l'ha scritto etc... . 
L'invariante di rappresentazione è un predicato  $$J:C\to boolean$$ e si riferisce ad una classe. Il dominio è l'insieme degli oggetti che la classe permette di creare, ma non tutti gli oggetti sono legali. Per oggetto legale si intende un oggetto che è una rappresentazione legittima di un oggetto astratto. Ad ogni oggetto legale, l'invariante associa il valore  *true*, ad ogni oggetto illegale associa *false*.
L'invariante di rappresentazione raccoglie tutte le caratteristiche che ha un oggetto legale. Se un oggetto non soddisfa l'invariante di rappresentazione, allora questo è illegale e non è una rappresentazione legittima dell'oggetto astratto che l'astrazione dati si pone di rappresentare.
Se tutti gli oggetti che possono essere creati da una classe sono legali, allora l'IR è semplicemente *true*, cioè per ogni oggetto restituisce *true*. Questo è vero, per esempio, nel caso dei record.