import java.io.*;
import java.util.Arrays;

class Main {

    static int[][] seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        seq = new int[N][N];
        for (int i = 0; i < N; i++) {
            seq[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        quadTree(0, 0, N);

    }

    private static void quadTree(int x, int y, int size) {
        int value = seq[x][y];


        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (seq[i][j] == value) continue;

                System.out.print("(");

                quadTree(x, y, size/2);
                quadTree(x, y + size/2, size/2);
                quadTree(x + size/2, y, size/2);
                quadTree(x + size/2, y + size/2, size/2);

                System.out.print(")");
                return;
            }
        }

        System.out.print(value);

    }

}
