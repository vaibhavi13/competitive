package Homework2;

import java.util.*;

public class Erdos {

    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static boolean visited[] ;
    static int distance[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int scenario = sc.nextInt();
        for(int m = 1 ; m <= scenario ; m++){
            int p = sc.nextInt();
            int n = sc.nextInt();
            List<String> papers = new ArrayList<>();
            sc.nextLine();
            for(int i = 0 ; i < p ; i++){
                papers.add(sc.nextLine());
            }
            List<String> names = new ArrayList<>();
            for(int i = 0 ; i < n ; i++){
                names.add(sc.nextLine());
            }
            List<String> output = find(papers,names);
            System.out.println("Scenario " + m);
            for(String o : output){
                System.out.println(o);
            }
        }
    }

    private static void addEdge(int i, int i1) {
        if(i >= adj.size() || i1 >= adj.size()){
            int index = Math.max(i , i1);
            for(int j = adj.size() ; j <= index ; j++){
                adj.add(j,new ArrayList<>());
            }
        }
        adj.get(i).add(i1);
        adj.get(i1).add(i);
    }

    private static boolean findShortestPath(int src, int dest) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited[src] = true;
        distance[src] = 0;
        int temp;

        ArrayList<Integer> tempList ;

        while(!queue.isEmpty()){
            temp = queue.poll();
            tempList = adj.get(temp);
            if (tempList == null) {
                return false;
            }
            for(int i = 0 ; i < tempList.size() ; i++){
                if(!visited[tempList.get(i)]){
                    visited[tempList.get(i)] = true;
                    distance[tempList.get(i)] = distance[temp] + 1 ;
                    if(tempList.get(i) == dest){
                        return true;
                    }
                    queue.add(tempList.get(i));
                }
            }
        }
        return false;
    }

    private static List<String> find(List<String> papers, List<String> inputNames) {
        adj = new ArrayList<>();
        List<String> res = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        int no = 1;

        for(String paper : papers){
            String temp = paper.substring(0,paper.indexOf(':'));
            String[] authors = temp.split(",");
            List<String> names = new ArrayList<>();
            for(int i = 0 ; i < authors.length; i = i+2) {
                String author = authors[i].trim();
                if(i + 1 < authors.length) {
                    author = author + ", " + authors[i+1].trim();
                }
                names.add(author);
                if(!map.containsKey(author)){
                    map.put(author, no++);
                }
            }

            // populate adj

            for(int i = 0 ; i < names.size() ; i++){
                for(int j = i; j < names.size() ; j++){
                    addEdge(map.get(names.get(i)), map.get(names.get(j)));
                }
            }
        }

        // find distance via bfs for each name
        String srcName = "Erdos, P.";

        for (String destName : inputNames){
            visited = new boolean[no];
            distance = new int[no];
            if(!map.containsKey(srcName) || !map.containsKey(destName.trim()) || !findShortestPath(map.get(srcName),map.get(destName.trim()))){
                res.add(destName + " infinity");
            } else {
                res.add(destName + " " +distance[map.get(destName.trim())]);
            }
        }
    return res;
    }
}
