import java.io.*;
import java.util.Arrays;

class Main {

    static int[][] roads;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 가능여부 ->  union find로 판별 가능
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if (M == 1) {
            System.out.println("NO");
            return;
        }

        roads = new int[N+1][N+1];
        parent = new int[N+1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                roads[i][j] = Integer.parseInt(input[j-1]);

                if (roads[i][j] == 1) union(i, j);
            }

        }

        int[] target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int start = find(target[0]);

        for (int i = 1; i < target.length; i++) {
            if (start != find(target[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x < y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }

    }

}
