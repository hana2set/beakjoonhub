import java.io.*;
import java.util.Arrays;

class Main {

    static int[][] roads;
    static int[] parent;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 가능여부 ->  union find로 판별 가능
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        roads = new int[N+1][N+1];
        parent = new int[N+1];
        visit = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                roads[i][j] = Integer.parseInt(input[j-1]);
            }

            parent[i] = i;
        }

        int[] target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(target[0], target[0]);

        for (int i = 1; i < target.length; i++) {
            if (parent[target[i]] != target[0]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static void dfs(int s, int value) {
        visit[s] = true;
        parent[s] = value;

        for (int i = 1; i < roads[s].length; i++) {
            if (roads[s][i] != 0 && visit[i] == false) dfs(i, value);
        }

    }

}
