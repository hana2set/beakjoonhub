import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];

        int[] memory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        // 메모리 값이 큼 -> "비용" 기준 "절약 메모리 최대값"을 DP로 저장
        // 메모리 하나씩 추가하면서 dp 계산
        // 비용 최대값 10000 ( c * n <= 100 * 100 )
        int[][] dp = new int[N+1][10001];
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10001; j++) {
                dp[i+1][j] = Math.max(dp[i][j],
                        j < cost[i]
                                ? dp[i][j]
                                : dp[i][j - cost[i]] + memory[i]);

                if (dp[i+1][j] >= M) answer = Math.min(answer, j);

            }
        }

        System.out.println(answer);

    }

}
