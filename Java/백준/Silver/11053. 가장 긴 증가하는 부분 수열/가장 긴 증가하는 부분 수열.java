import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];

        dp[0] = 1;

        // 현재 seq값 보다 작은 이전의 seq 값의 dp+1의 최대값
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (seq[i] > seq[j]) {
                    max = Math.max(dp[j], max);
                }
            }

            dp[i] = max+1;
        }
        
        Arrays.sort(dp);

        System.out.println(dp[N-1]);
    }
}