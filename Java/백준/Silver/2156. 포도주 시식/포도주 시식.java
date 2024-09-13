import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] drink = new int[N];
        for (int i = 0; i < N; i++) {
            drink[i] = Integer.parseInt(br.readLine());
        }

        // 현재 포도주를 마실 수 있는지는 2 step 전 마신지 여부가 결정
        // 즉, 총 3 step이 영향을 받음으로 거기서의 최대값을 DP로 계산하면 확장할 수 있음
        // -> 현재 포도주 최대값 = Math.max(현재 안마시기, 1step 전 안마시기, 2step 전 안마시기)
        //                    = Math.max(1step 전 최대값, 2step 전 최대값 + 현재, 3step 최대값 + 이전과 현재)
        int[] dp = new int[N];

        dp[0] = drink[0];
        if (N > 1) dp[1] = dp[0] + drink[1];
        if (N > 2) dp[2] =
                Math.max(dp[1],
                        Math.max(dp[0] + drink[2], drink[2]+drink[1])
                );

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i-1],
                    Math.max(dp[i-2] + drink[i],
                            dp[i-3] + drink[i-1] + drink[i]
                    )
            );
        }


        System.out.println(dp[N-1]);
    }

}
