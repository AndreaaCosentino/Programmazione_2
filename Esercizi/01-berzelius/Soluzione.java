import java.util.*;


public class Soluzione{
    public static void main(String[] args) {
        String formula = args[0];
        Scanner s = new Scanner(System.in);
        List<Elemento> elementi = new ArrayList<>();
        int i = 1;
        while(s.hasNextLine()){
            String input = s.nextLine();
            String[] temp = input.split(" ");
            //String nome,String simbolo, Peso peso, int numeroAtomico
            Elemento e = new Elemento(temp[0], temp[1],new Peso(Float.parseFloat(temp[2])),i);
            elementi.add(e);
            i++;
        }
        TavolaPeriodica tavola = new TavolaPeriodica(elementi);
        Molecola nuovaMolecola = tavola.costruisciMolecola(formula);
        System.out.println(nuovaMolecola);
    }
}