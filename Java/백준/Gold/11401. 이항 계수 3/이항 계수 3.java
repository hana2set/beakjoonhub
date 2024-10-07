import java.io.*;
import java.util.List;

class Main {

    static int mod = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[] fac = new int[N+1];
        fac[0] = 1; //초기값

        // 이항계수 -> N개를 K개 선택하는 경우의 수 -> N! / K!(N-K)!
        // 페르마 소정리 -> a^p-1 = 1 (mod p)
        // => N! / K!(N-K)! => N! * (K!(N-K)!)^p-2 (mod p)
        long factVal = 1;

        for (int i = 1; i <= N; i++) {
            factVal *= i;
            factVal %= mod;
            fac[i] = (int) factVal;
        }

        long result = fac[N] * pow(fac[K]) % mod;
        result = result * pow(fac[N-K]) % mod;

        System.out.println(result);

    }

    private static long pow(long val) {
        long result = 1;
        int exp = mod-2;

        //모듈러 연산을 통한 지수계산 (1629번)
        while (exp > 0) {
            if (exp % 2 == 1) {
                result *= val;
                result %= mod;
            }
            val *= val;
            val %= mod;
            exp /= 2;
        }

        return result;
    }

}
