import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        int[][] table = new int[N][N];
        int[][] sum = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 부분합 -> 누적합의 빼기로 계산가능
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[i][j] = sum[i][j-1] + table[i-1][j-1];
            }
        }

        for (int i = 0; i < M; i++) {
            int[] coor = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = coor[0];
            int y1 = coor[1];
            int x2 = coor[2];
            int y2 = coor[3];

            int pSum = 0;
            for (int j = x1; j <= x2; j++) {
                pSum += sum[j][y2] - sum[j][y1-1];
            };

            // 부분합 계산
            System.out.println(pSum);
        }

    }

}
