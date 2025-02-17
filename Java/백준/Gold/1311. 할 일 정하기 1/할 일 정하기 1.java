import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int INF = Integer.MAX_VALUE;
        int[][] cost = new int[N][N];
        int[][] dp = new int[N][1 << N]; // [현재 선택할 사람 번호][일 선택 상태(선택된 값 표현)] 의 최소값


        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        // 0번 사람 초기화
        for (int i = 0; i < N; i++) {
            dp[0][1 << i] = cost[0][i]; //자기자신 선택한 경우
        }

        // 1번 ~ N-1번까지 선택
        for (int i = 1; i < N; i++) { // i: 선택할 사람 번호
            for (int j = 0; j < 1<<N; j++) { // j: 현재까지 선택된 상태값. 0000101 -> 0번,2번 사람 선택했음
                if (dp[i-1][j] == INF) continue; //i-1이 선택된 상태여야 다음 i 선택 가능

                for (int k = 0; k < N; k++) { // k번째 일을 선택함
                    if ((j & (1<<k)) != 0) continue; // k가 이전에 이미 선택된 상태라면

                    int next = j|(1 << k);
                    dp[i][next] = Math.min(dp[i][next], dp[i-1][j] + cost[i][k]);

                }

            }
        }



        System.out.print(dp[N-1][(1<<N) - 1]); //마지막에, 모두선택 (1111...)
    }
}