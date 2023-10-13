package Homework3;

import java.util.*;

public class Doublets {

    private List<String> dict = new ArrayList<>();

    private HashMap<String,List<String>> patternToWord = new HashMap<>();

    private HashMap<String, HashSet<String>> adjMap = new HashMap<>();

    public static void main(String[] args) {
        Doublets mc = new Doublets();
        mc.solution();
    }

    public void solution() {
        this.dict = new ArrayList<>();
        this.patternToWord = new HashMap<>();
        this. adjMap = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            if(str.equals("")){
                break;
            }
            dict.add(str);
        }

        mapTheDict(dict);
        constructGraph();

        while(sc.hasNext()){
            sb.append(findPath(sc.next(), sc.next())).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private void constructGraph() {

        for(List<String> list : patternToWord.values()){
            if (list.size() <= 1) continue;
            for(int i = 0 ; i < list.size(); i++){
                for(int j = 0 ; j < list.size(); j++){
                    addEdge(list.get(i) , list.get(j));
                }
            }
        }

    }

    private void addEdge(String s1, String s2) {
        if(s1.equals(s2)) return;

        HashSet<String> set1 = adjMap.getOrDefault(s1, new HashSet<>());
        set1.add(s2);

        HashSet<String> set2 = adjMap.getOrDefault(s2, new HashSet<>());
        set2.add(s1);

        adjMap.put(s1,set1);
        adjMap.put(s2,set2);
    }

    private void mapTheDict(List<String> dict) {
        StringBuilder sb;
        for(String str : dict){
            for(int i = 0 ; i < str.length() ; i++){
                sb = new StringBuilder(str);
                sb.setCharAt(i,'*');

                String temp = sb.toString();
                // pattern to word
                patternToWord.putIfAbsent(temp, new ArrayList<>());
                patternToWord.get(temp).add(str);
            }
        }

    }

    private String findPath(String start, String end) {

        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String,String> prec = new HashMap<>();
        queue.add(start);
        prec.put(start,null);
        visited.add(start);
        boolean found = false;

        while(!queue.isEmpty()){
            String word = queue.poll();
            if(adjMap.containsKey(word)){
                HashSet<String> set = adjMap.get(word);
                for(String str : set){
                    if(!visited.contains(str)){
                        visited.add(str);
                        prec.put(str,word);
                        if(str.equals(end)){
                            found = true;
                            break;
                        }
                        queue.add(str);
                    }
                }
                if(found){
                    break;
                }
            }
        }

        if(!found){
            res =  Arrays.asList("No solution.");
        }else{
            String temp = end;
            while(temp != null){
                res.add(temp);
                temp = prec.get(temp);
            }
            Collections.reverse(res);
        }
        StringBuilder sb = new StringBuilder();
        for(String str : res){
            sb.append(str).append("\n");
        }
        return sb.toString();
    }

}
