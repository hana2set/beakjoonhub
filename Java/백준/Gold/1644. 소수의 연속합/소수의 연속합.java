import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 연속된 소수의 합임으로 N 이하 소수 미리 계산
        // 부분합을 위한 누적합 미리 계산
        // 갯수가 정해져 있지 않음으로, 투포인터로 경우의수 계산

        String[] tempInput = br.readLine().split(" ");
        int N = Integer.parseInt(tempInput[0]);
        List<Integer> seq = makePrime(N);

        int[] sum = new int[seq.size()+1];

        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1] + seq.get(i-1);
        }

        int l = 1;
        int r = 1;
        int count = 0;
        while (r < sum.length) {
            long pSum = sum[r]-sum[l-1];

            if (pSum == N) count++;

            if (pSum > N) l++;
            else r++;
        }

        System.out.println(count);
    }

    private static List<Integer> makePrime(int n) {
        List<Integer> list = new ArrayList<>();

        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime , true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i*i <= n; i++) {
            if (isPrime[i]) {
                for(int j = i*i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (isPrime[i]) {
                list.add(i);
            }
        }

        return list;
    }

}
