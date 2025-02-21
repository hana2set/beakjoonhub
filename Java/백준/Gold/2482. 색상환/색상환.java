import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][K+1]; // n개 색이 있을때 k개를 선택하는 경우의 수
        long mod = 1_000_000_003;


        // i개 중 1개 선택 = i
        // 선택하지 않는 경우의 수 = 1
        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }

        // 경우의 수: dp[n][k] = dp[n-2][k-1] + dp[n-1][k] <- 첫수를 선택하는 것 + 선택하지 않는 것
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= Math.min(K, i/2); j++) { // n/2 이상 선택 불가능 (번갈아가면서 선택해야함)
                dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % mod;
            }
        }


        System.out.println(dp[N][K]);
    }

}