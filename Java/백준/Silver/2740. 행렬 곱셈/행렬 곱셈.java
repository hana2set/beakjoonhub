import java.io.*;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N,M,K, A,B 초기화

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] A = new int[N][M];

        for (int i = 0; i < N; i++) {
            A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        input = br.readLine().split(" ");

        M = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[][] B = new int[M][K];

        for (int i = 0; i < M; i++) {
            B[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // N,M,K, A,B 초기화 end

        int[][] C = new int[N][K]; // 곱행렬

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {

                //행렬곱 공식
                for (int k = 0; k < M; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }

            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(String.join(" ", Arrays.stream(C[i]).mapToObj(String::valueOf).toArray(String[]::new)) + "\n");
        }

        System.out.println(sb);
    }
}
