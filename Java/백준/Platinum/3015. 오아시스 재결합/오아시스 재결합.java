import java.io.*;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] height = new int[N]; //키
        long count = 0;

        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }

        // A,B보다 키 큰사람이 없으면 됨 ->
        // 1. 계속 올라가거나 내려감: 1개씩 증가. (가운데 A,B보다 큰값 포함됨)
        // 2. 같은 값이 존재: 좌우에 해당 값보다 큰 값 등장시 같은값 갯수 + 같은값 조합 만큼 카운팅됨
        // -> 같은 값은 계산하기 어려움으로,
        // 값 추가시(push)에는 (기존 같은값 갯수: 자기끼리 연결) + (1개: 지금값-기존 높은 값)
        // 값 제거시(pop)에는 (기존 같은값 갯수: 다음 높은값이랑 연결)
        // 로 계산하는게 제일 좋음
        // 따라서 스택으로 내림차순으로 저장하고 "올라가는 경우"를 파악해, 해당 값까지 등장한 갯수 합을 구하면 된다.
        // -> 남아있는 스택의 값은 뒷수의 갯수로 결정됨 (앞이 큰값임으로)

        Stack<Node> s = new Stack<>();

        for (int i = 0; i < height.length; i++) {
            Node node = new Node(height[i], 1);
            while (!s.isEmpty() && s.peek().height <= height[i]) { //값이 커질 경우
                Node p = s.pop();
                count += p.count; // 같은 값 -> 자기끼리 연결

                if (p.height == height[i])
                    node.count = p.count+1;
            }

            // 하나 추가시 무조건 연결됨
            if (!s.isEmpty())
                count++;

            s.push(node);

        }

        System.out.println(count);

    }

    private static class Node {
        int height;
        int count;

        public Node(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }

}
