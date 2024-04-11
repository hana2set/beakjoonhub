import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int L = sc.nextInt();
        int W = sc.nextInt();
        int H = sc.nextInt();

        Double right = Double.parseDouble(String.valueOf(Math.min(Math.min(L, W), H)));
        Double left = 0.0;
        Double A = (right + left) / 2.0;

        int count = 0;

        while (left<right) {
            count++;
            if (right == A || count > 60) break;

            // (long)(L/A) * (long)(W/A) * (long)(H/A) - 넣을수 있는 갯수
            // N 보다 작음 -> 여유공간부족
            if ((long)(L/A) * (long)(W/A) * (long)(H/A) < N) {
                right = A;
            // N 보다 작거나 같음 -> 여유공간 있음
            } else {
                left = A;
            }

            A = (left+right)/2;
        }

        System.out.println(right);

    }
}