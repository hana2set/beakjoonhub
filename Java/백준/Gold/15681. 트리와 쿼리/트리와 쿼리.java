import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {

    static boolean[] visit;
    static Map<Integer, List<Integer>> tree = new HashMap<>();

    static int[] vertexCount; // 서브트리 정점의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] data = br.readLine().split(" ");

        int N = Integer.parseInt(data[0]);
        int R = Integer.parseInt(data[1]);
        int Q = Integer.parseInt(data[2]);

        visit = new boolean[N+1];
        vertexCount = new int[N+1];

        for (int i = 0; i < N-1; i++) {
            String[] uvStr = br.readLine().split(" ");
            int U = Integer.parseInt(uvStr[0]);
            int V = Integer.parseInt(uvStr[1]);

            tree.computeIfAbsent(U, k -> new ArrayList<>());
            tree.computeIfAbsent(V, k -> new ArrayList<>());
            tree.get(U).add(V);
            tree.get(V).add(U);
        }


        dfs(R); //정점부터 갯수 탐색


        while (Q-- > 0) {
            System.out.println(
                    vertexCount[Integer.parseInt(br.readLine())]
            );
        }



    }

    private static int dfs(int r) {
        visit[r] = true;

        int count = 1;
        for (int target : tree.get(r)) {
            if (visit[target] == false) {
                count += dfs(target);
            }
        }

        vertexCount[r] = count;
        return count;
    }

}