package Homework1;

import java.util.*;

public class Vote {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            int k = sc.nextInt();
            sc.nextLine();
            for(int i = 0 ; i < k ; i++){
                int n = sc.nextInt();
                String[] names = new String[n];
                sc.nextLine();
                for(int j = 0 ; j < n ; j++){
                    names[j] = sc.nextLine();
                }
                int count = 0 ;
                ArrayList<String> list = new ArrayList<>();
                while(sc.hasNext()){
                    String str = sc.next();
                    if(str.equals("\n")){
                        break;
                    }
                    list.add(str);
                    count++;
                }
                int a[][] = new int[count][n];
                for(int p = 0 ; p < count ; p++){
                    String str = list.get(p);
                    for(int q = 0 ; q < str.length() ; q++){
                        if(str.charAt(q) != ' '){
                            a[p][q] = Integer.parseInt(String.valueOf(str.charAt(q)));
                        }
                    }
                }
                System.out.println(findWinner(a,names));
            }
        }
    }

    private static String findWinner(int[][] a, String[] names) {
        int[] choice = new int[a.length];

        int[] cal = new int[a.length];
        int k = 0 ;
        while(k < a[0].length){

            for(int i = 0 ; i < a.length ; i++){
                cal[i] = a[i][choice[i]];
            }
            Integer[] candidates = new Integer[21];

            for(int  i = 0 ; i < cal.length ; i++){
                candidates[cal[i]]++;
            }

            int max = Collections.max(Arrays.asList(candidates));
            int maxIndex = -1;
            int countMax = 0;

            for(int i = 0 ; i < candidates.length ; i++){
                if(candidates[i] == max){
                    maxIndex = i;
                    countMax++;
                }
            }

            if(countMax == 1){
                return names[maxIndex+1];
            }else{

                // find Min
                int min = Integer.MAX_VALUE;
                int minIndex = -1;
                for(int i = 0 ; i < candidates.length ; i++){
                    if(candidates[i] != 0){
                        if(candidates[i] < min){
                            min = candidates[i];
                            minIndex = i;
                        }
                    }
                }
                choice[minIndex]++;
            }
            k++;
        }

        return "";
    }
}
