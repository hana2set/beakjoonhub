import java.io.*;
import java.util.*;

class Main {

    static int[] parent;
    static double[][] point;
    static List<Line> lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        double result = 0.0;
        parent = new int[n];
        point = new double[n][2];
        lines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            //크루스칼 알고리즘 사용을 위한 정렬
            String[] line = br.readLine().split(" ");
            double s = Double.parseDouble(line[0]);
            double e = Double.parseDouble(line[1]);

            point[i] = new double[]{s, e};
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (i==j) continue;

                lines.add(new Line(i, j,
                        Math.sqrt( Math.pow(point[i][0] - point[j][0], 2)
                                + Math.pow(point[i][1] - point[j][1], 2) )
                        )
                );
            }
        }

        Collections.sort(lines, Comparator.comparingDouble(a -> a.length)); //가중치 낮은순 정렬

        for (int i = 0; i < lines.size(); i++) {

            // 사이클 여부 확인
            if (union(lines.get(i)) == false) continue;

            // 가중치 추가
            result += lines.get(i).length;
        }


        System.out.println(result);
    }

    private static boolean union(Line line) {
        int x = find(line.p1);
        int y = find(line.p2);

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