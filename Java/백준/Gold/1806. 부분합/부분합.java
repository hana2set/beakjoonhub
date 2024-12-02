import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 부분합 -> 누적합의 차이로 계산
        // 가장 짧은 길이 찾아야함 -> 투포인터 알고리즘이 가장 좋을것으로 보임

        String[] tempInput = br.readLine().split(" ");
        int N = Integer.parseInt(tempInput[0]);
        long S = Integer.parseInt(tempInput[1]);
        long[] seq = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] sum = new long[N+1];

        for (int i = 0; i < N; i++) {
            sum[i+1] = sum[i] + seq[i];

            if (seq[i] >= S) { //하나가 S이상이면, 1 출력 후 종료
                System.out.println(1);
                System.exit(0);
            }
        }

        int l = 1;
        int r = 1;
        int min = Integer.MAX_VALUE;
        while (r <= N) {
            long pSum = sum[r]-sum[l-1];

            if (pSum >= S) min = Math.min(min, r-l+1);

            if (pSum >= S) l++;
            else r++;
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
