package Homework6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arbitrage {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            double[][] rates = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        rates[i][j] = 1.0;
                    } else {
                        rates[i][j] = sc.nextDouble();
                    }
                }
            }

            List<Integer> ans = findPath(rates);
            if (ans.isEmpty()) {
                System.out.println("no arbitrage sequence exists");
                continue;
            }
            // print path
            StringBuilder sb = new StringBuilder();
            for(Integer a : ans){
                sb.append(a + " ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    private static List<Integer> findPath(double[][] rates) {

        List<Integer> ans = new ArrayList<>();




        return ans;

    }
}
