import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        // N <= M이고, 많이 지어야함으로
        // M에서 사용하지 않을 사이트를 선택하는 경우의 수랑 같음 (M에서 M-N개 조합)
        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            double result = 1;
            int loop = M-N;
            int index = M;
            while (loop > 0) {
                result = (result * index--) / loop--;
            }

            System.out.println(Math.round(result));
        }
    }
}