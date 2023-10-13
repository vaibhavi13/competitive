package Homework7;

import java.util.*;

public class TowerOfCubes {

    private static int dp[][];
    private static List<String> faces;
    private static HashMap<String,List<Integer>> map;
    private static StringBuilder sb;

    public static void main(String[] args) {

        faces = Arrays.asList("front", "back", "left", "right", "top", "bottom");

        Scanner sc = new Scanner(System.in);
        int count = 1;
        while(sc.hasNext()){
            int n = sc.nextInt();
            map = new HashMap<>();
            if(n == 0){
                break;
            }
            int c[][] = new int[n][6];
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < 6 ; j++){
                    c[i][j] = sc.nextInt();
                }
            }
            if(sb != null){
                System.out.println(sb.toString());
            }
            sb = new StringBuilder();
            System.out.println("Case #" + count++);
            find(c);
            //sb.append("\n");
        }
        System.out.println(sb.toString().trim());

    }

    private static void find(int[][] c) {
         dp = new int[c.length + 1 ][6 + 1];
         for(int[] d : dp){
             Arrays.fill(d , -1);
         }

         int max = 0;

         for(int i = 0 ; i < c.length ; i++){
             for(int j = 0 ; j < 6 ; j++){
                 max = Math.max(max, search(c, i , j));
             }
         }
        System.out.println(max);

        // find path
        int position = -1 , face = - 1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (max == dp[i][j]) {
                    position = i;
                    face = j;
                    break;
                }
            }
        }

        List<String> output = new ArrayList<>();

        while(max != 0){
            int currFace = face % 2 == 0 ? face + 1 : face - 1;
            output.add(0, (position+1) + " " + faces.get(currFace));
            max--;
            if(max == 0){
                break;
            }
            List<Integer> list = map.get(position+ " " + faces.get(face));
            position = list.get(0);
            face = list.get(1);
        }

        for(String s : output){
            System.out.println(s);
        }
    }

    private static int search(int[][] c, int position, int face) {

        // base condition
        if(position < 0 ){
            return 0 ;
        }

        if(dp[position][face] != -1){
            return dp[position][face];
        }

        int color = getOppositeFace(c,position,face);

        int max = 1;

        for(int i = position - 1 ; i >= 0 ; i--){
            for(int j = 0 ; j < 6 ; j++){
                if(c[i][j] != color){
                    continue;
                }
                int localResult = search(c, i , j) + 1;
                if(localResult > max){
                    max = localResult;
                    map.put(position+ " " + faces.get(face), Arrays.asList(i,j));
                }
            }
        }
        dp[position][face] = max;
        return dp[position][face];
    }

    private static int getOppositeFace(int[][] c, int position, int face) {
        if(faces.get(face).equals("front") || faces.get(face).equals("left") || faces.get(face).equals("top")){
            return c[position][face + 1];
        }else{
            return c[position][face - 1];
        }
    }
}
