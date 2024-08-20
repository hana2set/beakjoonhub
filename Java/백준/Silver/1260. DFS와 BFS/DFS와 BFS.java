import java.io.*;
import java.util.*;

class Main {

    static Map<Integer, List<Integer>> lines = new HashMap<>();
    static boolean[] visit;
    static Queue<Integer> q = new LinkedList<Integer>(); //bfs용
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = temp[0];
        int M = temp[1];
        int V = temp[2];

        visit = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            lines.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lines.get(line[0]).add(line[1]);
            lines.get(line[1]).add(line[0]);
        }

        dfs(V);

        visit = new boolean[N+1]; //초기화
        sb.append("\n");

        bfs(V);

        System.out.println(sb.toString());
    }

    private static void dfs(int start) {
        if (visit[start] == true) return;
        visit[start] = true;
        sb.append(start + " ");

        Collections.sort(lines.get(start));

        for (int i : lines.get(start)) {
            dfs(i);
        }

    }

    private static void bfs(int start) {
        if (visit[start] == true) return;
        visit[start] = true;
        sb.append(start + " ");

        q.addAll(lines.get(start));

        while (!q.isEmpty()) {
            bfs(q.poll());
        }

    }



}