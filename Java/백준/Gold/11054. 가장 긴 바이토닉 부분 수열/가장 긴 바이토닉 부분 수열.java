import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 바이토닉 정렬 찾아보기

        // 좌측 방향 DP + 우측 방향 DP = 바이토닉 수열 최대값
        // dp 계산법 - 현재 seq값 보다 작은 이전의 seq 값의 dp+1의 최대값. LIS 11053번
        int[] lDp = new int[N];
        int[] rDp = new int[N];

        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i]) max = Math.max(lDp[j], max);
            }

            lDp[i] = max+1;
        }

        for (int i = N-1; i >= 0; i--) {
            int max = 0;
            for (int j = N-1; j > i; j--) {
                if (seq[j] < seq[i]) max = Math.max(rDp[j], max);
            }

            rDp[i] = max+1;
        }

        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = lDp[i] + rDp[i]-1;
        }

        Arrays.sort(dp);

        System.out.println(dp[N-1]);
    }

}
