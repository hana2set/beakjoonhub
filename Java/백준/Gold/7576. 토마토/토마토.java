import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[][] tomato;
    static boolean[][] visit;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        tomato = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            tomato[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 1) q.add(new int[]{i, j});
            }
        }

        int passDay = bfs(q);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 0) { // 아직 안익은 토마토가 있으면
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(passDay);
    }

    // q: 새로 익은 화일을 담은 큐
    private static int bfs(Queue<int[]> q) {
        //새로 익은 과일이 없다
        if (q.isEmpty()) return -1; // 전날이 최대값

        Queue<int[]> nextQ = new LinkedList<>(); //새로 익을 과일을 담은 큐

        while (q.isEmpty() == false) {
            int[] p = q.poll();

            ripeTomato(p[0], p[1], nextQ);
        }


        return bfs(nextQ)+1; //다음날로
    }

    private static void ripeTomato(int x, int y, Queue<int[]> nextQ) {
        for (int i = 0; i < dx.length; i++) {
            if (0 <= x+dx[i] && x+dx[i] < N && 0 <= y+dy[i] && y+dy[i] < M) { // 영역 조건
                if (tomato[x+dx[i]][y+dy[i]] != 0) continue;

                tomato[x+dx[i]][y+dy[i]] = 1;
                nextQ.add(new int[]{x+dx[i], y+dy[i]});
            }
        }
    }

}