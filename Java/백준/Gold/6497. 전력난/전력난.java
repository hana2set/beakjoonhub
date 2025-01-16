import java.io.*;
import java.util.*;

class Main {

    static int[] parent;
    static int[][] roads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 불이 켜진 길로만 왕복 가능
        // -> 부분집합중 가중치 합이 가장 작은 부분 연결 그래프
        // -> 최소신장트리

        while (true) {
            String[] data = br.readLine().split(" ");
            int m = Integer.parseInt(data[0]);
            int n = Integer.parseInt(data[1]);

            if (n == 0 && m == 0) System.exit(0);

            int curCost = 0;
            int minCost = 0;
            parent = new int[m];
            roads = new int[n][3];

            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < n; i++) {
                //크루스칼 알고리즘 사용을 위한 정렬
                roads[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                curCost += roads[i][2];
            }

            Arrays.sort(roads, Comparator.comparingInt(a -> a[2]));

            for (int i = 0; i < n; i++) {
                if (union(roads[i][0], roads[i][1]) == false) {
                    continue;
                }

                minCost += roads[i][2];
            }

            System.out.println(curCost - minCost);


        }

    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x < y) parent[y] = x;
        if (y < x) parent[x] = y;

        return true;

    }

    private static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }
}
