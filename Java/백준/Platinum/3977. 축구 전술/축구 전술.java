import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static List<Integer>[] graph;
    static boolean[] finished;
    static int[] sccNum;

    // 타잔 알고리즘용 변수
    static int index, sccIndex;
    static int[] id;
    static Stack<Integer> s = new Stack<>();

    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // SCC를 구하면 그 그룹 내에선 사이클임으로,
        // SCC를 구하고, SCC 그룹의 진입차수가 0인 그룹을 나열하면됨.
        // 단, 진입차수가 0인 그룹이 2개 이상인 경우, 두 그룹을 동시에 도달할 가능성은 없음 (도미노)

        int caseCount = Integer.parseInt(br.readLine());

        while (caseCount-- > 0) {

            // 변수 입력
            String[] data = br.readLine().split(" ");
            int N = Integer.parseInt(data[0]); // 도미노 갯수
            int M = Integer.parseInt(data[1]); // 간선의 갯수

            graph = new List[N];
            sccNum = new int[N];
            finished = new boolean[N];

            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                String[] edge = br.readLine().split(" ");
                int A = Integer.parseInt(edge[0]);
                int B = Integer.parseInt(edge[1]);
                graph[A].add(B);
            }

            // 타잔 알고리즘
            index = 0;
            sccIndex = 1;
            id = new int[N];

            // SCC 부여
            for (int i = 0; i < N; i++) {
                if (!finished[i]) dfs(i);
            }

            // 진입차수 부여
            indegree = new int[sccIndex];
            for (int i = 0; i < N; i++) {
                for (int next : graph[i]) {
                    // 자기자신으로 가는 것 제외 (사이클)
                    if (sccNum[i] != sccNum[next]) indegree[sccNum[next]]++;
                }
            }

            // indegree 0인 그룹 찾기
            int zeroIndex = -1;
            boolean isConfused = false;
            for (int i = 1; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    if (zeroIndex != -1) {
                        isConfused = true;
                        break;
                    }

                    zeroIndex = i;
                }
            }

            if (isConfused) {
                sb.append("Confused").append("\n");
            } else {
                for (int i = 0; i < N; i++) {
                    if (sccNum[i] == zeroIndex) {
                        sb.append(i).append("\n");
                    }

                }
            }

            sb.append("\n");
            br.readLine();
        }

        System.out.println(sb);
    }


    // 해당 인수의 부모 반환
    private static int dfs(int cur) {
        // id 부여
        id[cur] = ++index;
        s.push(cur);

        // parent 계산
        int parent = id[cur];
        for (int next : graph[cur]) {
            if (id[next] == 0) parent = Math.min(dfs(next), parent);
            else if (!finished[next]) parent = Math.min(id[next], parent);
        }

        // 연산된 부모(SCC의 부모, 가장 작은값)와 자기자신과 같음
        // -> 사이클발생
        // -> SCC 편입
        if (parent == id[cur]) {
            while (true) {
                // 스택에서 자기자신이 나올때 까지 출력(한묶음)
                int pop = s.pop();
                finished[pop] = true;
                sccNum[pop] = sccIndex;

                if (pop == cur) break;
            }

            sccIndex++;
        }

        return parent;
    }

}
