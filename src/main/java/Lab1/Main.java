package Lab1;

import java.util.Scanner;

public class Main {

    private static int find(int n ){
        int count = 1 ;
        while(n != 1){
            if(n % 2 != 0){
                n = 3*n + 1;
            }else{
                n = n/2;
            }
            count++;
        }
        return count;
    }

    private static int max_cycle(int i, int j){
        int max = 0 ;
        if(i > j){
            int t = i ;
            i = j ;
            j = t;
        }
        for(int k = i ; k <= j ; k++){
            max = Math.max(max, find(k));
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println (a + " " + b + " " + max_cycle(a,b));
        }
    }
}
