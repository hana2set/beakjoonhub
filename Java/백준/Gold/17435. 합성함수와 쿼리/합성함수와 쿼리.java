import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int maxPow = 19; // 2^19 = 524288

        int m = Integer.parseInt(br.readLine());
        int[][] dp = new int[maxPow+1][m+1];

        int[] fResult = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= m; i++) {
            dp[0][i] = fResult[i-1];
        }

        for (int i = 1; i <= maxPow; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][dp[i-1][j]];
            }
        }

        int Q = Integer.parseInt(br.readLine());

        while (Q-- > 0) {
            String[] data = br.readLine().split(" ");
            int n = Integer.parseInt(data[0]);
            int x = Integer.parseInt(data[1]);

            for (int b = 0; b < maxPow; b++) {
                if ((n & (1 << b)) > 0) {
                    x = dp[b][x];
                }
            }

            sb.append(x).append("\n");
        }

        System.out.println(sb);

    }

}
