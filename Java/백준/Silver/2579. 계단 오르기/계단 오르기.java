import java.util.*;

class Main {

    static int N;
    static int[] stairs;
    static int[][] dp; //계단번호, 횟수
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        stairs = new int[N+1];

        // 시간 짧은순으로 보내는게 좋음 -> 정렬 후 합 계산
        for (int i = 1; i <= N; i++) {
            stairs[i] = sc.nextInt();
        }

         dp = new int[N+1][3];

        dfs(0, 0, 0);

        System.out.println(Math.max(dp[N][1], dp[N][2]));
    }

    // index -> 계단 번호, count = 연속된 계단 밝은 갯수
    public static void dfs(int point, int index, int count) {
        if (dp[index][count] != 0 && dp[index][count] >= point) return;

        dp[index][count] = point;

        if (index == N) return;

        if (index+1 <= N && count != 2) dfs(point+stairs[index+1], index+1, count+1);
        if (index+2 <= N) dfs(point+stairs[index+2], index+2, 1);
    }
}