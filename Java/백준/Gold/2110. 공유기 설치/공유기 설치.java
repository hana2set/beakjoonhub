import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();

        int[] x = new int[N];

        for (int i = 0; i < N; i++) {
            x[i] = sc.nextInt();
        }

        // 가장 인접한 두 공유기 사이 거리가 최대 -> 간격이 같을수록 유리, 양끝점은 가장 끝
        // -> 가장 유리한 "상한값"이 정해져 있지만 ( 총 거리 / C-1 ) 집간 거리가 정해져있지 않음으로,
        // 상한값을 기준으로 이분탐색을 실행

        Arrays.sort(x);
        int dist = x[N-1] - x[0];
        int left = 1; // x좌표 임으로, 최소값은 1
        int right = dist / (C-1);

        while (left < right) {
            int mid = (right + left) / 2 + 1;

            // 해당 값(mid) 이상으로 집간 거리를 구성할 수 있는지
            int wifiCount = 1;
            for (int i = 1; i < N; i++) {
                int next = i;
                while (next < N && x[next]-x[i-1] < mid) {
                    next++;
                }

                if (next < N) {
                    wifiCount++;
                    i = next;
                }
            }


            if (wifiCount >= C) { // 가능함
                left = mid;
            } else { // 불가능함
                right = mid - 1;
            }
        }

        System.out.println(right);

    }

}