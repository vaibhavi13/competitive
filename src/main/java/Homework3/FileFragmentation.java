package Homework3;

import java.util.*;

public class FileFragmentation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        sc.nextLine();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            List<String> inputData = new ArrayList<>();
            int totalLength = 0;

            while(sc.hasNext()){
                String str = sc.nextLine();

                if(str.equals("")){
                    break;
                }
                totalLength += str.length();
                inputData.add(str);
            }
            String[] data;
            data = inputData.toArray(new String[0]);
            sb.append(findFragment(data)).append("\n").append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    private static String findFragment(String[] data) {

        Arrays.sort(data, Comparator.comparingInt(String::length).thenComparing(a->a));

        int start = 0;
        int end = data.length - 1;

        List<String> prefixList = new ArrayList<>();
        List<String> suffixList = new ArrayList<>();

        while (start < end) {
            prefixList.add(data[start]);
            suffixList.add(data[end]);
            start++;
            end--;
        }

        String res = prefixList.get(0) + suffixList.get(0);

        if(prefixList.size() > 1){
            if(!((prefixList.get(1) + suffixList.get(1)).equals(res) || (suffixList.get(1) + prefixList.get(1)).equals(res))){
                res = suffixList.get(0) + prefixList.get(0);
            }
        }
        return res.trim();
    }

}
