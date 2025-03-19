import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N, M;
    static List<Integer>[] graph;

    // 타잔 알고리즘
    static int[] id;
    static boolean[] finished;
    static int index, sccIndex;
    static Stack<Integer> s = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 절끼리 AND로 연결되어 있음 -> 모든 절이 true인 경우로 계산하면 됨
        // 절내부는 OR로 연결됨 -> 둘중 하나는 참
        //
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        graph = new List[2*N+1]; // 1부터 시작, 음수 포함됨 (-N ~ N)

        for (int i = 1; i <= 2*N; i++) {
            graph[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            String[] road = br.readLine().split(" ");
            int i = Integer.parseInt(road[0]);
            int j = Integer.parseInt(road[1]);

            // 양방향 그래프
            graph[getIndex(-i)].add(getIndex(j));
            graph[getIndex(-j)].add(getIndex(i));
        }

        if (isPossible()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
        
    }

    private static boolean isPossible() {
        // scc 변수 초기화
        index = 0;
        sccIndex = 1;
        id = new int[2*N+1];
        finished = new boolean[2*N+1];

        for (int i = 1; i <= 2*N; i++) {
            if (!finished[i]) {
                if (scc(i) == -1) return false;
            }
        }

        return true;
    }

    private static int scc(int cur) {
        id[cur] = ++index;
        s.add(cur);

        int parent = id[cur];
        for (int next : graph[cur]) {
            if (id[next] == 0) parent = Math.min(scc(next), parent);
            else if (!finished[next]) parent = Math.min(id[next], parent);
        }

        if (parent == id[cur]) {
            boolean[] check = new boolean[2*N+1];
            while (!s.isEmpty()) {
                int p = s.pop();

                check[p] = true;
                finished[p] = true;

                int nagitiveValue = p > N ? p-N : p+N;
                if (check[nagitiveValue]) return -1;



                // 그룹 번호 알 필요 없이, 역방향 중복 여부만 확인하면됨
                if (p == cur)
                    break;
            }
        }

        return parent;
    }

    private static int getIndex(int n) {
        if (n < 0) return -n+N;
        return n;
    }

}