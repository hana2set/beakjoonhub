import java.io.*;
import java.util.*;

class Main {

    static int N;
    static Map<Integer, Set<Integer>> network = new HashMap<>();
    static boolean[] visit;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        visit = new boolean[N+1];

        for (int i = 1; i <= N ; i++) {
            network.put(i, new HashSet<>());
        }


        for (int i = 0; i < M ; i++) {

            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            network.computeIfAbsent(line[0], k -> new HashSet<>());
            network.get(line[0]).add(line[1]);
            network.get(line[1]).add(line[0]);
        };


        dfs(1);

        System.out.println(count);
    }

    private static void dfs(int num) {
        if (visit[num] == true) return;

        if (num != 1) count++;
        visit[num] = true;
        for (int next : network.get(num)) {
            dfs(next);
        }
    }

}