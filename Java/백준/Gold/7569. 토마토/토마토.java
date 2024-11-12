import java.io.*;
import java.util.*;

class Main {

    static int[] dx = new int[]{1, -1, 0, 0, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1, 0, 0};
    static int[] dz = new int[]{0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] cond = br.readLine().split(" ");
        int M = Integer.parseInt(cond[0]);
        int N = Integer.parseInt(cond[1]);
        int H = Integer.parseInt(cond[2]);

        int[][][] tomato = new int[H][N][M];

        // 최소 일수 -> BFS
        // 익은 시간을 값으로 표시해서 결과 계산할 예정
        Queue<int[]> q = new LinkedList<>(); // 새로 익은 토마토 좌표

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                tomato[i][j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                // 익은 값 추출
                for (int k = 0; k < M; k++) {
                    if (tomato[i][j][k] == 1)
                        q.add(new int[]{i, j, k});
                }
            }
        }

        int day = 0;

        while (!q.isEmpty()) {
            int[] coord = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int tx = coord[0]+dx[i];
                int ty = coord[1]+dy[i];
                int tz = coord[2]+dz[i];

                // bfs 조건 확인
                if (tx < 0 || tx >= H) continue;
                if (ty < 0 || ty >= N) continue;
                if (tz < 0 || tz >= M) continue;
                if (tomato[tx][ty][tz] != 0) continue; // 이미 익었으면

                // 익은 날짜
                day = tomato[coord[0]][coord[1]][coord[2]]+1;
                tomato[tx][ty][tz] = day;
                q.add(new int[]{tx,ty,tz});

            }
        }

        if (day > 0) day -= 1;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) { //안변한 토마토 확인
                    if (tomato[i][j][k] == 0) day = -1;
                }
            }
        }

        System.out.println(day);


    }

}
