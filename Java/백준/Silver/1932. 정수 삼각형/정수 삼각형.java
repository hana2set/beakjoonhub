import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][N];

        dp[0][0] = Integer.parseInt(br.readLine());

        int index = 1;
        while (index < N) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            dp[index][0] = dp[index-1][0] + row[0];
            for (int i = 1; i <= index; i++) {
                dp[index][i] = Math.max(dp[index-1][i], dp[index-1][i-1]) + row[i];
            }

            index++;
        }

        Arrays.sort(dp[N-1]);

        System.out.println(dp[N-1][N-1]);

    }

}