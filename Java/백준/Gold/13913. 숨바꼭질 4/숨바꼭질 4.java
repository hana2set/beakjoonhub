import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int[] dp = new int[100_001];
        int[] before = new int[100_001];
        int result = 0;


        Queue<Node> q = new LinkedList<>();

        q.add(new Node(N, 0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.position > 100_000 || node.position < 0) continue;

            if (dp[node.position] != 0) continue;

            dp[node.position] = node.time;
            before[node.position] = node.before;

            if (node.position == K) {
                result = node.time;
                break;
            }

            q.add(new Node(node.position+1, node.time+1, node.position));
            if (node.position > 0) q.add(new Node(node.position-1, node.time+1, node.position));
            q.add(new Node(node.position*2, node.time+1, node.position));
        }

        System.out.println(result);

        Stack<Integer> s = new Stack<>();
        int index = K;
        s.add(index);
        while (index != N) {
            index = before[index];
            s.push(index);
        }

        while (!s.isEmpty()) {
            sb.append(s.pop() + " ");
        }
        System.out.println(sb.toString().trim());

    }

}

class Node {
    int position;
    int time;
    int before;
    List<Integer> list = new ArrayList<>();

    public Node(int position, int time, int before) {
        this.position = position;
        this.time = time;
        this.before = before;
    }
}