import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //  `여러 장들이 연속이 되도록 파일을 합쳐나가고` -> 가까이 있는것만 합칠 수 있음 -> dp로 계산

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] dp = new int[K][K]; // i에서 j까지 데이터 병합의 최소값
            int[][] sum = new int[K][K]; // i에서 j까지 데이터 병합 사이즈

            sum[0][0] = seq[0];
            // 병합 사이즈 미리 계산
            for (int i = 1; i < K; i++) {
                sum[0][i] = sum[0][i-1] + seq[i];
            }
            for (int i = 1; i < K; i++) {
                for (int j = i; j < K; j++) {
                    sum[i][j] = sum[0][j] - sum[0][i-1];
                }
            }

            // 길이별로 dp를 구하는게 좋음 -> 1~3의 dp을 구하려면 1~2, 2~3의 dp를 알아야하기 때문
            // 데이터 병합 사이즈가 비용임으로, "기존 dp값 + 병합사이즈" 로 다음 dp 계산
            for (int len = 1; len < K; len++) {
                for (int i = 0; i < K-len; i++) {
                    // i부터 len 거리 dp 계산
                    int j = i+len;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j] + sum[i][j]); // "기존 dp값 + 병합사이즈"
                    }
                }
            }


            System.out.println(dp[0][K-1]);
        }

    }
}
