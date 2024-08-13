import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] NM = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int N = (int) NM[0];
        long M = NM[1];
        long[] tree = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        Arrays.sort(tree);

        //이진 탐색
        long left = 0;
        long right = tree[N-1];

        while (true) {
            long middle = (left + right) / 2;

            if (left == middle) break;

            // 자른 나무 크기
            long sum = Arrays.stream(tree).map(t -> t- middle > 0 ? t- middle : 0).sum();
            if (sum > M) {
                left = middle;
            } else if (sum < M){
                right = middle;
            } else {
                // 최대값 구하기
                left++;
            }
        }

        System.out.println(left);

    }

}