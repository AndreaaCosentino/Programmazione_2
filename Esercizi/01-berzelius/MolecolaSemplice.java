import java.util.*;
/**
 * OVERVIEW: {@code MolecolaSemplice} è una classe concreta che implementa i comportamenti descritti da Molecola e modella una molecola semplice. 
 * Si dice molecola semplice una molecola formata da un solo tipo di atomo. 
 * Le istanze di questa classe sono immutaibli. 
 */
public class MolecolaSemplice implements Molecola{
    private final Elemento el;
    private final int i;

    /*
     * ABS FUN: AF(el,i) = Una molecola semplice è rappresentata dalla molecola da cui è formata e dalla sua molteplicità.
     * 
     * IR: el deve essere diverso da null e i deve essere positivo.
     */
    
    /**
     * Costruisce una molecola semplice a partire da un elemento dato e dalla sua molteplicità, anche questa data.
     * @param el L'elemento.
     * @param i La molteplicità. 
     * @throws IllegalArgumentException Se la molteplicità è non positiva. 
     * @throws NullPointerException Se l'elemento o la molteplcità data è {@code null}.
     */
    public MolecolaSemplice(Elemento el, Integer i){
        Objects.requireNonNull(el,"L'elemento dato non può essere null");
        Objects.requireNonNull(i,"La molteplcità data non può essere null");
        if(i <= 0) throw new IllegalArgumentException("La molteplicità data deve essere positiva");
        this.el = el;
        this.i = i;
    }
    @Override
    public String formula() {
        StringBuilder sb = new StringBuilder(el.getNome());
        if(i > 1) sb.append(" " + Integer.valueOf(i)+ " ");
        return sb.toString();
    }

    public Elemento getElemento(){
        return el;
    }
    @Override
    public Peso peso() {
       return el.getPeso();
    }

    @Override
    public Molecola aggregaMolecola(Molecola other) {
        Objects.requireNonNull(other,"La molecola data non può essere null");
        if(other instanceof MolecolaSemplice){
            MolecolaSemplice e = (MolecolaSemplice) other;
            if(e.el.equals(this.el)){
                return new MolecolaSemplice(el, i+e.i);
            }else{
                return new MolecolaComposta(List.of(this,e));
            }
        }else{
            MolecolaComposta e = (MolecolaComposta) other;
            return e.aggregaMolecola(this);
        }
    }

    
}
