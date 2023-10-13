package Homework6;

import java.math.BigInteger;
import java.util.Scanner;

public class Counting {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            int n = sc.nextInt();
            System.out.println(find(n));
        }
    }

    public static String find(int n){

        if(n == 0){
            return "1";
        }
        if(n == 1){
            return "2";  // 1 & 4
        }
        if(n == 2){
            return "5";   // given
        }

        BigInteger x0 = new BigInteger("1");
        BigInteger x1 = new BigInteger("2");
        BigInteger x2 = new BigInteger("5");
        for(int i = 3 ; i <= n ;i++){
            // x(n) = x(n-1) + x(n-2) + x(n-3) + x(n-1)
            BigInteger temp = x2.multiply(new BigInteger("2"));
            temp = temp.add(x1);
            temp = temp.add(x0);
            x0 = x1;
            x1 = x2 ;
            x2 = temp;
        }
        return x2.toString();
    }
}
