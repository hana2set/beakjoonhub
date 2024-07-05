import java.util.*;

class Main {

    // 원의 교점과 같음
    // R + r1 < r2 -> 0개 (내부원, 못만남)
    // r1 + r2 < R -> 0개
    // r1 + r2 == R -> 한개 or 무한개
    // r1 + r2 > R -> 2개
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int r1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int r2 = sc.nextInt();

            int answer = 0;

            // 계산 쉽게 제곱으로 만듬
            double[] tempRArr = new double[]{Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)), r1, r2};//1과 2의 거리 의 제곱

            Arrays.sort(tempRArr);

            long R = Math.round(Math.pow(tempRArr[2], 2));
            long r1r2 = (long) Math.pow(tempRArr[0]+tempRArr[1], 2);

            if (r1r2 < R) answer = 0;
            else if (r1r2 > R) answer = 2;
            else if (x1 == x2 && y1 == y2) answer = -1;
            else answer = 1;

           System.out.println(answer);
        }

    }
}