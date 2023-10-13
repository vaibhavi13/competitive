package Homework4;

import java.util.*;
import java.util.stream.Collectors;

public class Bridge {

    public static void main(String[] args) {
       Bridge br = new Bridge();
       br.solution();
    }

    public void solution(){

        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < testCases; i++){
           int n = sc.nextInt();
           int[] crossingTimes = new int[n];
           for(int j = 0 ; j < n ; j++){
               crossingTimes[j] = sc.nextInt();
           }
           sb.append(find(crossingTimes));
           sb.append("\n");
        }
        System.out.println(sb.toString().trim());

    }

    private String find(int[] crossingTimes) {
        StringBuilder res = new StringBuilder();

        List<Integer> inputTimes = Arrays.stream(crossingTimes).boxed().collect(Collectors.toList());
        List<Integer> outputTimes = new ArrayList<>();
        Integer totalTime = 0;
        while(inputTimes.size() > 0){

            Collections.sort(inputTimes);
            double meanTime = (double)(inputTimes.get(0) + inputTimes.get(inputTimes.size()-1))/2;
            List<Integer> lessThanMeanTime = inputTimes.stream().filter(x -> x < meanTime).collect(Collectors.toList());
            List<Integer> moreThanMeanTime = inputTimes.stream().filter(x -> x >= meanTime).collect(Collectors.toList());

            if(lessThanMeanTime.size() > moreThanMeanTime.size()){
                // send either 2 fastest or 2 slowest
                if(outputTimes.size() > 0 && outputTimes.get(0) < inputTimes.get(inputTimes.size()-2)){
                    // send two slowest
                    Integer s1 = inputTimes.get(inputTimes.size()-1);
                    Integer s2 = inputTimes.get(inputTimes.size()-2);
                    res.append(s2 + " " + s1 + "\n");
                    totalTime += s1;
                    outputTimes.add(inputTimes.remove(inputTimes.size()-1));
                    outputTimes.add(inputTimes.remove(inputTimes.size()-1));
                }else{
                    // send two fastest
                    Integer f1 = inputTimes.get(0);
                    Integer f2 = inputTimes.get(1);
                    res.append(f1 + " " + f2 + "\n");
                    totalTime += f2;
                    outputTimes.add(inputTimes.remove(0));
                    outputTimes.add(inputTimes.remove(0));
                }
            }else{
                // send smallest & fastest
                Integer f1 = inputTimes.get(0);
                Integer s1 = inputTimes.get(inputTimes.size()-1);
                res.append(f1 + " " + s1 + "\n");
                totalTime += s1;
                outputTimes.add(inputTimes.remove(0));
                outputTimes.add(inputTimes.remove(inputTimes.size()-1));
            }

            if(inputTimes.size() == 0){
                break;
            }

            Collections.sort(outputTimes);
            Integer outputFastest = outputTimes.get(0);
            res.append(outputFastest + "\n");
            totalTime += outputFastest;
            inputTimes.add(outputTimes.remove(0));
        }
        return totalTime + " \n" + res;
    }
}
