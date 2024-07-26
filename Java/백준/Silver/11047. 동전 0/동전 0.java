import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] coin = new int[N];

        // 배수임으로 그리드
        for (int i = 0; i < N; i++) {
            coin[i] = sc.nextInt();
        }

        int index = N;
        int count = 0;
        while (index-- > 0) {
            if (coin[index] > K) continue;

            count += K/coin[index];
            K -= coin[index] * (K/coin[index]);
        }

        System.out.println(count);

    }
}