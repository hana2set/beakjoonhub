import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 음수 -> 1임으로 계산하지 않음
        // 빼기만 있음으로 순차적으로 계산하면됨
        // 50이상 -> dp[20][20][20] 반환
        int[][][] dp = new int[21][21][21];

        for (int a = 0; a < 21; a++) {
            for (int b = 0; b < 21; b++) {
                for (int c = 0; c < 21; c++) {
                    if (a == 0 || b == 0 || c ==0) {
                        dp[a][b][c] = 1;
                        continue;
                    }

                    int la = a-1 > 0 ? a-1 : 0;
                    int lb = b-1 > 0 ? b-1 : 0;
                    int lc = c-1 > 0 ? c-1 : 0;

                    if (a < b && b < c) {
                        dp[a][b][c] = dp[a][b][lc]
                                + dp[a][lb][lc]
                                - dp[a][lb][c];
                    } else {
                        dp[a][b][c] = dp[la][b][c]
                                + dp[la][lb][c]
                                + dp[la][b][lc]
                                - dp[la][lb][lc];
                    }
                }
            }
        }


        while (true) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
            if (input[0] == -1 && input[1] == -1 && input[2] == -1) break;

            System.out.print("w(" + input[0] + ", " + input[1] + ", " + input[2] + ") = ");

            if (input[0] <= 0 || input[1] <= 0 || input[2] <= 0) {
                input[0] = 0;
                input[1] = 0;
                input[2] = 0;
            } else if (input[0] > 20 || input[1] > 20 || input[2] > 20) {
                input[0] = 20;
                input[1] = 20;
                input[2] = 20;
            }

            System.out.println(dp[input[0]][input[1]][input[2]]);
        }

    }

}