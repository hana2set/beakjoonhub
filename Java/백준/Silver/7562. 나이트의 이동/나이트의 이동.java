import java.io.*;
import java.util.*;

class Main {

    static int[] dx = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 최소 이동횟수 -> BFS
        while(N-- > 0) {
            int l = Integer.parseInt(br.readLine());
            int[] sp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] ep = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            System.out.println(bfs(l, sp, ep));
        }


    }

    private static int bfs(int l, int[] sp, int[] ep) {
        Queue<int[]> q = new LinkedList<>(); // {x좌표, y좌표, 이동거리}

        boolean[][] visit = new boolean[l][l];

        q.add(new int[]{sp[0], sp[1], 0});

        while (!q.isEmpty()) {
            int[] cp = q.poll();

            if (cp[0] == ep[0] && cp[1] == ep[1]) return cp[2];

            for (int i = 0; i < dx.length; i++) {
                int targetX = cp[0]+dx[i];
                int targetY = cp[1]+dy[i];


                if (isValid(targetX, targetY, visit)) {
                    visit[targetX][targetY] = true;
                    q.add(new int[]{targetX, targetY, cp[2]+1});
                }
            }

        }

        return -1;
    }

    private static boolean isValid(int x, int y, boolean[][] visit) {
        if (x < 0 || x >= visit.length) return false;
        if (y < 0 || y >= visit[x].length) return false;
        if (visit[x][y] == true) return false;

        return true;
    }

}
