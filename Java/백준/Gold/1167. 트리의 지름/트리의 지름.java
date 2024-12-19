import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int maxPoint = 0;
    static int maxValue = 0;
    static List<Node>[] node;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 지름은 가장 긴 child 노드에서 두번째로 긴 child 노드 길이
        // 1. 아무 정점에서 제일 먼 정점 계산
        // 2. 그 정점에서 가장 먼 정점/과의 길이 계산 -> 지름
        int V = Integer.parseInt(br.readLine());
        node = new List[V+1];
        dp = new int[V+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < V; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            List<Node> list = new ArrayList<>();
            int idx = 1;
            while (input[idx] != -1) {
                list.add(new Node(input[idx], input[idx+1]));
                idx += 2;
            }

            node[input[0]] = list;
        }

        dfs(1, 0);

        // 초기화
        Arrays.fill(dp, Integer.MAX_VALUE);
        maxValue = 0;

        dfs(maxPoint, 0);


        System.out.println(maxValue);
    }

    private static void dfs(int num, int value) {
        
        dp[num] = value;
        
        if (value > maxValue) {
            maxValue = value;
            maxPoint = num;
        }

        for (Node target : node[num]) {
            if (dp[target.point] > value) {
                dfs(target.point, target.length + value);
            }
        }
    }

}

class Node {
    int point;
    int length;

    public Node (int point, int length) {
        this.point = point;
        this.length = length;
    }

}
