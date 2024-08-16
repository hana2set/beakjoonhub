import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();

        // 약수의 갯수를 직접 계산하는 방법은 시간초과.
        // 각 행은 두 수의 곱으로 이루어져 있음으로, "행 값으로 N을 나누면" N보다 작은 갯수를 알 수 있음
        // ex) 2행에서 7보다 작은수 -> 2*1, 2*2, 2*3 <- 7/2
        // 이분탐색으로 계산
        // mid의 범위가 k를 포함함으로, mid가 k를 넘지않는 최대값 -> Lower Bound
        long left = 1;
        long right = k;
        while (left < right) {
            long mid = (left + right) / 2;

            int count = getDivisorCount(mid, N);
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }

        }

        System.out.println(left);

    }

    private static int getDivisorCount(long number, int n) {
        int divisorCount = 0;
        for (int i = 1; i <= n; i++) {
            divisorCount += Math.min(number / i, n); // n보다 큰 수는 불가능
        }

        return divisorCount;
    }

}