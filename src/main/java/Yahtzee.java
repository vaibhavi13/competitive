import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Yahtzee {

    private static HashMap<String, Integer> map = new HashMap();

    private static int findBestScore(int[][] rolls) {

        int bestScore = 0 ;

        int[][] grid = new int[13][13];
        for(int i = 0 ; i < 13 ; i++){
            grid[i] = getScoresGrid(rolls[i]);
        }

        // find max score using memoization

        int[] visited = new int[13];
        Arrays.fill(visited, 0);
        bestScore = helper(grid, visited, 13);

        return bestScore;
    }

    private static int helper(int[][] grid, int[] visited, int current_roll){

        if (map.containsKey(Arrays.toString(visited))) {
            return map.get(Arrays.toString(visited));
        }

        int max = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            visited[i] = 1;
            int local = helper(grid, visited,current_roll - 1) + grid[i][current_roll - 1] ;
            max = Math.max(max, local);
            visited[i] = 0;
        }
        map.put(Arrays.toString(visited), max);
        return max;
    }

    private static int[] getScoresGrid(int[] rolls) {
        int[] score = new int[13];
        int[] freq = new int[7];

        for (int roll : rolls) {
            freq[roll]++;
        }

        for (int i = 1; i <= 6; i++) {
            score[i - 1] = freq[i] * i;
            score[6] += score[i - 1];
        }

        int maxFrequency = Arrays.stream(freq).max().orElse(0);

        if (maxFrequency >= 3) {
            score[7] = score[6];
        }

        if (maxFrequency >= 4) {
            score[8] = score[6];
        }

        if (maxFrequency >= 5) {
            score[9] = 50;
        }

        for (int i = 1; i <= 3; i++) {
            if (IntStream.range(i, i + 4).allMatch(j -> freq[j] > 0)) {
                score[10] = 25;
                break;
            }
        }

        for (int i = 1; i <= 2; i++) {
            if (IntStream.range(i, i + 5).allMatch(j -> freq[j] > 0)) {
                score[11] = 35;
                break;
            }
        }

        if ((Arrays.stream(freq).anyMatch(f -> f == 2) && Arrays.stream(freq).anyMatch(f -> f == 3)) || maxFrequency == 5) {
            score[12] = 40;
        }

        return score;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int[][] rolls = new int[13][5];
            for(int i = 0 ; i < rolls.length; i++){
                for(int j = 0 ; j < rolls[i].length; j++){
                    rolls[i][j] = sc.nextInt();
                }
            }
            System.out.println(findBestScore(rolls));
        }
    }

}
