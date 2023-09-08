package Homework1;

import java.util.Scanner;

public class Trip {

    private static double getAmount(double[] a){

        double sum = 0.0;
        for(double num : a){
            sum += num;
        }
        double share = sum/a.length;

        double paidMore = 0.0;
        double paidLess = 0.0;

        for(double num : a){
            if(num < share){
                paidLess += (long)((share - num)*100.00)/100.00;
            }else if(num > share){
                paidMore += (long)((num - share)*100.00)/100.00;
            }
        }
       return paidLess > paidMore ? paidLess : paidMore;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            int n = sc.nextInt();
            if(n == 0){
                break;
            }
            double a[] = new double[n];
            for(int i = 0 ; i < n ; i++){
                a[i] = sc.nextDouble();
            }
            double amount = getAmount(a);
            System.out.println(String.format("$%.2f",amount));
        }
    }
}
