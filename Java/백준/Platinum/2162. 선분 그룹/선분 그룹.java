import java.io.*;

class Main {


    static int[] parent;
    static int[] x1;
    static int[] y1;
    static int[] x2;
    static int[] y2;

    static int[] count; // 선분 갯수
    static int groupCount;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //CCW를 통한 교차 계산

        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        count = new int[N+1];
        x1 = new int[N+1];
        y1 = new int[N+1];
        x2 = new int[N+1];
        y2 = new int[N+1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
//            count[i] = 1;
            String[] data = br.readLine().split(" ");
            x1[i] = Integer.parseInt(data[0]);
            y1[i] = Integer.parseInt(data[1]);
            x2[i] = Integer.parseInt(data[2]);
            y2[i] = Integer.parseInt(data[3]);
        }

        groupCount = N;

        for (int i = 1; i < N; i++) {
            for (int j = i+1; j <= N; j++) {
                if (find(i) == find(j)) continue;

                if (crossLine(i, j)) {
                    int cnt = union(i, j);
//                    max = Math.max(cnt, max);
                }

            }
        }

//        groupCount = 0;
        max = 0;

        for (int i = 1; i <= N; i++) {
            count[find(i)]++;
        }

        for (int i = 1; i <= N; i++) {
//            if (count[i] > 0) groupCount++;
            max = Math.max(count[i], max);

        }

        System.out.println(groupCount);
        System.out.println(max);


    }

    private static int union(int i, int j) {
        i = find(i);
        j = find(j);

        if (i != j) {
            parent[j] = i;
            count[i] += count[j];
            count[j] = 1;
            groupCount--;
        }

//        return count[i];
        return 0;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private static boolean crossLine(int a, int b) {
        int ccw123 = ccw(x1[a], y1[a], x2[a], y2[a], x1[b], y1[b]); // 선분a_p3
        int ccw124 = ccw(x1[a], y1[a], x2[a], y2[a], x2[b], y2[b]); // 선분a_p4
        int ccw341 = ccw(x1[b], y1[b], x2[b], y2[b], x1[a], y1[a]); // 선분b_p1
        int ccw342 = ccw(x1[b], y1[b], x2[b], y2[b], x2[a], y2[a]); // 선분b_p2

        // 일직선인 경우
        if (ccw123*ccw124 == 0 && ccw341*ccw342 == 0) {
            // 교차 여부 확인
            if (Math.min(x1[a], x2[a]) <= Math.max(x1[b], x2[b]) && Math.min(x1[b], x2[b]) <= Math.max(x1[a], x2[a])
                    && Math.min(y1[a], y2[a]) <= Math.max(y1[b], y2[b]) && Math.min(y1[b], y2[b]) <= Math.max(y1[a], y2[a]))
                return true;

            return false; // 교차하지 않는 일직선
        }

        // 일직선이 아닌 교차
        if (ccw123*ccw124 <= 0 && ccw341*ccw342 <= 0) {
            return true;
        }

        // 그 외
        return false;
    }

    private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        long ccw = (x1*y2+x2*y3+x3*y1) - (x2*y1+x3*y2+x1*y3);

        // ccw 끼리 곱하면 큰숫자가 될 수 있음으로 절삭
        if (ccw < 0) return -1;
        else if (ccw > 0) return 1;
        else return 0;
    }

}