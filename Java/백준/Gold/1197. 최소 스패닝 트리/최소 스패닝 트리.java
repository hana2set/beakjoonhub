import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Main {

    static int[] parent;
    static int[][] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] data = br.readLine().split(" ");
        int V = Integer.parseInt(data[0]);
        int E = Integer.parseInt(data[1]);

        lines = new int[E][3];
        parent = new int[V+1];
        long result = 0;

        for (int i = 0; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            //크루스칼 알고리즘 사용을 위한 정렬
            String[] line = br.readLine().split(" ");
            int s = Integer.parseInt(line[0]);
            int e = Integer.parseInt(line[1]);
            int w = Integer.parseInt(line[2]);

            lines[i] = new int[]{s, e, w};
        }

        Arrays.sort(lines, Comparator.comparing(a -> a[2])); //가중치 낮은순 정렬

        for (int i = 0; i < E; i++) {

            // 사이클 여부 확인
            if (union(lines[i][0], lines[i][1]) == false) continue;

            // 가중치 추가
            result += lines[i][2];
        }


        System.out.println(result);
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
