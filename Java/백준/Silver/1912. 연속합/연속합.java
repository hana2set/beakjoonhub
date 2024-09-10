import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 각 숫자에서 나올 수 있는 최대값을 계산하면 됨.
        // 계산법은 max(이전 최대값 + 현재값, 현재값)
        int[] dp = new int[N];
        dp[0] = seq[0];

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1] + seq[i], seq[i]);
        }

        // 최고 높은 숫자
        Arrays.sort(dp);

        System.out.println(dp[N-1]);
    }

}
