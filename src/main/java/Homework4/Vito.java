package Homework4;

import java.util.Arrays;
import java.util.Scanner;

public class Vito {

    public static void main(String[] args) {
        Vito v = new Vito();
        v.solution();
    }

    public void solution(){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for(int i = 0 ; i < n ; i++){
            int m = sc.nextInt();
            int arr[] = new int[m];
            for(int j = 0 ; j < m ; j++){
                arr[j] = sc.nextInt();
            }
            Arrays.sort(arr);
            System.out.println(find(arr));
        }
    }

    private int find(int[] arr) {
        int median;
        if(arr.length % 2 != 0){
             median =  arr[(int)Math.floor(arr.length/2)];

        }else{
            int start = arr[arr.length/2-1];
            int end = arr[arr.length/2];
            median = (start+end)/2;
        }

        int sum = 0 ;

        for(int i = 0 ; i < arr.length ; i++){
            sum += Math.abs(arr[i]-median);
        }
        return sum;
    }
}

