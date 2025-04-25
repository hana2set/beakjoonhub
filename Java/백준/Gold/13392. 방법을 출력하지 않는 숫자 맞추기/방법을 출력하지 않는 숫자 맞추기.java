import java.io.*;
import java.util.*;

class Main {

    static int[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // DP 풀이
        // 왼쪽으로 돌렸을 경우 다음 숫자에 영향이 있음.
        // 오른쪽으로 돌렸을때는 비교적으로 간단함 ( 0~9회 돌리면 해당 숫자가 나옴, 다른 숫자에 영향 없음 )
        // 따라서 왼쪽으로 돌렸을 때, 그 횟수를 %10 해서 지속적으로 저장하면 
        // 몇 회 오른쪽으로 돌려야하는지 쉽게 파악이 가능
        // 즉 "DP[나사 번호][왼쪽으로 돌린횟수] = 전체 돌린 최소 횟수"를 계산하면 됨 (나사번호를 늘리면서)
        // 
        int N = Integer.parseInt(br.readLine());

        // data 입력

        int[] cur = new int[N];
        int[] goal = new int[N];

        char[] data = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            cur[i] = data[i]-'0';
        }

        data = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            goal[i] = data[i]-'0';
        }

        dp = new int[N+1][10];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        //0,0에서 시작
        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) { // left 횟수(j) 증가하면서 계산
                if (dp[i][j] == Integer.MAX_VALUE) continue; //접근 불가능하면 탐색하지 않음

                int curNum = (cur[i] + j) % 10;

                // 왼쪽으로 돌림
                int left = (goal[i] - curNum + 10) % 10;
                int leftSpinCount = (j + left) % 10;
                dp[i+1][leftSpinCount] = Math.min(dp[i+1][leftSpinCount], dp[i][j] + left);

                // 오른쪽
                int right = (10 - left) % 10;
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + right);
            }
        }


        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            result = Math.min(result, dp[N][i]);
        }

        System.out.println(result);
    }

}