import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] time = new int[N];

        // 시간 짧은순으로 보내는게 좋음 -> 정렬 후 합 계산
        for (int i = 0; i < N; i++) {
            time[i] = sc.nextInt();
        }

        Arrays.sort(time);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j >= 0; j--) {
                sum += time[j];
            }
        }

        System.out.println(sum);

    }
}