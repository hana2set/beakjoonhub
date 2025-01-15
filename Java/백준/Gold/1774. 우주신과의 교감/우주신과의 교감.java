import java.io.*;
import java.util.*;

class Main {

    static int[] parent;
    static int[][] point;
    static List<Line> lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);

        double result = 0.0;
        parent = new int[N+1];
        point = new int[N+1][2];
        lines = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            //크루스칼 알고리즘 사용을 위한 정렬
            String[] line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);

            point[i] = new int[]{x, y};
        }


        for (int i = 0; i < M; i++) {
            //크루스칼 알고리즘 사용을 위한 정렬
            String[] line = br.readLine().split(" ");
            int s = Integer.parseInt(line[0]);
            int e = Integer.parseInt(line[1]);

            union(s, e);
        }


        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {

                lines.add(new Line(i, j,
                        Math.sqrt( Math.pow(point[i][0] - point[j][0], 2)
                                + Math.pow(point[i][1] - point[j][1], 2) )
                        )
                );
            }
        }

        Collections.sort(lines, Comparator.comparingDouble(a -> a.length)); //가중치 낮은순 정렬

        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);

            // 사이클 여부 확인
            if (union(line.p1, line.p2) == false) continue;

            // 가중치 추가
            result += line.length;
        }


        System.out.println(String.format("%.2f", result));
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

class Line {
    int p1, p2;
    double length;


    public Line(int p1, int p2, double length) {
        this.p1 = p1;
        this.p2 = p2;
        this.length = length;
    }

}