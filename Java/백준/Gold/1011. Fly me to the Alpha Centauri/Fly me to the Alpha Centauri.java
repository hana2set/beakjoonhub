import java.util.*;

class Main {

    // 첫 이동 거리 = 1
    // 마지막 이동거리 = 1
    // 최소값 구하기 -> 가장 빠르게 가기 -> 항상 최대속력
    // -> 중간까지 최대속도, 그이후로 감속
    // 1,2,3,2,1 과 같은 형태가 최솟값
    // 1 -> 1
    // 121 -> 4
    // 12321 -> 9
    // 1234321 -> 16
    // 123...k...321 -> k^2 (k는 최대속도)
    //
    // 그 사이는 사이에 같은값 추가로 k까지 1회로 가능.
    // ex) 15 -> 1,2,3, "3, 3" ,2 ,1
    // 즉 남은 값에서, 0 < 3 < 3+3 < 3+3+3 이런식으로 갯수 추가 가능
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int value = 0;

            int k = 1;
            int l = y-x;
            if (l <= 3) {
                System.out.println(l);
                continue;
            }

            while (true) {
                if (l >= Math.pow(k, 2) && l < Math.pow(k+1, 2)) break;
                k++;
            }

            value += 2*(k)-1;

            l -= Math.pow(k, 2);

            while (true) {
                if (l <= 0 || k <= 0) break;
                value++;
                if (l >= k) {
                    l -= k;
                    continue;
                }
                break;

            }

           System.out.println(value);
        }

    }
}