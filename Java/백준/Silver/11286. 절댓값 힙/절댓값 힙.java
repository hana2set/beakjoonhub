import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>(
                Comparator.comparingInt((Integer a) -> Math.abs(a))
                        .thenComparingInt(a -> a));

        while (N-- > 0) {
            int x = Integer.parseInt(br.readLine());

            if (x != 0) {
                q.add(x);
                continue;
            }

            if (q.isEmpty()) {
                System.out.println(0);
                continue;
            }

            System.out.println(q.poll());
        }

    }
}
