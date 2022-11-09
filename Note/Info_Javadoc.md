## Come documentare il codice tramite utilizzo di Javadoc

La documentazione del codice, ovvero il fornire una specificazione, è molto importante. Questa, la documentazione, è parte fondamentale del processo di astrazione. Infatti, ci permette di dare un senso all'astrazione che, altrimenti, risulterebbe troppo vaga.


### Modifies:
I modifies sono elencati nella descrizione sovrastante le tag.
### Requires:
I requires sono elencati per parametro, come corollario della descrizione d'ognuno d'essi nel tag *@param*. 
### Effects:
Gli effects sono indicati tramite l'utilizzo del tag *@return* , ma anche nella stessa sezione dei modifies.

#### Per "compilare" javadoc ed ottenere la pagina formattata utilizzare *javadoc nome.java*