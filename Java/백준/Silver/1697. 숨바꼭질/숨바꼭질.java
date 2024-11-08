import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        visit = new int[100_001];

        System.out.println(bfs(N)-1);

    }

    private static int bfs(int x) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);

        visit[x] = 1;

        while (!q.isEmpty()) {
            int p = q.poll();

            if (p == M) {
                return visit[p];
            }

            // 재귀 방문
            if (p-1 >= 0 && visit[p-1] == 0) {
                q.add(p-1);
                visit[p-1] = visit[p]+1;
            }

            if (p+1 <= 100_000 && visit[p+1] == 0) {
                q.add(p+1);
                visit[p+1] = visit[p]+1;
            }

            if (p*2 <= 100_000 && visit[p*2] == 0) {
                q.add(p*2);
                visit[p*2] = visit[p]+1;
            }

        }

        return -1;
    }
}
