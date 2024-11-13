import java.io.*;
import java.util.*;

class Main {

    static int[] dx = new int[]{1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] cond = br.readLine().split(" ");
        int N = Integer.parseInt(cond[0]);
        int M = Integer.parseInt(cond[1]);

        boolean[] visit = new boolean[101];
        int[] snake = new int[101];
        int[] ladder = new int[101];

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            snake[Integer.parseInt(s[0])] = Integer.parseInt(s[1]);
        }
        for (int i = 0; i < M; i++) {
            String[] l = br.readLine().split(" ");
            snake[Integer.parseInt(l[0])] = Integer.parseInt(l[1]);
        }

        // 최소 횟수 -> BFS
        Queue<int[]> q = new LinkedList<>(); // {좌표, 이동횟수}

        q.add(new int[]{1, 0});
        visit[1] = true;

        int count = -1;

        while (!q.isEmpty()) {
            int[] x = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int tx = x[0] + dx[i];

                // bfs 조건 확인
                if (tx <= 0 || tx > 100) continue;

                if (snake[tx] != 0) tx = snake[tx];
                if (ladder[tx] != 0) tx = ladder[tx];

                // 결과 출력
                if (tx == 100) {
                    count = x[1]+1;
                    break;
                }

                if (visit[tx] == true) continue;

                visit[tx] = true;

                // 익은 날짜
                q.add(new int[]{tx,x[1] + 1});

            }

            if (count != -1) break;
        }

        System.out.println(count);
    }

}
