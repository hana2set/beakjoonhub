import java.io.*;
import java.util.*;

class Main {

    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] cond = br.readLine().split(" ");
        int N = Integer.parseInt(cond[0]);
        int M = Integer.parseInt(cond[1]);

        int[][] matrix = new int[N][M];
        boolean[][][] visit = new boolean[N][M][2]; //[x][y][벽뚫기 여부]

        for (int i = 0; i < N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        // 최단경로임으로 BFS,
        // 한번 부술 수 있음으로 해당 값 트러거 포함한 좌표정보를 point로 저장
        Queue<Point> q = new LinkedList<>(); // {좌표, 이동횟수}
        q.add(new Point(0, 0, false, 1));
        visit[0][0][0] = true;

        int result = -1;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == N-1 && p.y == M-1) {
                result = p.count;
                break;
            }

            for (int i = 0; i < dx.length; i++) {
                int targetX = p.x+dx[i];
                int targetY = p.y+dy[i];

                if (targetX < 0 || targetX >= N) continue;
                if (targetY < 0 || targetY >= M) continue;

                // 0 일 경우
                if (matrix[targetX][targetY] == 0 && visit[targetX][targetY][p.isWallBreak == false ? 0 : 1] == false) {
                    visit[targetX][targetY][p.isWallBreak == false ? 0 : 1] = true;
                    q.add(new Point(targetX, targetY, p.isWallBreak, p.count+1));
                }

                // 1일 경우
                if (matrix[targetX][targetY] == 1 && p.isWallBreak == false && visit[targetX][targetY][1] == false) {
                    visit[targetX][targetY][1] = true;
                    q.add(new Point(targetX, targetY, true, p.count+1));
                }


            }

        }

        System.out.println(result);
    }

}

class Point {
    int x;
    int y;
    boolean isWallBreak;
    int count;

    public Point(int x, int y, boolean isWallBreak, int count) {
        this.x = x;
        this.y = y;
        this.isWallBreak = isWallBreak;
        this.count = count;
    }
}
