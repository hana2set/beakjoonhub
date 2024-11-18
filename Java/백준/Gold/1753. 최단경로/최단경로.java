import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        int K = Integer.parseInt(br.readLine());

        int[] weight = new int[V + 1];
        weight[K] = 0;

        Arrays.fill(weight, Integer.MAX_VALUE);

        List<Node>[] node = new ArrayList[V + 1];

        for (int i = 0; i < V+1; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            String[] line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);
            int w = Integer.parseInt(line[2]);

            node[u].add(new Node(v, w)); // (u, v) 가중치 추가
        }

        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.w));
        q.add(new Node(K, 0));

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

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < weight.length; i++) {
            if (i == K) sb.append("0\n");
            else if (weight[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(weight[i]+"\n");
        }

        System.out.println(sb);

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