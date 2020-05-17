package br.com.codenation;

import java.util.Arrays;
import java.util.HashMap;

public class StatisticUtil {

    public static int average(int[] elements) {
        return Arrays.stream(elements).sum() / elements.length;
    }

    public static int mode(int[] elements) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int max = 1, mode = 0;

        for (int element : elements) {
            if (hashMap.get(element) != null) {
                int count = hashMap.get(element);
                count++;
                hashMap.put(element, count);

                if (count > max) {
                    max = count;
                    mode = element;
                }
            } else
                hashMap.put(element, 1);
        }
        return mode;
    }

    public static int median(int[] elements) {
        Arrays.sort(elements);
        int total = elements.length;
        return (total % 2 == 0)
                ? (elements[total / 2 - 1] + elements[total / 2]) / 2
                : elements[elements.length / 2];
    }
}