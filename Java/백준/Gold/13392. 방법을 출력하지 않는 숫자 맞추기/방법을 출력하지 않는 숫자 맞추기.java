import java.util.Scanner;

public class Main {
    static int[][] dp;
    static int N;
    static int[] from;
    static int[] to;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        String A = sc.next();
        String B = sc.next();

        from = new int[N];
        to = new int[N];

        for (int i = 0; i < N; i++) {
            from[i] = A.charAt(i) - '0';
            to[i] = B.charAt(i) - '0';
        }

        dp = new int[N + 1][10];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) continue;

                int current = (from[i] + j) % 10;
                int left = (to[i] - current + 10) % 10;
                int right = (10 - left) % 10;

                // 왼쪽으로 돌리는 경우
                int leftRot = (j + left) % 10;
                dp[i + 1][leftRot] = Math.min(dp[i + 1][leftRot], dp[i][j] + left);

                // 오른쪽으로 돌리는 경우
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + right);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < 10; j++) {
            answer = Math.min(answer, dp[N][j]);
        }

        System.out.println(answer);
    }
}