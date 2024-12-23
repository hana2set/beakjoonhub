import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main {

    static Map<String, Node> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        map = new HashMap<>();
        while (N-- > 0) {
            String[] input = br.readLine().split(" ");

            map.put(input[0], new Node(input[1], input[2]));
        }

        sb = preOrder(sb, "A");
        System.out.println(sb);
        sb.setLength(0);

        sb = inorderOrder(sb, "A");
        System.out.println(sb);
        sb.setLength(0);

        sb = postorderOrder(sb, "A");
        System.out.println(sb);
        sb.setLength(0);

    }


    private static StringBuilder preOrder(StringBuilder sb, String nodeStr) {
        if (nodeStr.equals(".")) return sb;
        Node curNode = map.get(nodeStr);

        // 탐색순서 - root, L, R
        sb.append(nodeStr);
        preOrder(sb, curNode.left);
        preOrder(sb, curNode.right);

        return sb;
    }

    private static StringBuilder inorderOrder(StringBuilder sb, String nodeStr) {
        if (nodeStr.equals(".")) return sb;
        Node curNode = map.get(nodeStr);

        // 탐색순서 - L, root, R
        inorderOrder(sb, curNode.left);
        sb.append(nodeStr);
        inorderOrder(sb, curNode.right);

        return sb;

    }

    private static StringBuilder postorderOrder(StringBuilder sb, String nodeStr) {
        if (nodeStr.equals(".")) return sb;
        Node curNode = map.get(nodeStr);

        // 탐색순서 - L, R, root
        postorderOrder(sb, curNode.left);
        postorderOrder(sb, curNode.right);
        sb.append(nodeStr);

        return sb;
    }

}

class Node {
    String left;
    String right;

    public Node(String left, String right) {
        this.left = left;
        this.right = right;
    }
}