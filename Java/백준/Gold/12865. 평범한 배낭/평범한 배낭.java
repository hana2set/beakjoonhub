import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] scan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = scan[0];
        int K = scan[1];
        int[] W = new int[N];
        int[] V = new int[N];

        for (int i = 0; i < N; i++) {
            scan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            W[i] = scan[0];
            V[i] = scan[1];
        }

        // 짐을 풀 수 없음으로 (무게 고정), DP로 해결해야함
        // N, K로 크기의 DP를 구성해, N번째 짐을 넣었을 경우, K 무게에 따른 가치 최대값을 누적 계산.
        int[][] dp = new int[N][K+1];

        for (int i = 0; i < N; i++) {
            // i 번째 짐을 추가
            for (int j = 0; j <= K; j++) {
                if (i == 0) {
                    if (j >= W[i]) {
                        dp[i][j] = V[i];
                    }
                    continue;
                }

                if (j < W[i]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    // i번째 짐 넣기 VS 안넣기 최대값 비교
                    dp[i][j] = Math.max(dp[i-1][j-W[i]] + V[i], dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[N-1][K]);
    }
}