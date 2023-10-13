package Homework6;

import java.math.BigInteger;
import java.util.Scanner;

public class CompleteTreeLabeling {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int k = sc.nextInt();
            int d = sc.nextInt();
            System.out.println(find(k,d));
        }
    }

    private static BigInteger find(int k, int d) {
        BigInteger totalLabels = getTotalLabels(k,d);
        return helper(totalLabels,k,d);
    }

    private static BigInteger helper(BigInteger n, int k, int d) {

        // base condition

        if(d == 0){
            return BigInteger.ONE;
        }

        BigInteger totalLabels = factorial(n.subtract(BigInteger.ONE));
        BigInteger parentLabels = factorial(n.subtract(BigInteger.ONE).divide(new BigInteger(String.valueOf(k))));

        BigInteger totalWaysofParentLabels = BigInteger.ONE;

        for(int i = 0 ; i < k ; i++){
            totalWaysofParentLabels = totalWaysofParentLabels.multiply(parentLabels);
        }
        BigInteger currentLabels = totalLabels.divide(totalWaysofParentLabels);

        // calculate remaining child labels

        BigInteger childLabels = helper(n.subtract(BigInteger.ONE).divide(new BigInteger(String.valueOf(k))), k , d -1);

        BigInteger totalWaysofChildLabels = BigInteger.ONE;

        for(int i = 0 ; i < k ; i++){
            totalWaysofChildLabels = totalWaysofChildLabels.multiply(childLabels);
        }

        return currentLabels.multiply(totalWaysofChildLabels);
    }

    private static BigInteger factorial(BigInteger n){

        BigInteger temp = BigInteger.ONE;
        BigInteger ans = BigInteger.ONE;
        while(temp.compareTo(n) <= 0){
            ans = ans.multiply(temp);
            temp = temp.add(BigInteger.ONE);
        }
        return ans;
    }

    private static BigInteger getTotalLabels(int k, int d) {

        if( k == 1){
            return new BigInteger(String.valueOf(d));
        }

        BigInteger temp = BigInteger.ONE;
        for(int i = 0 ; i <= d ; i++){
            temp = temp.multiply(new BigInteger(String.valueOf(k)));
        }
        // nodes = (k^h+1 -k / k-1) + 1
        return temp.subtract(new BigInteger(String.valueOf(k))).divide(new BigInteger(String.valueOf(k)).subtract(BigInteger.ONE)).add(BigInteger.ONE);
    }

}

