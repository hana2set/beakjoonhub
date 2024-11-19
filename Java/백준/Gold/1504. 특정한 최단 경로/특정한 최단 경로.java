import java.io.*;
import java.util.*;

class Main {

    private static int N, E;
    private static List<Node>[] node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        // 간선 정보
        node = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);

            node[a].add(new Node(b, c)); // (u, v) 거리
            node[b].add(new Node(a, c)); // (v, u) 거리
        }

        input = br.readLine().split(" ");
        int v1 = Integer.parseInt(input[0]);
        int v2 = Integer.parseInt(input[1]);

        // v1,v2를 지나야 함으로, 다익스트라 알고리즘을 분할해
        // 1 -> v1 -> v2 -> N,
        // 1 -> v2 -> v1 -> N 을 계산해 최소값 계산
        int dist1 = dijkstra(1, v1, v2, N);
        int dist2 = dijkstra(1, v2, v1, N);

        if (dist1 == -1)
            if (dist2 == -1) System.out.println(-1);
            else System.out.println(dist2);
        else
            if (dist2 == -1) System.out.println(dist2);
            else System.out.println(Math.min(dist1,dist2));

    }

    private static int dijkstra(int i, int v1, int v2, int n) {
        int dist1v1 = dijkstra(1, v1);
        if (dist1v1 == -1) return -1;

        int distv1v2 = dijkstra(v1, v2);
        if (distv1v2 == -1) return -1;

        int distv2n = dijkstra(v2, n);
        if (distv2n == -1) return -1;

        return dist1v1 + distv1v2 + distv2n;
    }

    private static int dijkstra(int s, int n) {
        int[] weight = new int[N + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[s] = 0;

        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.w));
        q.add(new Node(s, 0));

        while (!q.isEmpty()) {
            Node start = q.poll();

            if (weight[start.p] < start.w) continue;

            for (Node line : node[start.p]) {
                if (weight[line.p] > start.w + line.w) { // 짧은 경로 발견
                    weight[line.p] = start.w + line.w;
                    q.add(new Node(line.p, weight[line.p]));
                }
            }
        }

        return weight[n] == Integer.MAX_VALUE ? -1 : weight[n];
    }
}

class Node {
    int p;
    int w;

    public Node(int p, int w) {
        this.p = p;
        this.w = w;
    }
}