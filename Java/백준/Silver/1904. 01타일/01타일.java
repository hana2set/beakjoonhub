import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[1_000_001];

        // 경우의 수 = (n-1의 경우의 수 + '1') + (n-2의 경우의 수 + '00')
        // 위 두 경우는 겹치지 않고, 모든 경우의 수를 아우름

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 5;

        for (int i = 5; i < N+1; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 15746;
        }

        System.out.println(dp[N]);
    }

}
