import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 연속된 행렬 끼리만 곱할수 있음 -> i-j 행렬곱에 대한 값을 미리 계산 -> DP
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][2];
        int[][][] multipleMatrix = new int[N][N][2];    // i-j 행렬곱의 크기
        long[][] dp = new long[N][N];                     // i-j 행렬곱의 최소값
        for (int i = 0; i < N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            multipleMatrix[i][i] = matrix[i];
        }

        // 순서대로 계산하기 위해 길이 기반으로 dp 계산
        for (int len = 1; len < N; len++) {
            for (int i = 0; i < N-len; i++) {
                int j = i+len;
                multipleMatrix[i][j] = new int[]{matrix[i][0], matrix[j][1]}; //곱행렬 크기 계산
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    long mm = multipleMatrix[i][k][0]*multipleMatrix[i][k][1]*multipleMatrix[k][j][1]; //i-k,k-j 행렬곱 연산 수
                    dp[i][j] = Math.min(dp[i][j], mm + dp[i][k]+dp[k+1][j]);
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}
