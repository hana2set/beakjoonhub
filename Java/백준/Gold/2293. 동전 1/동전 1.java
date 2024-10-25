import java.io.*;
import java.util.*;

class Main {
    static boolean[][] visit;
    static int[] coin;
    static int[] dp;
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        k = input[1];
        coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        // 코인 순서가 달라도 됨 -> 정렬로 해결
        Arrays.sort(coin);

        // 코인의 가치가 배수라는 보장이 없음 - 그리디 사용 불가
        // 시간 -> dp로 미리 계산
        // 작은코인 순으로 dp 인덱스에 해당하는 값을 만들 수 있는 경우의 수 계산
        // -> 이후 기존것에서 다음 코인을 더해서 만들 수 있는 경우의 수를 더함
        dp = new int[k+1];
        dp[0] = 1;

        for (int i = 0; i < coin.length; i++) {
            int m = coin[i];
            int index = m;

            while (index <= k) {
                dp[index] = dp[index-m] + dp[index];
                index++;
            }
        }

        System.out.println(dp[k]);

    }

}
