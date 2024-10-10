import java.io.*;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        long B = Long.parseLong(input[1]);
        int mod = 1_000;

        int[][] A = new int[N][N];

        for (int i = 0; i < N; i++) {
            A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 계산

        int[][] result = new int[N][N]; //항등원 만들기
        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
        }

        while (B > 0) {
            if (B % 2 == 1) {
                result = matrixMultiple(N, mod, result, A);
            }

            A = matrixMultiple(N, mod, A, A);
            B /= 2;
        }

        // 출력
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(String.join(" ", Arrays.stream(result[i]).mapToObj(String::valueOf).toArray(String[]::new)) + "\n");
        }

        System.out.println(sb);
    }

    private static int[][] matrixMultiple(int N, int mod, int[][] A, int[][] B) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                //행렬곱 공식
                for (int k = 0; k < N; k++) {
                    result[i][j] += A[i][k] * B[k][j] % mod;
                    result[i][j] %= mod;
                }

            }
        }

        return result;
    }


}
