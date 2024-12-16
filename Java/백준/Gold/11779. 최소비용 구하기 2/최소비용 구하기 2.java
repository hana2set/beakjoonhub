import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 한정점에서의 최단경로 -> 다익스트라

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<int[]>[] roads = new List[n+1];

        for (int i = 1; i < roads.length; i++) {
            roads[i] = new ArrayList();
        }

        while (m-- > 0) {
            int[] bus = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            roads[bus[0]].add(new int[]{bus[1], bus[2]});
        }

        int[] goal = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        PriorityQueue<Node> q = new PriorityQueue<Node>(Comparator.comparingInt(a -> a.length));
        q.add(new Node(0, goal[0], ""));
        Node result = null;

        result = getNode(roads, goal, q);

        String goalStr = (result.list + " " + result.pos).trim();

        System.out.println(result.length);
        System.out.println(goalStr.split(" ").length);
        System.out.println(goalStr);

    }

    private static Node getNode(List<int[]>[] roads, int[] goal, PriorityQueue<Node> q) {
        boolean[] visit = new boolean[roads.length + 1];

        while (q.peek().pos != goal[1]) {
            Node cur = q.poll();

            if (visit[cur.pos]) continue;

            visit[cur.pos] = true;

            for (int[] road : roads[cur.pos]) {
                q.add(new Node(cur.length + road[1], road[0], cur.list + " " + cur.pos));
            }
        }

        return q.peek();
    }

}


class Node {

    int length = 0;
    int pos = 0;
    String list;

    public Node(int length, int pos, String list) {
        this.length = length;
        this.pos = pos;
        this.list = list;
    }

}