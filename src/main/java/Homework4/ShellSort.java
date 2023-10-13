package Homework4;

import java.util.*;

public class ShellSort {

    public static void main(String[] args) {
        ShellSort ss = new ShellSort();
        ss.solution();
    }

    public void solution(){
        Scanner sc = new Scanner(System.in);
        int cases= sc.nextInt();
        for(int i = 0 ; i < cases ; i++){
           int n = sc.nextInt();
           sc.nextLine();
           ArrayList<String> list = new ArrayList<>();
           for(int j = 1 ; j <= n ; j++){
              list.add(sc.nextLine());
           }
           int count = 1;
            HashMap<String,Integer> map = new HashMap<>();
            HashMap<Integer,String> intMap = new HashMap<>();
            for(int j = 1 ; j <= n ; j++){
               String str = sc.nextLine();
               map.put(str,count);
               intMap.put(count,str);
               count++;
           }
           List<Integer> nums = new ArrayList<>();
           for(int j = 0 ; j < n ; j++){
               nums.add(map.get(list.get(j)));
           }
           List<String> res = find(nums,intMap,n);
           for(String str : res){
               System.out.println(str);
           }
            System.out.println();
        }
    }

    private List<String> find(List<Integer> nums, HashMap<Integer, String> map, int n) {

        List<String> res = new ArrayList<>();
        HashMap<Integer,Integer> pos = new HashMap<>();
        int count = 0;

        for(Integer numElement : nums){
            pos.put(numElement, count++);
        }
        int curr = n - 1;
        while( curr > 0){
            int posCurr = pos.get(curr);
            int posNext = pos.get(curr + 1);

            if(posCurr > posNext){
                // need to shuffle
                res.add(map.get(curr));
                for(Map.Entry<Integer,Integer> entry : pos.entrySet()){
                    entry.setValue(entry.getValue() + 1);
                }
                pos.put(curr,0);
            }
            pos.remove(curr + 1);
            curr--;
        }

        return res;
    }


}
