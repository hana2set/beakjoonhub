import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, degree;
    static List<Node>[] tree;
    static int[] level;
    static long[] cost; // 루트부터의 총 비용
    static int[][] parent; //[2^n]번쨰 조상 [n]노드에서의

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // lca

        // 초기화
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i *= 2) degree++; // N값에 따른 최대 차수 결정

        tree = new List[N + 1];
        level = new int[N + 1];
        parent = new int[degree][N + 1];
        cost = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 트리 초기화
        for (int i = 0; i < N - 1; i++) {
            String[] data = br.readLine().split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);
            int cost = Integer.parseInt(data[2]);

            tree[a].add(new Node(b, cost));
            tree[b].add(new Node(a, cost));
        }

        init(1, 1);
        setData();

        // 출력
        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            String[] data = br.readLine().split(" ");
            int function = Integer.parseInt(data[0]);
            int u = Integer.parseInt(data[1]);
            int v = Integer.parseInt(data[2]);

            if (function == 1) {
                sb.append(getCost(u, v));
            } else {
                int k = Integer.parseInt(data[3]);
                sb.append(getNode(u, v, k));
            }

            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static int getNode(int u, int v, int k) {
        int lca = getLca(u, v);
        int dif = level[u] - level[lca] + 1;

        int p, cnt;

        if (dif == k) return lca;

        if (dif > k) {
            // u에서 cnt만큼 조상
            p = u;
            cnt = k-1;
        } else {
            // v에서 cnt만큼 조상
            p = v;
            cnt = level[v] - level[lca] + (dif - k);
        }

        for (int i = 0; i < degree; i++) {
            if (((1<<i) & cnt) > 0) {
                p = parent[i][p];
            }
        }

        return p;

    }


    private static long getCost(int u, int v) {
        int lca = getLca(u, v);
        return cost[u] + cost[v] - cost[lca]*2;
    }


    private static int getLca(int u, int v) {
        // level: l < r
        int l = level[u] < level[v] ? u : v;
        int r = level[u] < level[v] ? v : u;

        // 공통 level까지 값 일치시키기
        int dif = level[r]-level[l];
        for (int i = 0; i <= degree; i++) {
            if (((1<<i) & dif) > 0) {
                r = parent[i][r];
            }
        }

        if (l == r) return l;

        for (int i = degree-1; i >= 0; i--) {
            if (parent[i][l] != parent[i][r]) {
                l = parent[i][l];
                r = parent[i][r];
            }

        }

        return parent[0][l];

    }

    // level, parent, road 초기값 생성
    private static void init(int node, int index) {
        level[node] = index;

        for (Node next : tree[node]) {
            if (level[next.target] != 0) continue; //미방문시

            parent[0][next.target] = node;
            cost[next.target] = cost[node] + next.cost;
            init(next.target, index + 1);
        }
    }

    // 차수별 parent 생성
    private static void setData() {
        for (int i = 1; i < degree; i++) {
            for (int j = 1; j <= N; j++) {
                // 2^(n-1)번째 조상의 2^(n-1)번째 조상 = 2^n 번째 조상
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }
}


class Node {
    int target;
    int cost;

    public Node(int target, int cost) {
        this.target = target;
        this.cost = cost;
    }
}