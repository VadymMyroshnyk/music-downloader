package org.geekhub.hw5.example;

import java.util.List;

public class ForEachExample {

    public static void main(String[] args) {
        List.of(1, 2, 3, 4)
            .forEach(num -> {
                isValid(num);
                System.out.println("Test forEach " + num);
            });

        System.out.println("Finish");
    }

    private static void isValid(int num) {
        if (num == 3) {
            throw new RuntimeException();
        }
    }
}
