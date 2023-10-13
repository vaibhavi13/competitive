package Homework5;

import java.util.HashMap;
import java.util.Scanner;

public class Ones {

    private int findB(int n) {
        int ending = n % 10;
        HashMap<Integer, Integer> map = new HashMap();

        for (int i = 0; i < 10; i++) {
            map.put(((ending * i) % 10), i);
        }

        int ones = 0;
        int last = 0;
        do {
            if (last == 1) {
                last = last / 10;
                ones++;
                continue;
            }

            int digit = (11 - (last % 10)) % 10;
            int multiplier = map.get(digit);
            int multiplied = multiplier * n;
            last = (last + multiplied) / 10;
            ones++;
        } while (last != 0);

        return ones;
    }

    public static void main(String[] args) {
        Ones ones = new Ones();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int n = Integer.parseInt(sc.nextLine());
            System.out.println(ones.findB(n));
        }
    }
}
