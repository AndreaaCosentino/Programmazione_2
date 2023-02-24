/**
 * OVERVIEW: {@code TrasmissionePeriodica} è una classe concreta che estende {@code Trasmissione} e modella una trasmissione periodica, ovvero una trasmissione che si ripete
 * per un certo numero di volte nella betagiornata ad una certa distanza di tempo.
 * 
 * Le istanze di questa classe sono immutabili.
 * */

import java.util.*;

public class TrasmissionePeriodica extends Trasmissione{
        private final List<Fascia> fasce; 

        /*
            ABS FUN: AF(fasce,super.titolo) = La trasmissione periodica è rappresentata dalle fasce in cui va in onda e dal suo titolo.

            IR: fasce diverso da null, elementi di fasce diversi da null e le fasce devono essere tutte nello stesso giorno. Titolo diverso da null e non vuoto.
            Le fasce non si devono sovrapporre tra loro.
        */


        /**
         * Costruisce una trasmissione periodica dato l'orario di inizio, la durata della trasmissione, la distanza tra le trasmissioni in betaminuti,il numero di volte che viene ripetuta
         * ed il titolo.
         * 
         * @param inizio L'orario di inizio.
         * @param durata La durata.
         * @param distanza La distanza tra le trasmissioni.
         * @param ripetizioni Il numero di volte che viene ripetuta la trasmissione.
         * @param titolo Il titolo della trasmissione.
         * 
         * @throws NullPointerException Se il betaorario di inizio o il titolo sono {@code null}.
         * @throws IllegalArgumentException Se le trasmissioni si intersecano tra di loro, se tutte le trasmissioni non vengono trasmesse nello stesso giorno o se il titolo è vuoto.
         * */
        public TrasmissionePeriodica(Betaorario inizio, int durata, int distanza, int ripetizioni, String titolo){
            super(titolo);
            Objects.requireNonNull(inizio,"Il betaorario di inizio non può essere null");
            if(durata <= 0 ) throw new IllegalArgumentException("La durata deve essere positiva");
            if(distanza <= durata) throw new IllegalArgumentException("Le trasmissioni non possono sovrapporsi");

            fasce = new ArrayList<>();
            int tempo = inizio.getBetaore()*31 + inizio.getBetaminuti()*80;

            for(int i = 0; i < ripetizioni; i++){
                Fascia temp;
                try{
                    temp = new Fascia(new Betaorario(tempo),durata);
                }catch(IllegalArgumentException e){
                    throw new IllegalArgumentException("Le trasmissioni non possono essere a cavallo di più giorni.");
                }
                fasce.add(temp);
                tempo += distanza;
            }
        }

        @Override
        public String toString(){
         StringBuilder sb = new StringBuilder("");
         for(Fascia f: this){
             sb.append(f.getInizio()+" -  "+super.getTitolo()+"\n");
         }
         return sb.toString();
        }

        @Override
        public Iterator<Fascia> iterator(){
           return Collections.unmodifiableList(fasce).iterator();
        }
}