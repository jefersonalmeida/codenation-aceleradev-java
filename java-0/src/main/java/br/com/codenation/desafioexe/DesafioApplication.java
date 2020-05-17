package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {
    private static final char limit = 350;

    public static List<Integer> fibonacci() {
        List<Integer> seq = new ArrayList<>();
        int i = 0, current;

        do {
            seq.add(i < 2 ? i : seq.get(i - 1) + seq.get(i - 2));
            current = seq.get(i);
            i++;
        } while (current <= limit);

        return seq;
    }

    public static Boolean isFibonacci(Integer a) {
        return fibonacci().contains(a);
    }

}