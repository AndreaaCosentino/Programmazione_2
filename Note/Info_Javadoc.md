## Come documentare il codice tramite utilizzo di Javadoc

La documentazione del codice, ovvero il fornire una specificazione, è molto importante. Questa, la documentazione, è parte fondamentale del processo di astrazione (*Astrazione procedurale*). Infatti, ci permette di darle (all'astrazione) un senso che, altrimenti, risulterebbe troppo vaga.


### Modifies:
Sono le modifiche apportate da un certo metodo. Vengono elencati nella descrizione sovrastante le tag o nella tag stessa del parametro.
### Requires:
Ciò che un metodo richiede in input affinchè possa essere eseguito correttamente. I requires sono elencati per parametro, come corollario della descrizione d'ognuno d'essi nel tag *@param*. 
### Effects:
Gli effetti che provoca l'esecuzione di un metodo. Ad esempio, la scrittura a video. Gli effects sono indicati tramite l'utilizzo del tag *@return* , ma anche nella stessa sezione dei modifies.

#### Per "compilare" javadoc ed ottenere la pagina formattata utilizzare *javadoc nome.java*