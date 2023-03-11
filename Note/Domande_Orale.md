### Domande orale

Domande che potrebbero essere chieste all'orale di *Programmazione II* e possibili risposte.

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
Una procedura si dice sottodeterminata se alcuni dettagli di che cosa la procedura faccia sono lasciati indefiniti. Ciò vuol dire che per alcuni input la procedura ha un set di output possibili, e ne può produrre uno qualunque. 
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

Una procedura sottodeterminate è utile, per esempio, quando si vuole definire un metodo per il supertipo che però lascia ai sottotipi libertà di implementazione.
Per esempio, se abbiamo questo supertipo:
```java
public class Set<E>{
    ... 
    /**
     * Restituisce un iteratore sugli elementi di this.
     * @return Un iteratore sugli elementi di this.
     * */
    public Iterator<E> elements(){
    }
}
```
In che modo l'iteratore restituirà gli elementi all'interno del set? Non lo sappiamo, infatti questa procedura è sottodeterminata, ergo esistono degli input(in questo caso non ci sono input) per cui esistono differenti possibili output. Potremmo decidere di restituirli come sono stati inseriti, oppure utilizzando un ordine , etc... . Allora l'idea è che i sottotipi di *Set<E>*, per esempio *SortedSet<E>*, possono fare un *override* di questo metodo e rispecificarlo, andando a renderlo deterministico.

---

##### Effetti collaterali benevoli

Gli effetti collaterali benevoli sono delle modifiche non visibili al di fuori dell'implementazione. Gli effetti collaterali benevoli si riferiscono ad un metodo. Sono collaterali perchè non è ciò per cui il metodo è realizzato. Il metodo può esibire un comportamento, il suo comportamento proprio descritto nella specifica, e oltre a quel comportamento modificare l'implementazione. Nelle specifiche scriviamo i comportamenti che hanno i metodi, ma gli effetti collaterali benevoli non fanno parte del comportamento e vanno quindi esclusi. 
Sono benevoli **NON** perchè portano un vantaggio, ma bensì perchè non cambiano la rappresentazione astratta dell'oggetto. Per convincersi dell'assurdità della prima definizione basti pensare che, se considerata valida la definizione per cui un effetto è benevolo perchè porta un vantaggio, per definire un effetto collaterale come benevolo dovremmo ragionare sulla sua effettiva convenienza, aprendo un dibattito che non ha una risposta definitiva.
Gli effetti collaterali benevoli hanno senso di esistere in virtù della definizione della funzione d'astrazione (per la risposta a che cos'è la funzione d'astrazione si guardi la relativa domanda). Sicché la funzione d'astrazione è many-to-one (molti ad uno, o  biettiva) esistono più implementazioni concrete che corrispondo allo stesso oggetto astratto. E' evidente che se implementiamo delle astrazioni dati per cui la funzione è uno ad uno gli effetti collaterali non possono esistere. 
A cosa servono gli effetti collaterali benevoli? Servono ad ottenere una rappresentazione più efficiente, quindi più facile da utilizzare. Questa non è la definizione di effetti collaterali benevoli, pensì il motivo per cui li adoperiamo e come li adoperiamo. Si noti che è effetto collaterale benevolo anche la modifica della rappresentazione interna che peggiora, da un punto di vista dell'efficienza, la stessa.
Un esempio classico è quello dei numeri irrazionali espressi come frazioni. 
Il numero $\frac{50}{100}$ rappresenta a livello astratto $0.5$ . Se internamente decidessimo di cambiare la rappresentazione in $\frac{1}{2}$ a livello astratto non ci sarebbero ripercussioni. Quindi, un metodo come
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
A livello formale possiamo definire la funzione d'astrazione come $AF: C \to A$, dove $C$, il dominio, rappresenta gli stati concreti ed $A$, il codominio (detto range in inglese), rappresenta l'oggetto astratto che rappresenta un dato stato concreto. 
La funzione d'astrazione può essere una funzione many-to-one, ma non è vero che lo sia sempre. E' many-to-one quando ci sono più possibili rappresentazioni concrete di un oggetto astratto. 
Un esempio di una funzione d'astrazione che non è biettiva è quella relativa all'astrazione dati introdotta come wrapper per l'int, ovvero la classe *Integer*. Dentro la classe Integer è presente un campo che è l'intero stesso che rappresenta. Un Integer non può rappresenare un intero in due diversi modi. Quindi la sua funzione d'astrazione è one-to-one, ovvero una funzione identità.
L'invariante di rappresentazione è un predicato  $J:C\to boolean$ e si riferisce ad una classe. Il dominio è l'insieme degli oggetti che la classe permette di creare, ma non tutti gli oggetti sono legali. Per oggetto legale si intende un oggetto che è una rappresentazione legittima di un oggetto astratto. Ad ogni oggetto legale, l'invariante associa il valore  *true*, ad ogni oggetto illegale associa *false*.
L'invariante di rappresentazione raccoglie tutte le caratteristiche che ha un oggetto legale. Se un oggetto non soddisfa l'invariante di rappresentazione, allora questo è illegale e non è una rappresentazione legittima dell'oggetto astratto che l'astrazione dati si pone di rappresentare.
Se tutti gli oggetti che possono essere creati da una classe sono legali, allora l'IR è semplicemente *true*, cioè per ogni oggetto restituisce *true*.
L'invariante di rappresentazione si chiama invariante perchè non varia, cioè è sempre vero. In realtà l'invariante può essere violato temporaneamente, l'importante è che prima della chiamata di un metodo ed alla fine valga.
E' importante sottolineare che tra l'invariante e la funzione d'astrazione esiste un legame. Non ha senso andare a definire la funzione d'astrazione per oggetti che non sono legali, cioè che non rispettano l'IR.

---
##### Le astrazioni
Un'astrazione è una mappa (i.e. una funzione) molti ad uno. Un'astrazione permette di astrarre da dettagli irrilevanti, di modo di descrivere un problema solamente con quelli che sono rilevanti. L'astrazione per *parametrizzazione* permette di astrarre dall'identità del dato utilizzato. Non importa quale sia il valore del dato (che rappresenta l'identità del dato stesso, un dato è definito dal suo valore) ma piuttosto è rilevante il tipo dei dati ed altresì il numero. Definiamo l'astrazione in termini dei *parametri*, detti *parametri formali*, e il dato effettivo è legato a questi parametri, che ci permettono di utilizzarlo. Per fare un esempio, il seguente segmento di "codice"
```java
    3+4;
```
Permette di fare la somma tra il numero 3 ed il numero 4. Senza l'astrazione per parametrizzazione, per ogni somma che desideriamo effettuare è necessario riscrivere il codice
```java
    8+15;
```
Con l'astrazione per parametrizzazione, invece, possiamo usare dei parametri
```java
    x+y;
```
il cui valore non è precisato.
Un'altra astrazione è l'astrazione per *specificazione*, che permette di concentrarsi sul comportamento di un'astrazione (*procedurale* e *dati*) e non sull'effettiva implementazione. 
Altresì detto, è di interesse ciò che viene fatto e non come viene fatto. L'astrazione per specificazione porta due benefici, il primo la *località*. Per località si intende che l'implementazione di un'astrazione è leggibile, o scrivibile, in termini della specificazione e basta. Non c'è necessità di andare a consultare altre astrazioni. Per l'astrazione dati questo implica che tutte le modifiche apportate allo stato devono essere fatta all'interno dell'astrazione e non fuori, pena la perdita della località. La seconda proprietà/beneficio è la modificabilità che permette di modificare l'implementazione di un'astrazione senza andare ad impattare sul resto del programma. Nell'astrazione dati questo ha un'implicazione più forte della località. Infatti, non solo lo stato deve essere modificato strettamente all'interno dell'astrazione, ma lo stato stesso non può essere visibile all'esterno (information hiding, incapsulamento). Se così non  fosse, cambiamenti allo stato non sarebbero possibili se non andando a mettere a rischio il funzionamento del programma.
L'astrazione procedurale permette di definire delle operazioni. In *java* le operazioni sono definite tramite dei metodi. 
L'astrazione dati permette di definire degli oggetti astratti in modo concreto, andando a "dimenticarsi" dei particolari irrelevanti. Questo consente di trattare l'oggetto come la rappresentazione concreta dell'oggetto astratto.

---
##### Polimorfismo
Il polimorfismo generalizza le astrazioni in modo da poterle farle funzionare su diversi tipi. Il polimorfismo **non** è un'astrazione. L'astrazione è una  generalizzazione concettuale ( generalizzo il concetto di animale, di veicolo, etc..) mentre il polimorfismo è una generalizzazione comportamentale. In questo modo non si deve ridefinire un'astrazione solamente perchè utilizza un tipo diverso.
Un'astrazione dati potrebbe essere polimorfa rispetto ai tipi degli elementi che i suoi oggetti contengono. Un esempio potrebbe essere l'astrazione *List*. Una lista potrebbe contenere animali, libri, persone etc... . 
Un metodo potrebbe essere polimorfo rispetto ai tipi degli argomenti che riceve. Per esempio, riprendendo la lista di prima, potremmo poter rimuovere un elemento di quella lista, che passiamo per argomento ad un metodo *remove*. Il tipo del parametro dipende dal tipo degli elementi della lista. 

In java possiamo ottenere il polimorfismo in due modi:
- Attraverso la gerarchia dei tipi
- Usando i generici (In questo caso si parla di polimorfismo parametrizzato)

Nel primo caso gli argomenti interessati sono dichiarati come appartenenti al supertipo, in questo modo il tipo concreto può essere di uno dei sottotipi del supertipo. Solitamente come supertipo si sceglie il tipo *Object*, ma a volte questo può non essere una scelta soddisfacente. Infatti, *Object* potrebbe non avere tutti i metodi necessari. In questo caso, allora, si definisce un nuovo tipo da utilizzare da supertipo (solitamente un'interfaccia, esempio Comparable), a cui si provvedono tutti i metodi extra necessari.
Il tipo degli elementi restituiti dai metodi che restituiscono un oggetto della collezione è di tipo *Object*. Questo obbliga il codice che li utilizza ad effettuare un casting esplicito.
Quando, per esempio, inseriamo elementi in una collezione nel contesto dell'astrazione polimorfa il compilatore non fa alcun tipo di controllo sul tipo degli elementi inseriti. Se in una lista vogliamo inserire degli Integer ma poi inseriamo anche un Gatto, il compilatore non solleverà alcuna eccezione.
Nel caso in cui *Object* non fosse abbastanza (perchè servono metodi in più) si utilizzano delle interfacce per definire i metodi che i tipi degli elementi devono supportare.
Ci sono tre approcci:
- Element subtype approach: Tutti i potenziali elementi devono essere sottotipi dell'interfaccia. Questo però richiede di definire il supertipo prima ancora che i sottotipi siano ancora definiti. Ciò non è sempre possibile.
- Related subtype approach: Definisco un'interfaccia di cui gli elementi **non** sono sottotipi. Si definisce un sottotipo dell'interfaccia per ogni potenziale elemento. Questo mi permette di definire prima tutti gli elementi e poi, successivamente, di associare ad ogni elemento un sottotipo dell'interfaccia (che è una classe concreta).
- Combinazione degli approcchi prececedenti. Ci sono dei tipi che sono direttamente sottotipi dell'interfaccia altri che invece hanno un interfaccia associata. Permette di scegliere all'utente la soluzione migliore per ogni elemento.

Il secondo modo di ottenere il polimorfismo in Java è attraverso il polimorfismo parametrizzato, ovvero con i generici. Un *generico* è una classe od interfaccia che nella sua dichiarazione ha uno o più tipi parametro. In questo caso si dice che la classe/interfaccia è una classe/interfaccia generica. I tipi parametro all'interno della dichiarazione del generico si dicono *tipo elemento*.  Ogni generico definisce un set di *tipi parametrizzati*. Un tipo parametrizzato è formato dalla classe/interfaccia seguita dalle parentesi angolari (< >) e i tipi attuali dei parametri (e.g. List<Integer>). Per ogni generico è definito anche un *raw type* . Il raw type si utilizza per due ragioni. La prima è per retro-compatibilità (migration compatibility), ovvero per permettere a tutti quei programmi che esistevano prima dell'introduzione dei generici di continuare a funzionare. Il secondo motivo è per l'utilizzo di instanceof, ma su questo punto ci torno successivamente. Al di fuori dell'ultima situazione descritta, i raw type non devono **MAI** essere utilizzati. Il tipo parametrizzato ci garantisce (a compile-time) che le operazioni fatte siano corrette a livello di tipo. Questo perchè, quando usiamo il tipo parametrizzato, il compilatore inserisce dei cast invisibili. 
Sicché i generici dovevano, e devono, permette la retro compatibilità si è deciso di implementarli usando *erasure* (cancellazione). Questo vuol dire, che a runtime il programma non sa niente del tipo del generico. Ergo, righe di codice come questa
```java
    if( o instanceof List<Integer>)
```
non andranno a buon fine. Per questo, come detto prima, si utilizzano i raw type. La riga di codice
```java
    if( o instanceof List)
```
è legale. Attenzione al fatto che *List* non ha lo stesso significato di *List<Object>*, anche se apparentemente sembrano uguali. *List* è supertipo di tutti i tipi parametrizzati di classe List. *List<Object>* invece no. E' sbagliato dire che *List<Object>* sia supertipo di *List<String>*. Questa seconda proprietà dei generici è chiamata **invarianza**. Si noti come gli array sono all'estremo opposto: essi sono, infatti, covarianti e reificati (covariant e reified). 
Java è un linguaggio fortemente tipizziato, quinci si ricava che dovrà esistere un modo per controllare il tipo di un generico, affinchè si ottenga un programma type safe. Con i vettori, che sono reificati (abbiamo una rappresentazione esplicita del tipo), il controllo viene fatto a run-time (per quanto riguarda il tipo concreto, il tipo apparente viene già controllato a compile-time). 
Invece, nei generici, il compilatore aggiunge dei casting espliciti e controlla che quei casting siano corretti, e tutto ciò viene fatto a compile-time.


---
##### Le eccezioni

Le eccezioni sono un meccanismo che permette di segnalare all'utente delle situazioni che richiedono attenzione. Senza questo meccanismo il programma non ha modo di segnalare all'utente che l'esecuzione di un certo metodo è fallita per qualche motivo. Precedentemente all'introduzione dell'eccezioni c'erano due modi per cercare di comunicare all'utente che qualcosa fosse andato storto: il primo, il più drastico ma forse più sicuro, è l'interruzione del programma. Questo, però, va contro l'idea di "graceful degradation". Se un aereo ha problemi con il carrello d'atterraggio, non vogliamo che l'aereo si spenga. Invece, avremmo il desiderio che ci venga notificato questo problema e che ci dia la possibilità di trovare una soluzione o, almeno, di continuare anche con il carrello fuori uso. 
Allora, ancor prima dell'eccezioni, può essere che un metodo potesse restituire un valore determinato per gli errori. Ciò presenta altresì dei problemi:
- Se i valori restituiti dal metodo sono tutti valori possibili, cioè appartengono tutti al range della funzione, che cosa si fa?
- Qualora esistessero valori fuori dal range, come ci assicuriamo che l'utente si accorga dell'errore? L'utente potrebbe prendere per buono quel valore, o non accorgersene affatto, o accorgesene successivamente senza capire il perchè il programma sia morto.
Il meccanismo delle eccezioni permette di:
- Segnalare all'utente che c'è stato un problema. L'utente non può ignorare l'eccezione e non può non accorgersene.
- Permettere all'utente di prendere delle misure per assicurarsi che il programma continui a funzionare, almeno in una forma parziale.
In *java* le eccezioni sono di due tipi: checked ed unchecked.
L'eccezioni unchecked vengono utilizzate per segnalare un errore del programmatore nell'invocazione del metodo, o che si suppone il programmatore potesse prevenire. In un metodo che prende come input un numero intero e ne fa la radice quadrata, passare un numero negativo è sicuramente un errore del programmatore. Il metodo, quindi, può lanciare un'eccezione (in questo caso potrebbe decidere di usare una *IllegalArgumentException*). Tutte le eccezioni unchecked sono figlie di *RunTimeException*. Le eccezioni di questo tipo non devono essere obbligatoriamente dichiarate nell'intestazione di un metodo (è buona pratica, però, documentarle).
Le eccezioni checked, figlie di *Exception* tranne *IllegalArgumentException* (sì è casinistico!), sono delle eccezioni che obbligano il programmatore a prendersene cura. Solitamente vengono lanciate per situazioni non nel controllo dell'utente. Per esempio, l'apertura di un file potrebbe generare degli errori (il file non esiste, è stato spostato, etc...).
L'utente è quindi chiamato a prendersi cura delle eccezioni checked. Può farlo in due modi:
- Fare "galleggiare" l'eccezione, dichiarando l'eccezione nell'intestazione.
- "Mascherare" l'eccezione attraverso l'utilizzo di un try-catch. Nella parte del catch il programmatore può decidere diverse soluzioni. Può lanciare altre eccezioni (ma a quel punto se checked vanno aggiunte all'intestazione del metodo) oppure svolegere delle operazioni in risposta all'eccezione.
Alcune volte mascherare l'eccezione può essere utile per lanciare un'eccezione che abbia senso.
Per esempio, sia *Statistica* una classe che prende dei numeri interi e calcola la media. *Statistica* è mutabile, e permette di aggiungere numeri interi e di rimuoverli. Immaginiamo che statistica abbia un metodo *remove(int I)* che permette di rimuovere l'i-esima osservazione. Se *Statistica* mantiene le osservazioni con un vettore, e l'indice passato al metodo *remove*è fuori dal bound, che cosa deve fare il metodo? Lanciare un'eccezione. Ma se lanciasse l'eccezione IndexOutOfBound, che informazioni dà all'utente? E' meglio che mascheri l'eccezione e ne lanci una che abbia senso per il livello di astrazione considerato.

---
##### Tipi di supertipo

Nella gerarchia di tipi ci sono *tre* tipi di supertipo.

Alcuni supertipi sono detti **incompleti** e servono a stabilire dei vincoli nel comportamento dei sottotipi ma le loro specifiche sono talmente poco vincolanti che i sottotipi possono  essere scritti ignorando la specifica del supertipo. Un esempio è la classe supertipo *Collections*. La classe astratta *Collections* permette di definire i nomi delle operazioni (add, isEmpty, size, etc...) ma non va a porre vincoli stringenti su cosa esattamente facciano questi metodi.

Altri sono detti supertipi **completi** e permettono di scrivere sottotipi utilizzando la specifica del supertipo e definiscono un'astrazione dati completa, quindi i sottotipi possono essere scritti utilizzando il codice del supertipo.

Infine, esistono anche gli **snippets**, questi sono comunemente le interfacce. Uno snippet è un supertipo che provvede dei metodi, ma non abbastanza per configurarsi come un'intera astrazione dati. I sottotipi possono essere scritti sfruttando la specifica del supertipo.

Riuscire a preservare il principio di sostituzione è facile quando i supertipi sono incompleti o sono degli snippets. Questo perchè, solitamenti, questi non hanno alcune proprietà che vanno rispettate, proprio perchè non definiscono un'astrazione dati completa.

---
##### Principio di sostituzione

Il principi di sostituzione è il principio fondante della gerarchia di tipi. Questo principio assicura che i sottotipi possano essere utilizzati al posto di un supertipo. Si può sostituire un supertipo con un sottotipo. Quando si costruiscono gerarchie di tipi vanno seguite tre regole:

- Regola della segnatura (signature rule): I sottotipi devono implementare gli stessi metodi del supertipo e devono avere una segnatura che sia *compatibile*. Questa regola ci assicura che qualsiasi chiamata che sia corretta dal punto di vista del tipo (type correct) per il supertipo lo è anche per il sottotipo. Questa regola è fatta valere dal compilatore stesso, che non ci permette di fare altrimenti. L'unica differenza è che il sottitipo può lanciare meno eccezioni. Java vede la compatibilità in modo molto stretto. Per fare un esempio, il sottotipo non può restituire un sottotipo del tipo restituito dal supertipo.  Ovvero, se il supertipo avesse la seguente segnatura:
```java
    public Object concatenate(String x, String y){
        return x+y;
    }
```
Il sottotipo non potrebbe fare quanto segue:
```java
    @Override public String concatenate(String x, String y){
        return x+y;
    }
```
Nonostante String sia un sottotipo di Object e, per il principio di sostituzione, non ci sia in realtà alcun problema.

- Regola dei metodi (methods rule): Le chiamate ai metodi comuni al supertipo e al sottotipo devono, in quest'ultimo, comportarsi come le chiamate al supertipo per quei metodi.
In altre parole, se il sottotipo fa un *override* di un metodo, questo deve comportarsi come si comporta nel supertipo. Un metodo deve "ragionare" rispettando le specifiche date al supertipo. Potremmo decidere di rispecificare un metodo ma dobbiamo fare attenzione, se un metodo viene rispecificato si rischia di non rispettare più i vincoli dati dal supertipo.
Per un esempio di rispecificazione si guardi la domanda sula procedura sottodeterminata.
In modo formale, in che modo può la specifica di un metodo del sottotipo variare rispetto a quella del supertipo? Il sottotipo può indebolire le precondizioni e rafforzare le post-condizioni. Per pre-condizioni s'intendono tutte le condizioni che devono essere garantite di valere dal chiamante al momento della chiamata stessa. Per post-condizioni si intendono le condizioni che sono garantite valere subito dopo la fine della chiamata (assumendo che le precondizioni valessero).
Indebolire le precondizioni significa che il sottotipo si aspetta meno dal chiamante. Questo non va contro all'idea del principio di sostituzione. Se il sottotipo si aspetta meno del supertipo, vuol dire che se è chiamato al posto del supertipo non genera alcun problema. 
Rafforzare le postcondizioni vuol dire che il sottotipo deve comunque rispettare gli effetti del supertipo ( non può cambiarli) ma può aggiungerne di nuovi, sempre che questi non contrastino con quelli definiti dal supertipo.
Per questa regola non è sempre vero che un metodo del sottotipo può lanciare meno eccezioni. Infatti, deve mantenere lo stesso comportamento.
Questa regola non può essere controllata dal compilatore, dato che è una regola semantica, che si occupa del significato (comportamento) dei metodi, e non può essere controllata. E' compito del programmatore assicurarsi che sia rispettata.

- Regola delle proprietà (properties rule): Questa regola non si concentra sulla singola chiamata al metodo, come fa la regola dei metodi, ma sulle proprietà degli oggetti. Un sottotipo rispetta la regola delle proprietà se preserva ogni proprietà del supertipo. Sono incluse le proprietà di evoluzione (cioè come l'oggetto evolve/varia nel tempo). Un esempio è la proprietà di immutabilità. Questo non vuol dire che se il supertipo è immutabile allora il sottotipo non può essere mutabile, ma vuol dire che , qualora il sottotipo fosse mutabile, le mutazioni di quest'ultimo non devono essere visibili al supertipo. Per esempio, dato un punto 2d immutabile (x e y sono final) e un punto 3d che lo estende con z mutabile, questa gerarchia rispetta il LSP. Questo perchè le coordinate x ed  y sono immutabili anche nel sottotipo, ed il supertipo non ha accesso alla parte mutabile del sottotipo, perchè non ha la concezione di una terza coordinata.

---
##### Dispatching

TODO 

---
##### Note sparse
- I record di cui parla la Liskov **non** sono i record introdotti in Java 14. Per la Liskov un record è una classe package protected che colleziona dei campi e a cui il codice del pacchetto ha accesso diretto, con possibilità di modifica. Insomma, è un modo per compattare delle informazioni.
- Come già detto nella relativa domanda, non fatevi confondore dal significato di benevolent quando si parla di benevolent side effect! Benevolent vuol dire che è un effetto che non cambia l'oggetto astratto rappresentato, non vuol dire che offre dei vantaggi!
- Il mascheramento dell'eccezioni non è per proteggere la rappresentazione. Dopo che l'utente sa che noi abbiamo implementato qualcosa con una lista, che cosa se ne fa? Nulla, non può in alcun modo crearci dei problemi. E' importante, invece, per dare un significato comprensibile all'utente.
