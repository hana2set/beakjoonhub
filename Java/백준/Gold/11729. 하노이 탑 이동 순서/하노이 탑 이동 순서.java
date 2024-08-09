import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int count = 0;

        // N개 옮기기 -> N-1개 옮기기 + N번째 원반 옮기기 + N-1개 옮기기 -> 루프로 해결
        sb.insert(0, move(N, 1, 3, 2) + "\n");

        System.out.println(sb.toString());
    }

    // 옮긴 횟수, 시작점, 종료점, 비어있는 곳
    private static int move(int n, int start, int target, int empty) throws IOException {
        if (n == 1) {
            sb.append(start + " " + target + "\n");
            return 1;
        }

        return move(n-1, start, empty, target) + move(1, start, target, empty) + move(n-1, empty, target, start);
    }
}