import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 1부터 dp 계산
        int[] dp = new int[N+1];
        dp[1] = 0;

        for (int i = 2; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;

            if (i % 2 == 0) dp[i] = Math.min(dp[i/2] + 1, dp[i]);
            if (i % 3 == 0) dp[i] = Math.min(dp[i/3] + 1, dp[i]);
            dp[i] = Math.min(dp[i-1] + 1, dp[i]);
        }

        System.out.println(dp[N]);
    }

}
