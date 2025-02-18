import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N;
    static int INF;
    static int allVisit;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 외판원 순회 문제 (TSP, Traveling Salesman problem)

        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[N][1<<N]; // dp[현재위치][각지점방문여부]
        INF = 1_000_000 * N;
        allVisit = (1<<N) - 1;



        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < N; j++) {
                // 갈수없는 값 -> INF로
                if (cost[i][j] == 0) cost[i][j] = INF;
            }
        }

        // 초기화
        // INF로 초기화할 경우, 특정 도시에서 방문할 곳이 없을 때 dp 반환시, INF를 반환하게 되고
        // INF는 미방문도시임으로, 해당 도시를 방문할때마다 루프를 될게됨 -> 시간초과
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 모든 경로를 순회함으로, 시작 위치는 중요하지 않음 -> 0번 시작
        // visit값은 0000...01 (0번 방문한 상태)
        System.out.print(tsp(0, 1));
    }

    private static int tsp(int city, int state) {
        // 모든 도시를 순회하였다면 마지막 방문은 0번 도시로 고정
        if (state == allVisit) {
            return cost[city][0];
        }

        //이미 방문했다면
        if (dp[city][state] != -1) return dp[city][state];
        dp[city][state] = INF;

        for (int i = 0; i < N; i++) {
            // i 도시를 방문할 수 없거나, 이미 방문한 경우
            if (cost[city][i] == INF || (state & (1<<i)) != 0) continue;

            int next = state | (1<<i);
            dp[city][state] = Math.min(dp[city][state], tsp(i, next) + cost[city][i]);
        }

        return dp[city][state]; // -> 0에서 0으로 순회한 값 반환
    }
}