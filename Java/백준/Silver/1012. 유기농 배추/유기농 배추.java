import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[][] cabbage;
    static boolean[][] visit;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());


        while (T-- > 0) {
            // 초기화
            String[] input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]); //가로, y
            N = Integer.parseInt(input[1]); //세로, x
            int K = Integer.parseInt(input[2]);

            cabbage = new int[N][M];
            visit = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                int[] p = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                cabbage[p[1]][p[0]] = 1;
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (needWorm(i, j)) {
                        dfs(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);

        }
    }

    private static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < dx.length; i++) {
            if (x+dx[i] < 0 || x+dx[i] >= N || y+dy[i] < 0 || y+dy[i] >= M) continue; // 영역 조건

            if (needWorm(x+dx[i], y+dy[i])) {
                dfs(x+dx[i], y+dy[i]);
            }
        }

    }

    private static boolean needWorm(int x, int y) {
        return visit[x][y] == false && cabbage[x][y] == 1;
    }

}