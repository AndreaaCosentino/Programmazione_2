***BIBLIOTECA***

**La traccia**

Se siete degli studenti vi sarà sicuramente capitato di avere, tra una lezione e l'altra, del tempo libero. Se capita raramente e questo tempo è quantitativamente poco è probabile che attenderete la prossima lezione senza occupare il tempo in modo produttivo. Ma se capita di frequente, potrà esservi venuta l'idea di occupare il tempo studiando. 
Quale posto migliore per studiare della biblioteca? Molto probabilmente farete affidamento alla biblioteca dell'università, che vi permette di prenotare a vostro nome un posto e di prendere in prestito dei libri.

Scopo del progetto è modellare le entità coinvolte in questa attività.

**La biblioteca**

Assumiamo, per semplicità, che tutte le biblioteche appartengano ad un ateneo. Ogni biblioteca è determinata dal suo nome,dalla sua capacità in termini di posti e dai libri che contiene.
La biblioteca deve poter assegnare i propri posti agli utenti della biblioteca ( in questo agli studenti ), senza però assegnare un posto già assegnato ad un altro studente. Solamente gli studenti della stessa università della biblioteca possono prenotare il posto.
Inoltre è possibile prendere in prestito i libri contenuti nella collezione della biblioteca (questo solo se ci sia una copia disponibile ). I libri possono essere prestati al massimo per un mese.


**Lo studente**

Uno studente può prenotare solamente un posto, in una fascia oraria compresa tra le 9 di mattina e le 18. Lo studente può scegliere per quante ore desideri avere il posto. Qualora lo studente finisca prima di usufruire della biblioteca, può decidere di rilasciare il posto e di rimetterlo a disposizione.


**La classe di test**

Nella classe di test si vuole definire tutte le biblioteche, con la loro capienza ed i libri che contengono. Un esempio di inserimento di una biblioteca è il seguente:
```
BIBLIOTECA BIFC
Università: Unimi
Capienza: 300
Libri:
1- "Architettura degli elaboratori" - Nunzio Borghese, N° 5 copie.
2- "Program Development in Java" - Barbara Liskov, N° 1 copia.
3- "Effective Java" - Joshua Block, N° 3 copie.

```
Per fare una prenotazione, deve essere possibile aggiungere degli studenti:

```
STUDENTE
Nome: Andrea
Cognome: Cosentino
Matricola: 213871
Ateneo: Uniba
```

Deve essere possibile anche effettuare un prestito:
```
PRESTITO
Studente: Andrea Cosentino
Matricola: 278102
Biblioteca: BICF
```
E analogamente deve essere possibile effettuare una prenotazione:
```
PRENOTAZIONE
Studente: Andrea Cosentino
Matricola: 869312
Biblioteca: BICF
FASCIA ORARIA: 9:00 - 17:30
```

Per ogni biblioteca stampare i posti occupati e i prestiti effettuati.

