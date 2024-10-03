import java.io.*;
import java.util.Arrays;

class Main {

    static int[][] seq;
    static int[] dx = new int[]{0, 1, 2};
    static int[] dy = new int[]{0, 1, 2};
    static int[] count = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        seq = new int[N][N];
        for (int i = 0; i < N; i++) {
            seq[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0, 0, N);

        System.out.println(count[0]);
        System.out.println(count[1]);
        System.out.println(count[2]);
    }

    private static void dfs(int x, int y, int size) {
        int value = seq[x][y];


        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (seq[i][j] == value) continue;

                // 다른값 존재하면 9등분하기
                for (int di = 0; di < dx.length; di++) {
                    for (int dj = 0; dj < dy.length; dj++) {
                        dfs(x+dx[di]*size/3, y+dy[dj]*size/3, size/3);
                    }
                }

                return;
            }
        }

        switch (value) {
            case -1: count[0]++; break;
            case 0: count[1]++; break;
            case 1: count[2]++; break;
        }


    }

}
