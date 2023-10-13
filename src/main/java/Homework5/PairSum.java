package Homework5;

import java.util.Scanner;

public class PairSum {

    private int[] originalNums;
    private int[] stageValues;
    private boolean[] visited;

    private String find(int n, int[] nums) {
        this.originalNums = new int[n];
        this.stageValues = new int[nums.length];
        this.visited = new boolean[nums.length];
        return backtrack(nums, 0) ? buildAns() : "Impossible";
    }

    private String buildAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.originalNums.length; i++) {
            sb.append(this.originalNums[i]).append(" ");
        }
        return sb.toString().trim();
    }

    private boolean backtrack(int[] arr, int stage) {
        if (stage == arr.length) {
            return true;
        }

        for (int i = 0; i < arr.length; i++) {
            if (this.visited[i]) {
                continue;
            }
            if (stage <= 1) {
                this.stageValues[stage] = arr[i];
            } else if (stage == 2) {
                this.stageValues[stage] = arr[i];
                int sum = this.stageValues[0] + this.stageValues[1] + this.stageValues[2];
                if (sum % 2 != 0) {
                    continue;
                }
                sum /= 2;
                this.originalNums[0] = sum - this.stageValues[0];
                this.originalNums[1] = sum - this.stageValues[1];
                this.originalNums[2] = sum - this.stageValues[2];

                if (!(this.originalNums[0] <= this.originalNums[1] && this.originalNums[1] <= this.originalNums[2])) {
                    continue;
                }
            } else if (stage > 2) {
                int level = stage;
                int step = 1;
                while (level >= step) {
                    level -= step;
                    step++;
                }
                int current_number = arr[i] - this.originalNums[level];

                if (level == 0) {
                    this.originalNums[step] = current_number;
                    this.stageValues[stage] = arr[i];
                } else {
                    if (this.originalNums[step] != current_number) {
                        continue;
                    }
                    if (this.originalNums[step] < this.originalNums[step - 1]) {
                        continue;
                    }

                    this.stageValues[stage] = arr[i];
                }
            }
            this.visited[i] = true;

            if (backtrack(arr, stage + 1)) {
                return true;
            }
            // restoring
            this.visited[i] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        PairSum pairSum = new PairSum();
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] nums = line.split(" ");
            int n = Integer.parseInt(nums[0]);
            int[] arr = new int[n * (n - 1) / 2];
            for (int i = 1; i < nums.length; i++) {
                arr[i - 1] = Integer.parseInt(nums[i]);
            }
            sb.append(pairSum.find(n, arr)).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}

