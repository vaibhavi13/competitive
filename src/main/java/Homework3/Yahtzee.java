package Homework3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Yahtzee {

    HashMap<String, Integer> map = new HashMap();
    HashMap<String, Integer> isBonus = new HashMap();
    HashMap<String, String> backtrack = new HashMap();

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


    private int[] solveYahtzee(int[][] rolls) {
        this.map.clear();
        this.isBonus.clear();
        this.backtrack.clear();
        int[][] grid = new int[13][13];
        for (int i = 0; i < 13; i++) {
            grid[i] = getScoresGrid(rolls[i]);
        }

        int[] ans = new int[2];
        ans = helper(grid, new int[13], 12);
        System.out.print(printOptRow(grid));
        return ans;
    }

    public String printOptRow(int[][] grid) {
        StringBuilder sb = new StringBuilder();
        int cat = 12;
        int[] start = new int[13];
        while (this.backtrack.containsKey(Arrays.toString(start)) && cat >= 0) {
            String fromInput = this.backtrack.get(Arrays.toString(start));
            String[] elements = fromInput.substring(1, fromInput.length() - 1).split(", ");
            int[] from = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                from[i] = Integer.parseInt(elements[i]);
            }

            for (int i = 0; i < start.length; i++) {
                if (start[i] != from[i]) {
                    sb.insert(0, " " + grid[i][cat--]);
                    break;
                }
            }
            start = from;
        }
        return sb.toString().trim();
    }

    public int[] helper(int[][] grid, int[] taken, int cat) {

        String hash = Arrays.toString(taken);
        if (this.map.containsKey(hash)) {
            return new int[]{this.map.get(hash), this.isBonus.get(hash)};
        }

        int[] ans = new int[2];
        int[] from = new int[13];
        for (int i = 0; i < taken.length; i++) {
            if (taken[i] == 0) {
                // take
                taken[i] = 1;

                if (cat <= 5) {
                    int[] local = helper(grid, taken, cat - 1);
                    local[0] += grid[i][cat];

                    if (local[1] == 1) {
                        if (ans[0] < local[0]) {
                            ans = local;
                            from = Arrays.copyOf(taken, 13);
                        }
                    } else {
                        if (local[0] >= 63) {
                            if (ans[0] < local[0]) {
                                ans = local;
                                ans[0] += 35;
                                ans[1] = 1;
                                from = Arrays.copyOf(taken, 13);
                            }
                        } else {
                            if (ans[0] <= local[0]) {
                                ans = local;
                                from = Arrays.copyOf(taken, 13);
                            }
                        }
                    }
                } else {
                    int[] local = helper(grid, taken, cat - 1);
                    local[0] += grid[i][cat];

                    if (local[0] == ans[0] && local[1] == 0) {
                        ans = local;
                        from = Arrays.copyOf(taken, 13);
                    } else if (ans[0] < local[0]) {
                        ans = local;
                        from = Arrays.copyOf(taken, 13);
                    }
                }
                taken[i] = 0;
            }
        }
        this.map.put(hash, ans[0]);
        this.isBonus.put(hash, ans[1]);
        this.backtrack.put(hash, Arrays.toString(from));
        return ans;
    }

    public static void main(String[] args) {
        Yahtzee y = new Yahtzee();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int[][] grid = new int[13][5];
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 5; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            int[] ans = y.solveYahtzee(grid);
            System.out.println(" " + (ans[1] == 1 ? "35 " : "0 ") + ans[0]);
        }
    }

}