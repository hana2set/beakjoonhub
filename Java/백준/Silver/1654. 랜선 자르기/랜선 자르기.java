import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        String[] input = br.readLine().split(" ");
        int K = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);
        long[] cables = new long[K];

        for (int i = 0; i < K; i++) {
            cables[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(cables);

        // 이분탐색
        long l = 1;
        long r = cables[K-1]+1;

        // r이 cable 최대값+1인 이유
        // 2 3, 100, 1500 일 경우 500짜리로 3개 만들 수 있음 (최대부터 탐색)
        // 답이 cable의 최대값일 경우, r을 탐색하지 않음으로 +1 (r은 N보다 작음을 항상 유지해야함) 

        while (l < r) { // 최대값임으로 upper bound
            long mid = (r+l)/2;

            long cableCount = 0;
            for (long cable : cables) {
                cableCount += cable / mid;
            }

            if (cableCount < N) r = mid;
            else l = mid+1;
        }

        // l은 가능한 최대길이+1 (mid+1)
        System.out.println(l-1);

    }

}
