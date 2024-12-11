import java.io.*;
import java.util.*;

class Main {

    static int[][] event;
    static int[][] dp;
    static int N, W;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 일반적인 완전탐색, 전체 DP 계산 시 시간초과 (2^W)
        // 두 차가 사건 처리시 해당 위치에 있다는 점을 이용해,
        // 각 차가 "해결한 사건을 중심"으로 dp 구성
        // 그리고 완전탐색을 하되 dp를 통해 반복 수를 줄일 수 있음
        // dp[i][j]: 첫번째 차가 i번째, 두번째 차가 j번째 사건을 처리했을 때, 최단 경로

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        event = new int[W+1][2];
        dp = new int[W+1][W+1];

        for (int i = 1; i <= W; i++) {
            String[] tempInput = br.readLine().split(" ");
            event[i] = new int[]{Integer.parseInt(tempInput[0]), Integer.parseInt(tempInput[1])};
        }

        System.out.println(dfs(1, 0, 0));

        // 거리로 움직인 차 계산
        int one = 0, two = 0;
        for (int i = 1; i <= W; i++) {
            int dist = distance(1, one, i);

            if (dp[one][two] - dist == dp[i][two]) { //첫 차가 움직인 거리와 같으면
                one = i;
                System.out.println(1);
            } else {
                two = i;
                System.out.println(2);
            }

        }
    }

    // fir: 첫번째 차 마지막 해결 사건
    // sec: 두번째 차 마지막 해결 사건
    // ev 번째 사건이 발생할 때 현재 차 위치에서 최단 경로 계산
    private static int dfs(int ev, int fir, int sec) {
        if (ev > W) return 0;

        if (dp[fir][sec] == 0) {
            int one = dfs(ev+1, ev, sec) + distance(1, fir, ev);
            int two = dfs(ev+1, fir, ev) + distance(2, sec, ev);

            dp[fir][sec] = Math.min(one, two);
        }

        return dp[fir][sec];

    }


    // start: 시작 사건
    // end : 종료 사건
    private static int distance(int carNum, int start, int end) {
        int s_x = event[start][0];
        int s_y = event[start][1];
        int e_x = event[end][0];
        int e_y = event[end][1];

        // 시작전 위치 타입이 2개
        if (start == 0) {
            if (carNum == 1) {
                s_x = 1;
                s_y = 1;
            } else {
                s_x = N;
                s_y = N;
            }
        }

        return Math.abs(s_x - e_x) + Math.abs(s_y - e_y);
    }
}