import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    static boolean[] visit;
    static int[] w;
    static List<Integer>[] tree;

    static int[][] dp; // 1이 중심인 부분집합에 [정점 위치]의 [포함 여부]에 따른 가중치의 총합
    static List<Integer> result = new ArrayList<>(); // 결과 정점 집합

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 독립 집합은 쉽게 이야기해서, 간선이 없는 정점들의 집합이다.
        // 즉, DFS로 간선들을 따라가면서 X-O, O-X, X-X 3개의 케이스를 다 따져보면됨.
        // 트리로 주어졌다고 했음으로, 모든 정점이 연결되어있음.
        // -> 편의를 위해 1부터 시작
        // 정점에 속하는 정점을 출력해야함으로
        // dp로 계산 후 역추적-출력 할 예정


        int N = Integer.parseInt(br.readLine());
        visit = new boolean[N+1];
        w = new int[N+1];
        tree = new List[N+1];
        dp = new int[N+1][2];

        String[] pData = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            w[i] = Integer.parseInt(pData[i-1]);
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            String[] se = br.readLine().split(" ");
            int s = Integer.parseInt(se[0]);
            int e = Integer.parseInt(se[1]);

            tree[s].add(e);
            tree[e].add(s);
        }


        dfs(1); // 1부터 탐색

        // dp[1][0] 또는 dp[1][1]이 답
        visit = new boolean[N+1];
        if (dp[1][0] > dp [1][1]) {
            System.out.println(dp[1][0]);
            trace(1, 0);
        } else {
            System.out.println(dp[1][1]);
            trace(1, 1);
        }

        Collections.sort(result);

        System.out.println(result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "))
        );

    }

    private static void trace(int p, int attend) {
        visit[p] = true;

        if (attend == 1) {
            result.add(p);
            for (int next : tree[p]) {
                if (!visit[next]) {
                    trace(next, 0);
                }
            }
        } else {
            for (int next : tree[p]) {
                if (!visit[next]) {
                    if (dp[next][1] > dp[next][0]) {
                        trace(next, 1);
                    } else {
                        trace(next, 0);
                    }
                }
            }
        }

    }

    private static void dfs(int p) {
        visit[p] = true;

        dp[p][0] = 0;
        dp[p][1] = w[p];

        for (int target : tree[p]) {
            if (!visit[target]) {
                dfs(target);

                dp[p][0] += Math.max(dp[target][1], dp[target][0]); // X-O, X-X
                dp[p][1] += dp[target][0]; // O-X
            }
        }

    }

}