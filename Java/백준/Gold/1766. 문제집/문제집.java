import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 위상정렬 문제
        String[] info = br.readLine().split(" ");

        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);

        int[] inDegree = new int[N+1];
        List<Integer>[] next = new List[N+1];

        for (int i = 1; i <= N; i++) {
            next[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            String[] data = br.readLine().split(" ");
            int A = Integer.parseInt(data[0]);
            int B = Integer.parseInt(data[1]);

            inDegree[B]++;
            next[A].add(B);
        }

        // N번이 가장 어려운 문제 -> pq로 해결
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) q.add(i);
        }


        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");

            Collections.sort(next[cur]);
            for (int i : next[cur]) {
                if (--inDegree[i] == 0) q.add(i);
            }
        }


        System.out.println(sb);

    }

}
