import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    static boolean[] visit;
    static List<Integer>[] tree;

    static int[][] dp; // 트리의 부분집합의 특정 [정점]에서 [얼리 어답터 여부]에 따른 가중치값, 0: 아님, 1: 얼리어탑터

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 연결된 모든 사람이 얼리어답터여야함 -> 경우의 수: O-X, O-O, X-O
        // -> 트리구조임으로 DFS, DP로 가중치 계산

        int N = Integer.parseInt(br.readLine()); //정점의 수
        visit = new boolean[N+1];
        tree = new List[N+1];
        dp = new int[N+1][2];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            String[] se = br.readLine().split(" ");
            int s = Integer.parseInt(se[0]);
            int e = Integer.parseInt(se[1]);

            tree[s].add(e);
            tree[e].add(s);
        }

        dfs(1); //1번부터 리프노드까지 dp 계산

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int num) {
        visit[num] = true;

        dp[num][0] = 0;
        dp[num][1] = 1;


        for (int next : tree[num]) {
            if (visit[next] == false) {
                dfs(next);
                dp[num][1] += Math.min(dp[next][0], dp[next][1]);
                dp[num][0] += dp[next][1];
            }
        }
    }

}