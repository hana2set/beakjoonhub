import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] lines = new int[N][2];

        for (int i = 0; i < N; i++) { //전깃줄 초기화
            lines[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 같은 위치에 두 개 이상의 전깃줄이 연결될 수 없음으로
        // A로 정렬 후 나타나는 B의 수열의 증가하는 최장길이부분수열을 구하면 그것이 최솟값. 11053 LIS
        // B 에 대한 DP 계산
        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));

        // dp : 해당 줄에 대한 LIS 길이 값
        int[] dp = new int[N];
        int result = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (lines[j][1] < lines[i][1]) dp[i] = Math.max(dp[j]+1, dp[i]);
            }

            result = Math.max(result, dp[i]);
        }

        // 없애야 되는 전깃줄 개수 출력
        System.out.println(N - result);
    }

}
