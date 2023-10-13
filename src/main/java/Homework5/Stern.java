package Homework5;

import java.util.Scanner;

public class Stern {

    StringBuilder sb;

    public String findSternNumber(int m, int n) {
        this.sb = new StringBuilder();
        helper(m, n, 0, 1, 1, 0, 1, 1);
        return this.sb.toString();
    }

    public void helper(int m, int n, int lt, int lb, int rt, int rb, int t, int b) {
        if (m == t && n == b) {
            return;
        }

        if (m * b > t * n) {
            this.sb.append("R");
            helper(m, n, t, b, rt, rb, t + rt, b + rb);
        } else {
            this.sb.append("L");
            helper(m, n, lt, lb, t, b, t + lt, b + lb);
        }
    }

    public static void main(String[] args) {
        Stern stern = new Stern();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            if (n == m && n == 1) {
                break;
            }
            System.out.println(stern.findSternNumber(m, n));
        }
    }
}

