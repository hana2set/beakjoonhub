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

    // 위상 정렬용 변수
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // SCC와 위상 정렬의 혼합문제.
        // 위상 정렬은 사이클이 있는 문제에 적합하지 않으나, (비순환 방향 그래프에서 사용됨)
        // 사이클이 있는 그룹들을 SCC로 묶으면 (사이클 그룹은 하나만 쓰려뜨려도 한번에 쓰러짐)
        // SCC끼리의 진입차수를 구할 수 있기때문에,
        // SCC를 통해 그룹을 구분하고, SCC를 위상정렬하면, 최소 갯수를 구할 수 있음.

        // SCC에는 타잔 알고리즘 사용할 계획

        int caseCount = Integer.parseInt(br.readLine());

        while (caseCount-- > 0) {

            // 변수 입력
            String[] data = br.readLine().split(" ");
            int N = Integer.parseInt(data[0]); // 도미노 갯수
            int M = Integer.parseInt(data[1]); // 간선의 갯수

            graph = new List[N+1];
            sccNum = new int[N+1];
            finished = new boolean[N+1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                String[] edge = br.readLine().split(" ");
                int x = Integer.parseInt(edge[0]);
                int y = Integer.parseInt(edge[1]);
                graph[x].add(y);
            }

            // 타잔 알고리즘
            index = 0;
            sccIndex = 1;
            id = new int[N+1];

            // SCC 부여
            for (int i = 1; i <= N; i++) {
                if (!finished[i]) dfs(i);
            }

            // 진입차수 부여
            indegree = new int[sccIndex];
            for (int i = 1; i <= N; i++) {
                for (int next : graph[i]) {
                    // 자기자신으로 가는 것 제외 (사이클)
                    if (sccNum[i] != sccNum[next]) indegree[sccNum[next]]++;
                }
            }

            // 도미노 쓰러뜨리기
            int answer = 0;
            for (int i = 1; i < sccIndex; i++) {
                // 진입차수가 0 -> 직접 쓰러뜨려야함
                if (indegree[i] == 0) answer++;
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }


    // 해당 인수의 부모 반환
    private static int dfs(int cur) {
        // id 부여
        id[cur] = ++index;
        s.push(cur);

        // 포함된 SCC에서 가장 작은 값 = parent
        int parent = id[cur];
        for (int next : graph[cur]) {
            // id가 없다면(미방문) -> SCC에서 가장 작은값을 부모로
            if (id[next] == 0) parent = Math.min(dfs(next), parent);
            // id가 있고(방문했으나), SCC에 포함되어있지 않다면 -> 현재값의 부모는 가장 작은 값
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
