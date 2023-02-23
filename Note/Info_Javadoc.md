## Come documentare il codice tramite utilizzo di Javadoc

La documentazione del codice è fondamentale per passare l'esame di [*programmazione II*](https://prog2.di.unimi.it/).

Questo documento cerca di spiegare in che modo la documentazione debba essere fatta in javadoc. 

La documentazione in javadoc **di un metodo** si divide in due macro-parti:
-  Una prima parte dove spieghiamo a parole ed attraverso esempi il comportamento del metodo.
-  Una seconda parte più formale dove attraverso l'uso di tag andiamo a spiegare i parametri, che cosa restituisce il metodo e le varie eccezioni.

Prendiamo per esempio questo segmento di codice:

```java
    public Integer sommaNumeri(Integer i, Integer j){
        Objects.requireNonNull(i);
        Objects.requireNonNull(j);
        return i+j;
    }
```
Nel segmento di codice sovrastante viene fatta una somma tra due Integer (il tipo Integer è l'oggetto wrapper di int ). Una documentazione di questo metodo potrebbe essere:

```
    Questo metodo restituisce la somma tra i due numeri dati.
    @param i Il primo numero.
    @param j Il secondo numero.
    @return La somma tra i due numeri dati.
    @throws NullPointerException Se i o j sono {@code null}.
```

Inizialmente descriviamo che cosa fa il metodo. Questo metodo è banale e quindi anche la descrizione. Per metodi più complessi potrebbe essere utile anche andare a fare degli esempi. 
Un buon motto da tenere a mente quando si scrivono documentazioni è *Dì loro quello che gli stai per dire. Diglielo. Dì loro quello che hai detto loro*.
Le tag utilizzate si spiegano da sole, ma per completezza:
- Il tag @param si utilizza per ogni parametro del metodo, e serve per descriverlo.
- Il tag @return serve per dire che cosa restituisce il metodo. Se il metodo restituisce void allora va omesso.
- Il tag @throws serve a spiegare quali eccezioni vengono lanciate. Ogni eccezione di tipo diverso ha il suo tag @throws. (se avessimo avuto un altra eccezione avremmo avuto sotto , esempio , @throws IllegalArgumentException).

---

Nella documentazione Liskov-style sono presenti tre "categorie" : i modifies, i requires e gli effects. La documentazione in javadoc è più fluida e meno rigida, qua sotto cerco di tradurre ciò che spiega Liskov in javadoc.

### Modifies:
Sono le modifiche apportate da un certo metodo. Vengono elencati nella descrizione sovrastante le tag o nella tag stessa del parametro.
### Requires:
Ciò che un metodo richiede in input affinchè possa essere eseguito correttamente. I requires sono elencati per parametro, come corollario della descrizione d'ognuno d'essi nel tag *@param*. 
### Effects:
Gli effetti che provoca l'esecuzione di un metodo. Ad esempio, la scrittura a video. Gli effects sono indicati tramite l'utilizzo del tag *@return*, del tag *@throws* , ma anche nella stessa sezione dei modifies.

##### Le eccezioni

Dopo il tag *@return* si elencano le eccezioni che il metodo può lanciare o che possiamo prevedere che possa lanciare (anche se non è il metodo che le lancia direttamente).
Le eccezioni sono elencati, per ogni tipo, con il tag *@throws*. Si elencano **tutte** le eccezioni, che siano checked od unchecked.
Le eccezioni che solitamente utilizziamo sono tre:

- IllegalArgumentException
- NullPointerException 
- NoSuchElementException

Generalmente queste tre eccezioni bastano per descrivere il comportamento eccezionale di un metodo.

##### Documentate sempre

Molte volte il motivo per cui non si passa l'esame di programmazione 2 è l'assenza di importanti pezzi della documentazione. La documentazione **deve** descrivere cosa fa il metodo. Se nella documentazione ci sono frasi del tipo
"@throws IllegalArgumentException Se la stringa data non è nel formato corretto"
E non specificate qual è il formato corretto.
Qual è il formato corretto? L'utente non può saperlo.

##### Tecnicità
Per "compilare" javadoc ed ottenere la pagina formattata utilizzare *javadoc nome.java*

![](https://www.konakart.com/wp-content/uploads/2014/11/javadoc.png)