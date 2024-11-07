import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[][] maze;
    static boolean[][] visit;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        maze = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs(0, 0));

    }

    private static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 1));
        visit[x][y] = true;

        int minValue = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == N-1 && p.y == M-1) {
                minValue = p.count;
                break;
            }

            // 재귀 방문
            for (int i = 0; i < dx.length; i++) {
                int tx = p.x+dx[i];
                int ty = p.y+dy[i];

                // 가능 조건 확인
                if (tx < 0 || tx >= N) continue;
                if (ty < 0 || ty >= M) continue;
                if (maze[tx][ty] == 0) continue;
                if (visit[tx][ty] == true) continue;

                visit[tx][ty] = true;

                q.add(new Point(tx, ty, p.count+1));
            }
        }

        return minValue;
    }
}

class Point {
    int x;
    int y;
    int count;

    Point (int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}