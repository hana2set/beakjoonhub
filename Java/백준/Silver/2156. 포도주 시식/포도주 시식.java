import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }

        int[] drink = new int[N];
        for (int i = 0; i < N; i++) {
            drink[i] = Integer.parseInt(br.readLine());
        }


        // 현재 포도주를 마실 수 있는지는 2 step 전 마신지 여부가 결정
        // 즉, 총 3 step이 영향을 받음으로 거기서의 최대값을 DP로 계산하면 확장할 수 있음
        // 현재 포도주를 마셨을때, 안마셨을때의 최대값을 DP로 출력
        // -> 현재 포도주 마셨을때 최대값 = Math.max(2step 전 안마신 포도주값 + 이전과 현재의 합, 1step 전 안마신 + 현재)
        // -> 현재 포도주 안마셨을때 최대값 = Math.max(1step 전 마셨을때 최대값, 2step 전 마셨을때 최대값) <- 2 step 중 한번은 안마셔야함
        int[][] dp = new int[N][2]; //0: 마심, 1: 안마심

        dp[0][0] = drink[0];
        dp[1][0] = drink[1] + dp[0][0];
        dp[1][1] = dp[0][0];

        for (int i = 2; i < N; i++) {
            dp[i][0] = Math.max(drink[i] + dp[i-1][1], drink[i] + drink[i-1] + dp[i-2][1]);
            dp[i][1] = Math.max(dp[i-1][0], dp[i-2][0]);
        }


        System.out.println(Math.max(dp[N-1][0],dp[N-1][1]));
    }

}
