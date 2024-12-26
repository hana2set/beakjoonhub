import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int[] parants = new int[1_000_000];
    static Node[] nodes = new Node[1_000_000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전위 순회 방식으로 노드는 감소했다 증가했다를 반복
        // 감소할때는 왼쪽으로 탐색
        // 증가할때는 우측으로 탐색
        // 증감 추세로 노드 관계 파악이 가능

        int root = Integer.parseInt(br.readLine());
        int before = root;
        nodes[before] = new Node();
        Stack<Integer> q = new Stack<>(); // 부모 찾기용
        q.add(before);
        while (true) {
            String input = br.readLine();
            if (input == null || input.length() == 0) break;
            int cur = Integer.parseInt(input);

            if (cur == 0) break;

            if (nodes[cur] == null) {
                nodes[cur] = new Node();
            }

            // 왼쪽노드 탐색
            if (before > cur) {
                parants[cur] = before;
                nodes[before].left = cur;
            } else {
                // 오른쪽 노드임으로 부모보다 커야하고, 부모의 부모보다는 작아야함
                int parant = root;
                while (!q.isEmpty() && q.peek() < cur) {
                    parant = q.pop();
                }
                parants[cur] = parant;
                nodes[parant].right = cur;
            }

            q.add(cur);

            before = cur;
        }


        // 후위 탐색
        StringBuilder sb = postOrder(root);



        System.out.println(sb);
    }

    private static StringBuilder postOrder(int index) {
        StringBuilder sb = new StringBuilder();

        if (nodes[index].left != 0) sb.append(postOrder(nodes[index].left));
        if (nodes[index].right != 0) sb.append(postOrder(nodes[index].right));
        sb.append(index).append("\n");

        return sb;
    }

}

class Node {
    int left;
    int right;
}