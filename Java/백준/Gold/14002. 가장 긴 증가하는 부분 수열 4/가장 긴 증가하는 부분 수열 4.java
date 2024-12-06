import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1부터 시작해서 dp 채우기
        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];
        String[] process = new String[N];
        dp[0] = 1;
        process[0] = seq[0] + ",";

        int result = 0;
        for (int i = 1; i < N; i++) {
            int max = 0;
            int index = -1;
            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i]) { // 증가수열이고
                    if (max < dp[j]) { // dp값이 최대
                        max = dp[j];
                        index = j;
                    }
                }
            }

            if (index == -1) {
                dp[i] = 1;
                process[i] = seq[i] + ",";
                continue;
            }

            dp[i] = max+1;
            process[i]= process[index] + seq[i] + ",";

            if (dp[i] > dp[result]) {
                result = i;
            }
        }

        System.out.println(dp[result]);
        System.out.println(Arrays.stream(process[result].split(",")).collect(Collectors.joining(" ")));
    }


}