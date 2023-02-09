import java.util.*;
/**
 * OVERVIEW: {@code Elemento} è una classe concreta che modella un elemento della tavola periodica ({@link TavolaPeriodica}).
 * Le istanze di questa classe sono immutabili. 
 * Due elementi si dicono uguali se hanno lo stesso nome.
 */

public class Elemento {
    private final String nome;
    private final String simbolo;
    private final Peso peso;
    private final int numeroAtomico;

    //Ho scelto di  caratterizzare l'elemento con il suo numero atomico. Assumo che il modo in cui vengano inseriti sia sempre l'ordine
    //giusto e che quindi , esempio, l'idrogeno avrà sempre numero atomico 1.

    /*
     * ABS FUN: AF(nome,simbolo,peso,numeroAtomico) = Un elemento è rappresentato dal suo numero atomico, nome, simbolo, peso.
     * 
     * IR: nome != null, così come simbolo e peso. Inoltre simbolo e peso non possono essere vuoti.
     * 
     */

    /**
     * Costruisce un nuovo elemento a partire da un nome,simbolo, peso e numero atomico dati.
     * @param nome Il nome.
     * @param simbolo Il simbolo.
     * @param peso Il peso.
     * @param numeroAtomico Il numero atomico.
     * @throws NullPointerException Se uno dei parametri dati (a parte il numero atomico) è {@code null}. 
     * @throws IllegalArgumentException Se il nome od il simbolo sono vuoti. Se il numero atomico è non positivo.
     */
     public Elemento(String nome,String simbolo, Peso peso, int numeroAtomico){
        Objects.requireNonNull(nome,"Il nome non può essere null");
        Objects.requireNonNull(simbolo,"Il simbolo non può essere null");
        Objects.requireNonNull(peso,"Il peso non può essere null");

        if(nome.length() == 0) throw new IllegalArgumentException("Il nome non può essere vuoto");
        if(simbolo.length() == 0) throw new IllegalArgumentException("Il simbolo non può essere vuoto");
        if(numeroAtomico <= 0) throw new IllegalArgumentException("Il numero atomico deve essere positivo");

        this.nome = nome;
        this.simbolo = simbolo; 
        this.peso = peso;
        this.numeroAtomico = numeroAtomico;
    }

     @Override
     public String toString() {
        return Integer.valueOf(numeroAtomico) + " " + nome + " " + simbolo + " "+ peso;
     }


    // Non lì commento, sono dei getters.
     public String getNome(){return this.nome;}
     public Peso getPeso(){return this.peso;}
     public int getNumeroAtomico(){return this.numeroAtomico;}
     public String getSimbolo(){return this.simbolo;}

     @Override
     public boolean equals(Object obj) {
         Objects.requireNonNull(obj,"L'oggetto dato non può essere null");
         if(!(obj instanceof Elemento)) return false;
         
         Elemento o = (Elemento)obj;
         if(o.nome.equals(this.nome)) return true;
         return false;
     }
}
