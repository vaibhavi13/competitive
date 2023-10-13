package Homework7;

import java.util.Scanner;

public class Factovisors {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            if(find(m, n)){
                System.out.println(m+" divides " + n + "!");
            }else{
                System.out.println(m+ " does not divide " + n + "!");
            }
        }
    }

    private static boolean find(int m,int n) {

        if(m == 0) return false; // invalid
        if(m == 1) return true;  // always divisible
        if(n == 0) return false; // invalid

        int count = 0;
        if(m % 2 == 0){
            while (m % 2 == 0) {
                count++;
                m /= 2;
            }
            if(!divides(n,2,count)) return false;
        }

        for (int i = 3; i <= Math.sqrt(m); i += 2) {
            if(m % i == 0){
                count = 0;
                while (m % i == 0) {
                    count++;
                    m /= i;
                }
                if(!divides(n,i,count)) return false;
            }
        }

        if(m > n) return false;

        return true;
    }

    public static boolean divides(int n, int key , int value){

            int count = 0 ;
            boolean divide = false;
            for(int i = key ; i <= n ; i += key){
                int num = i;
                while( num > 1 && num % key == 0){
                    count++;
                    if(count >= value){
                       divide = true;
                       break;
                    }
                    num = num / key;
                }

                if(divide){
                    break;
                }
            }

            if(count < value){
                return false;
            }

        return true;
    }
}
