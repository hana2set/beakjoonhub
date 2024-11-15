import java.io.*;
import java.util.*;

class Main {

    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        // 이분 그래프 -> 두가지 색으로 칠할 수 있으면 됨
        // 정렬 후 BFS로 칠하면서, 이미 다른색으로 칠해지면 이분 그래프 불가능
        while (K-- > 0) {
            int[] cond = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int V = cond[0];
            int E = cond[1];

            HashMap<Integer, Integer> point = new HashMap<>();
            Map<Integer, Set<Integer>> lines = new HashMap<>();

            // 간선 초기화
            for (int i = 1; i <= V; i++) {
                lines.put(i, new HashSet<>());
            }

            for (int i = 0; i < E; i++) {
                int[] uv = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int u = uv[0];
                int v = uv[1];

                lines.get(u).add(v);
                lines.get(v).add(u);
            }

            boolean possible = true;

            // 정점 색칠하기
            loop1:
            for (int i = 1; i <= V; i++) {
                if (point.get(i) == null) {

                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, 1});

                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        for (int target : lines.get(p[0])) {
                            if (point.get(target) == null) {
                                point.put(target, -p[1]);
                                q.add(new int[]{target, -p[1]});
                                continue;
                            }

                            if (point.get(target) == p[1]) {
                                possible = false;
                                break loop1;
                            }

                        }

                    }

                }

            }
            if (possible) System.out.println("YES");
            else  System.out.println("NO");

        }
    }
}