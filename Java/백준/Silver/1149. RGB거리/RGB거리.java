import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][3];

        int[] f = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp[0][0] = f[0];
        dp[0][1] = f[1];
        dp[0][2] = f[2];

        int index = 1;
        while (index < N) {
            int[] ex = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dp[index][0] = Math.min(dp[index-1][1] + ex[0], dp[index-1][2] + ex[0]);
            dp[index][1] = Math.min(dp[index-1][0] + ex[1], dp[index-1][2] + ex[1]);
            dp[index][2] = Math.min(dp[index-1][0] + ex[2], dp[index-1][1] + ex[2]);

            index++;
        }

        int[] result = dp[N-1];
        Arrays.sort(result);

        System.out.println(result[0]);

    }

}