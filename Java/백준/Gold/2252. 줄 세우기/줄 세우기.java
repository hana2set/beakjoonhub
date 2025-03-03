import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);

        // 위상정렬 알고리즘
        int[] degree = new int[N+1];
        List<Integer>[] loads = new List[N+1];

        for (int i = 0; i < N+1; i++) {
            loads[i] = new ArrayList<>();
        }


        while (M-- > 0) {
            data = br.readLine().split(" ");

            int A = Integer.parseInt(data[0]);
            int B = Integer.parseInt(data[1]);
            loads[A].add(B);

            degree[B]++;
        }
        
        // 진입차수가 0 이면 추가
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < N+1; i++) {
            if (degree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");

            // 다음 진입차수가 0이면 추가 (사이클이 없음)
            for (int next : loads[cur]) {
                degree[next]--;
                if (degree[next] == 0) q.add(next);
            }
            
        }

        System.out.print(sb);

    }

}
