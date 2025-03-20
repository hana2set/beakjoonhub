import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N, M;
    static List<Integer>[] graph;

    // 타잔 알고리즘
    static int[] id;
    static int[] sccNum;
    static boolean[] finished;
    static int index, sccIndex;
    static Stack<Integer> s = new Stack<>();

    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 절끼리 AND로 연결되어 있음 -> 모든 절이 true인 경우로 계산하면 됨
        // 절내부는 OR로 연결됨 -> 둘중 하나는 참
        //
        // 절은 and조건임으로, (x, y)일 경우 둘중 하나가 거짓이면 나머지는 참이어야함.
        // 즉, ~x -> y, ~y -> x 두개의 방향 그래프를 확정 가능
        // 이 두 방향 간선을 통해 SCC 계산 후
        // 연결된 SCC 내에서 모순된 연결 형태(x, -x)이 없는지 확인하면 됨
        //
        // 타잔 알고리즘은 결과값이 위상정렬의 역순으로 SCC 번호가 매겨지기때문에,
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

        if (makeScc()) {
            sb.append(1).append("\n");

            // scc번호가 위상정렬의 역순 -> 작은 수가 더 큰 우선순위
            for (int i = 1; i <= N; i++) {
                if (sccNum[i] > sccNum[i+N]) sb.append(0).append(" ");
                else sb.append(1).append(" ");
            }
        } else {
            sb.append(0);
        }

        System.out.println(sb);
    }

    private static boolean makeScc() {
        // scc 변수 초기화
        index = 0;
        sccIndex = 1;
        id = new int[2*N+1];
        finished = new boolean[2*N+1];
        sccNum = new int[2*N+1];

        // 결과값 계산
        result = new int[N+1];

        for (int i = 1; i <= N; i++) {
            result[i] = -1;
        }

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
                sccNum[p] = sccIndex;

                int nagitiveValue = p > N ? p-N : p+N;
                if (check[nagitiveValue]) return -1;

                if (p == cur)
                    break;
            }

            sccIndex++;
        }

        return parent;
    }

    private static int getIndex(int n) {
        if (n < 0) return -n+N;
        return n;
    }

}