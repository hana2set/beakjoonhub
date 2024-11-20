import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        System.out.println( bfs(N, K) );

    }

    private static int bfs(int n, int k) {
        if (n == k) return 0;

        boolean[] visit = new boolean[100_001]; //방문한 곳 또 방문할 필요 없음

        PriorityQueue<int[]> q = new PriorityQueue(Comparator.comparing((int[] a) -> a[1]));

        q.add(new int[]{n, 0});

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int position = info[0];
            int time = info[1];

            for (int next : new int[] {position*2, position-1, position+1}) {
                int nextTime = time + (next == position*2 ? 0 : 1);

                while (0 <= next && next <= 100000) {
                    if (visit[next] == true) break;

                    if (next == k) return nextTime;

                    visit[next] = true;
                    q.add(new int[] {next, nextTime});

                    next *= 2;
                }
            }

        }

        return -1;
    }


}
