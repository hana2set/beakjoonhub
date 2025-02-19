import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static char[][] nums;
    static int K,N;
    static int allVisitBitmark;
    static long[][] dp;
    static int[][] mods;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 모든 경우의 수 : 최대 15! (너무 큼으로, DP로 계산)
        // 2. 나누어 떨어지는 수 계산 :
        //   최대 50자리이고, long으로도 커버안됨 -> k가 100 이하임으로, 앞자리부터 순서대로 따와서 나머지 계산
        // 3. 합친 수가 나누어 떨어지는지? :
        //   (a+b) mod k = (a mod k + b mod k) mod k -> a*10^n mod k = a mod k * 10^n mod k 임을 이용,
        //   나머지와 자리수를 계산해서
        //   순차적으로 나머지 DP 계산 (메모이제이션)

        N = Integer.parseInt(br.readLine());
        nums = new char[N][];
        for (int i = 0; i < N; i++) {
            nums[i] = br.readLine().toCharArray();
        }
        K = Integer.parseInt(br.readLine());

        allVisitBitmark = (1<<N) - 1;
        dp = new long[K][1<<N]; // [나머지][숫자 조합 상태]의 조합을 가진 총 갯수
        mods = new int[K][N]; // [나머지][숫자 순서]로, (나머지+N번째 숫자 더한 값을 K로 나눈 나머지)를 미리 저장한 배열

        for (int i = 0; i < K; i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(mods[i], -1);
        }
        
        long answer = getDevidedCount(0, 0);
        long total = 1;

        if (answer != 0) {
            // total 계산
            for (int i = 2; i <= N; i++) {
                total *= i; // 조합 수 -> N!
            }

            long gcd = getGcd(answer, total);

            answer /= gcd;
            total /= gcd;
        }

        System.out.println(answer + "/" + total);
    }

    private static long getGcd(long a, long b) { // a<b
        if (a == 0) return b;
        return getGcd(b % a, a);
    }

    // remain: 나머지, bitmark: 숫자 조합 상태
    private static long getDevidedCount(int remain, int bitmark) {
        // 이미 방문
        if (dp[remain][bitmark] != -1) return dp[remain][bitmark];

        // 모든 점 방문 -> 숫자가 나누어지는지 확인
        if (bitmark == allVisitBitmark) return remain == 0 ? 1 : 0;

        // 나머지가 0인 개수 체크
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            int idx = 1<<i;

            // 방문하지 않았다면
            if ((bitmark & idx) == 0) {
                cnt += getDevidedCount(getMod(i, remain), bitmark | idx);
            }

        }

        return dp[remain][bitmark] = cnt;
    }

    private static int getMod(int idx, int remain) {
        if (mods[remain][idx] != -1) return mods[remain][idx];

        int calc = remain;
        char[] num = nums[idx];
        // 숫자 10의자리씩 더하면서 나머지 계산 -> 최종 나머지 계산
        for (int i = 0; i < num.length; i++) {
            calc *= 10;
            calc += (num[i]-'0');
            calc %= K;
        }

        return mods[remain][idx] = calc;
    }

}