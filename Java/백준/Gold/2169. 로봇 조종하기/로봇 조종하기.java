import java.io.*;
import java.util.*;

class Main {

    static int[][] arr;
    static int[][] dp;
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // DP 풀이
        // N -> y좌표, M -> x좌표
        // DFS 계산시 시간 초과
        // y축은 상단에서밖에 못옴으로,
        // 임시로 좌상측에서 오는 최대값, 우상단측에서 오는 최대값을 계산하여
        // 더 높은값을 dp에 추가하면 해결
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp = new int[N][M];

        // 첫번째는 좌측에서 오는 경우의 수 밖에 없음
        dp[0][0] = arr[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i-1] + arr[0][i];
        }

        for (int y = 1; y < N; y++) {
            int[] left = new int[M];
            int[] right = new int[M];

            // 좌상단
            left[0] = dp[y-1][0] + arr[y][0];
            for (int x = 1; x < M; x++) {
                left[x] = Math.max(dp[y-1][x], left[x-1]) + arr[y][x];
            }

            // 우상단
            right[M-1] = dp[y-1][M-1] + arr[y][M-1];
            for (int x = M-2; x >= 0; x--) {
                right[x] = Math.max(dp[y-1][x],right[x+1]) + arr[y][x];
            }

            // 둘중 최대값
            for (int x = 0; x < M; x++) {
                dp[y][x] = Math.max(left[x], right[x]);
            }
        }

        System.out.println(dp[N-1][M-1]);

    }

}