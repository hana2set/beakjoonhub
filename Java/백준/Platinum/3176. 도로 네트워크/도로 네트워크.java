import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, degree;
    static List<Node>[] tree;
    static int[] level;
    static int[][] parent; //[2^n]번쨰 조상 [n]노드에서의
    static int[][] minRoad; //[2^n]번쨰 조상 ([n]노드에서) 과의 최소 거리
    static int[][] maxRoad;

    static int min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기화
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i*=2) degree++; // N값에 따른 최대 차수 결정

        tree = new List[N+1];
        level = new int[N+1];
        parent = new int[degree][N+1];
        minRoad = new int[degree][N+1];
        maxRoad = new int[degree][N+1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 트리 초기화
        for (int i = 0; i < N-1; i++) {
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
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);

            getParent(a, b);
            sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.println(sb);

    }

    // level, parent, road 초기값 생성
    private static void init(int node, int index) {
        level[node] = index;

        for (Node next : tree[node]) {
            if (level[next.target] != 0) continue; //미방문시

            parent[0][next.target] = node;
            minRoad[0][next.target] = next.cost;
            maxRoad[0][next.target] = next.cost;
            init(next.target, index+1);
        }
    }

    // 차수별 parent 생성
    private static void setData() {
        for (int i = 1; i < degree; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];

                minRoad[i][j] = Math.min(minRoad[i-1][j], minRoad[i-1][parent[i-1][j]]);
                maxRoad[i][j] = Math.max(maxRoad[i-1][j], maxRoad[i-1][parent[i-1][j]]);
            }
        }
    }

    private static void getParent(int a, int b) {
        int l = level[a] < level[b] ? a : b;
        int r = level[a] < level[b] ? b : a;

        min = Integer.MAX_VALUE;
        max = -1;

        // level 맞추기
        int dif = level[r]-level[l];
        for (int i = 0; i < degree; i++) {
            if (((1<<i) & dif) > 0) { //결합법칙 성립 -> 각자 계산
                min = Math.min(min, minRoad[i][r]);
                max = Math.max(max, maxRoad[i][r]);

                r = parent[i][r]; //i 차수만큼 부모 거슬러 올라가기
            }
        }

        if (l == r)
            return;

        for (int i = degree -1; i >= 0; i--) {
            if (parent[i][l] != parent[i][r]) {

                min = Math.min(min, Math.min(minRoad[i][l], minRoad[i][r]));
                max = Math.max(max, Math.max(maxRoad[i][l], maxRoad[i][r]));

                l = parent[i][l];
                r = parent[i][r];
            }
        }

        min = Math.min(min, Math.min(minRoad[0][l], minRoad[0][r]));
        max = Math.max(max, Math.max(maxRoad[0][l], maxRoad[0][r]));
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