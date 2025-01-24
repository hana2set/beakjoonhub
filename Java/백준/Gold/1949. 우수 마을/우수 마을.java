import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    static boolean[] visit;
    static int[] village;
    static List<Integer>[] street;

    static int[][] dp; // 트리의 부분집합의 [마을]에서 [우수마을 여부]에 따른 가중치값, 0: 아님, 1: 우수마을

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 우수마을의 총 합이 최대로 -> DFS, DP로 최대치 계산

        int N = Integer.parseInt(br.readLine()); //정점의 수
        visit = new boolean[N+1];
        village = new int[N+1];
        street = new List[N+1];
        dp = new int[N+1][2];

        String[] villiageStr = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            village[i] = Integer.parseInt(villiageStr[i-1]);
            street[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            String[] se = br.readLine().split(" ");
            int s = Integer.parseInt(se[0]);
            int e = Integer.parseInt(se[1]);

            street[s].add(e);
            street[e].add(s);
        }

        dfs(1); //1번부터 리프노드까지 dp 계산

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int num) {
        visit[num] = true;

        dp[num][0] = 0;
        dp[num][1] = village[num];

        for (int next : street[num]) {
            if (visit[next] == true) continue;

            dfs(next);
            dp[num][0] += Math.max(dp[next][1], dp[next][0]); 
            dp[num][1] += dp[next][0];

            // 여기서 X-(X...X)가 생기지 않는 이유:
            // max값으로 계산할 경우,
            // -> 자식노드가 모두 X이면, 해당 노드의 가중치는 자식노드의 합보다 높음
            // -> 해당 노드가 O가 됨으로, O-X
        }

    }

}