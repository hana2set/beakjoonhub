import java.io.*;
import java.util.*;

class Main {

    static int index = 1;
    static int[] visited;
    static Map<Integer, List<Integer>> line = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int R = Integer.parseInt(input[2]);

        visited = new int[N+1];
        for (int i = 1; i <= N; i++) {
            line.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            line.get(u).add(v);
            line.get(v).add(u);
        }

        bfs(R);

        for (int i = 1; i <= N; i++) {
            System.out.println(visited[i]);
        }

    }

    private static void bfs(int r) {
        Queue<Integer> q = new LinkedList<>();

        visited[r] = index++;
        q.add(r);

        while (!q.isEmpty()) {
            int value = q.poll();

            Collections.sort(line.get(value));

            for (int v : line.get(value)) {
                if (visited[v] == 0) {
                    visited[v] = index++;
                    q.add(v);
                }
            }

        }
    }

}
