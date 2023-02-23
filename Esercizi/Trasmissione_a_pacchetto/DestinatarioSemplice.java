import java.util.*;
import java.nio.charset.*;

/**
 * OVERVIEW: {@code DestinatarioSemplice} è una classe concreta che implementa i comportamenti di {@code Destinatario} 
 * e modella un destinatario che riceve pacchetti da un canale di comunicazione e li riceve in modo ordinato.
 * Le istanze di questa classe sono mutabili.
 * */

public class DestinatarioSemplice implements Destinatario{
        private  List<Pacchetto> pacchetti;
        private final Parametri param;

        /*
            ABS FUN: AF(pacchetti,param) = Un oggetto Destinatario è rappresentato dai parametri che lo caratterizzano e dai pacchetti che ha ricevuto.

            IR: pacchetti != null, elementi di pacchetti != null, param != null. Il pacchetto in posizione i-esima di pacchetti deve avere attributo sequenza pari ad i+1.

        */

        /**
         * Costruisce un DestinatarioSemplice a partire dai parametri dati.
         * @param param I parametri.
         * @throws NullPointerException Se param è {@code null}.
         * */
        public DestinatarioSemplice(Parametri param){
            Objects.requireNonNull(param,"L'oggetto parametri non può essere nullo");
            pacchetti = new ArrayList<>();
            this.param = param;
        }

        //getter
        @Override
        public Parametri parametri(){return param;}

        @Override
        public boolean ricevi(Pacchetto pacchetto){
           if(pacchetto != null){
            pacchetti.add(pacchetto);
           }else{
            List<Byte> byteMessaggio = new ArrayList<>();
            for(Pacchetto p : pacchetti){
                Byte[] temp = p.getByte();
                for(Byte b : temp){
                    byteMessaggio.add(b);
                }
            }
            String res = Decoder.decodifica((Byte[])byteMessaggio.toArray(),param.getCharset());
            System.out.println(res);
            pacchetti = new ArrayList<>();
           }
           return true;
        }
}

