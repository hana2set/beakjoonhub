import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 계단수는 앞 숫자 +-1 임으로, 자리수가 늘어날때마다 기하급수적으로 갯수가 증가
        // 직접 구하지 말고 자리수(index)를 높이면서 올 수 있는 숫자의 갯수로 다음에 올수 있는 숫자 갯수를 예측
        int[][] dp = new int[N][10]; //자리수에서 숫자 갯수
        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i-1][1] % 1_000_000_000;
            for (int j = 1; j < 9; j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1_000_000_000;
            }
            dp[i][9] = dp[i-1][8] % 1_000_000_000;
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N-1][i];
        }

        System.out.println(sum % 1_000_000_000);
    }

}
