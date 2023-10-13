package Homework7;

import java.util.*;

public class Bicoloring {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextInt()){
            int n = sc.nextInt();
            if(n == 0) return;
            List<List<Integer>> adj = new ArrayList<>();
            int color[] = new int[n];

            for(int j = 0 ; j < n ; j++){
                adj.add(new ArrayList<>());
            }
            int m = sc.nextInt();
            for(int j = 0 ; j < m ; j++){
                addEdge(adj, sc.nextInt(), sc.nextInt());
            }
            color[0] = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            if(find(adj, color, queue)){
                System.out.println("BICOLORABLE.");
            }else{
                System.out.println("NOT BICOLORABLE.");
            }
        }
    }

    private static boolean find(List<List<Integer>> adj, int[] color, Queue<Integer> queue) {

        while(!queue.isEmpty()){
            Integer node = queue.poll();
            int parentColor = color[node];

            List<Integer> child = adj.get(node);
            for(int c : child){
                if(color[c] != 0 && color[c] == parentColor){
                    return false;
                }
                if(color[c] == 0){
                    color[c] = parentColor * -1 ;
                    queue.add(c);
                }
            }
        }
        return true;
    }

    private static void addEdge(List<List<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
}
