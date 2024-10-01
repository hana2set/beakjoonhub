import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        String[][] table = new String[N][M];

        for (int i = 0; i < N; i++) {
            table[i] = Arrays.stream(br.readLine().split("")).toArray(String[]::new);
        }


        // 정방향(BWBW...)을 정해두고, 일치하면 0, 다르면 1로 입력 -> K개 부분합으로 변경해야되는 갯수 계산 가능
        // 즉, 정방향에 대한 누적합 계산
        int[][] sum = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {

                // 2차원 배열 누적합 =
                // (행의 누적합) + (열의 누적합) - (행열 누적합의 중복값) + (현재 값 -> B,W -> 1 or 0)
                sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1]; // 기존 누적합

                String rightColor = (i+j)%2 == 0 ? "B" : "W";
                if (table[i-1][j-1].equals(rightColor)) sum[i][j] += 1; // 현재값 더하기
            }
        }

        int result = Integer.MAX_VALUE; // 변경 최소값
        for (int i = 1; i <= N-K+1; i++) {
            for (int j = 1; j <= M-K+1; j++) {

                // 2차원 배열 부분합 =
                // (x2,y2의 누적합) - (x2,y1의 누적합) - (x1,y2의 누적합) + (x1,y1의 누적합)
                int pSum = sum[i+K-1][j+K-1] - sum[i+K-1][j-1] - sum[i-1][j+K-1] + sum[i-1][j-1];

                pSum = Math.min(pSum, K*K - pSum); //역방향 계산
                result = Math.min(pSum, result) ;
            }
        }

        System.out.println(result);
    }

}
