import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int maxPoint = 1;
    static int maxValue = 0;
    static List<Node>[] node;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 루트에서 가장 먼 끝점 구하고
        // 가장 먼 끝점에서 다른 먼 끝점을 구해서 거리 계산할 것 -> 지름
        int n = Integer.parseInt(br.readLine());
        node = new List[n+1];
        visit = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 양방향
            node[input[0]].add(new Node(input[1], input[2]));
            node[input[1]].add(new Node(input[0], input[2]));
        }

        visit[1] = true;
        dfs(1, 0);

        // 초기화
        visit = new boolean[n+1];
        maxValue = 0;

        visit[maxPoint] = true;
        dfs(maxPoint, 0);

        System.out.println(maxValue);
    }

    private static void dfs(int num, int value) {
        if (value > maxValue) {
            maxValue = value;
            maxPoint = num;
        }

        for (Node target : node[num]) {
            if (visit[target.point] == false) {
                visit[target.point] = true;
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
