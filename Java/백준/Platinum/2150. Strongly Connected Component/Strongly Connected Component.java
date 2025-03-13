import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static List<Integer>[] graph;
    static List<Integer>[] revGraph;
    static boolean[] visit;

    // 끝점 저장용 스택
    static Stack<Integer> s = new Stack<>();
    // 결과값
    static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 코사라주 알고리즘
        // 1. dfs로 끝점 계산
        // 2. 역방향 그래프 dfs로 그룹 구분

        String[] data = br.readLine().split(" ");
        int V = Integer.parseInt(data[0]);
        int E = Integer.parseInt(data[1]);
        graph = new List[V+1];
        revGraph = new List[V+1];
        visit = new boolean[V+1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            String[] edge = br.readLine().split(" ");
            int A = Integer.parseInt(edge[0]);
            int B = Integer.parseInt(edge[1]);
            graph[A].add(B);
            revGraph[B].add(A);
        }


        for (int i = 1; i <= V; i++) {
            if (!visit[i]) dfs(i);
        }

        visit = new boolean[V+1];

        while (!s.isEmpty()) {
            int value = s.pop();
            if (!visit[value]) {
                result.add(revDfs(value, new ArrayList<>()));
            }
        }

        for (int i = 0; i < result.size(); i++) {
            Collections.sort(result.get(i));
        }
        result.sort(Comparator.comparing(a -> a.get(0)));

        // 출력부
        sb.append(result.size()).append("\n");
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                sb.append(result.get(i).get(j)).append(" ");
            }
            sb.append(-1).append("\n");
        }


        System.out.println(sb);

    }


    private static void dfs(int val) {
        visit[val] = true;

        for (int next : graph[val]) {
            if (!visit[next]) dfs(next);
        }

        // 끝점부터 순서대로 입력
        s.push(val);
    }

    private static List<Integer> revDfs(int val, List<Integer> list) {
        visit[val] = true;
        list.add(val);

        for (int next : revGraph[val]) {
            if (!visit[next]) {
                revDfs(next, list);
            }
        }

        return list;
    }

}
