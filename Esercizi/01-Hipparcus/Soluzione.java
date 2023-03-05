import java.util.*;
public class Soluzione {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        List<Double> input = new ArrayList<>();

        while(s.hasNextDouble()){
            Double o = s.nextDouble();
            input.add(o);
        }
        s.close();
        UnaPassata a = new UnaPassata(input);
        DuePassate b = new DuePassate(input);
        UnaPassataStabile c = new UnaPassataStabile(input);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
