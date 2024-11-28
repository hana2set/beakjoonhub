import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = Integer.parseInt(br.readLine());

        // 투포인터로 풀어보기
        Arrays.sort(seq);

        int l = 0;
        int r = N-1;
        int count = 0;
        while (l < r) {
            int sum = seq[r] + seq[l];
            if (sum == x) count++;

            if (sum < x) l++;
            else r--;

        }

        System.out.println(count);
    }
}
