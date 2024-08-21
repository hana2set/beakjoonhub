import java.io.*;
import java.util.*;

class Main {

    static int[][] map;
    static int white;
    static int blue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        // dfs 방식으로, 특정 영역이 같은색이면 카운팅
        // 아니면 4등분 후 dfs 반복

        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[i] = row;
        }

        dfs(0, 0, N);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void dfs(int x, int y, int size) {
        int color = map[x][y];

        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (color == map[i][j]) continue;

                // 모든 색이 일치하지 않는 경우
                int h = size/2;
                dfs(x,y,h);
                dfs(x+h,y,h);
                dfs(x,y+h,h);
                dfs(x+h,y+h,h);
                return;
            }
        }

        // 모든 색이 일치할 경우
        if (color == 0) white++;
        else blue++;
    }


}