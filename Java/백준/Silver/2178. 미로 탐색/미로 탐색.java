import java.io.*;
import java.util.*;

class Main {

    static int N, M, count = Integer.MAX_VALUE;
    static int[][] maze;
    static int[][] visit;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        maze = new int[N][M];
        visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        visit[N-1][M-1] = Integer.MAX_VALUE;
        dfs(0, 0, 0);
        System.out.println(visit[N-1][M-1]+1);

    }

    private static void dfs(int x, int y, int index) {
        if (x == N-1 && y == M-1) {
            visit[x][y] = Math.min(index, visit[x][y]);
            return;
        }

        // 가능 조건 확인
        if (x < 0 || x >= N) return;
        if (y < 0 || y >= M) return;
        if (maze[x][y] == 0) return;
        if (visit[x][y] != 0 && visit[x][y] <= index) return;

        // 재귀 방문
        visit[x][y] = index;
        for (int i = 0; i < dx.length; i++) {
            dfs(x+dx[i], y+dy[i], index+1);
        }


    }

}
