import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static int[][] cost;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        cost = new int[N][3];
        int maxValue = 987_654_321;
        int answer = maxValue;

        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        for (int i = 0; i < 3; i++) {
            // 마지막 색칠할 때 첫번째 색칠한 숫자까지 dp에 넣으면 배열이 복잡해짐.
            // 시작때 첫 색을 결정
            dp = new int[N][3];
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[j], maxValue);
            }

            // 선택한 색상만 dp값 추가
            dp[0][i] = cost[0][i];


            for (int j = 1; j < N; j++) {

                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + cost[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if (i==j) continue; // 같은 색 무시
                answer = Math.min(dp[N-1][j], answer);
            }

        }

        System.out.println(answer);
    }

}