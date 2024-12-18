import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 방향이 주어져있지 않음으로
        // 양방향 연결 후 루트에서 내려가면서 갱신
        int N = Integer.parseInt(br.readLine());
        int[] parent = new int[N+1];
        List<Integer>[] node = new List[N+1];
        boolean[] visit = new boolean[N+1];

        for (int i = 0; i < node.length; i++) {
            node[i] = new ArrayList<>();
        }

        int index = N;
        while (index-- > 1) {
            String[] input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int c = Integer.parseInt(input[1]);

            node[r].add(c);
            node[c].add(r);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty()) {
            int val = q.poll();

            for (int child : node[val]) {

                if (visit[child] == true) continue;
                visit[child] = true;
                parent[child] = val;
                q.add(child);
            }

        }

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.println(sb);
    }

}
