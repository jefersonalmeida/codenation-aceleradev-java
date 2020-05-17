package br.com;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

public class Principal {

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        System.out.println(average(new int[]{1, 2, 3, 4, 5})); // 3
        System.out.println(mode(new int[]{1, 2, 3, 3})); // 3
        System.out.println(median(new int[]{1, 2, 3, 4, 5})); // 3
        System.out.println(LocalDateTime.now());
    }

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
