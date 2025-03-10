import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, height;
    static List<Integer>[] child;
    static int[] level;
    static int[][] parent;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // lca2
        // 초기화
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i*=2) height++; // N값에 따른 최대 차수 결정

        child = new List[N+1];
        level = new int[N+1];
        parent = new int[height][N+1];

        for (int i = 1; i <= N; i++) {
            child[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            String[] data = br.readLine().split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);

            child[a].add(b);
            child[b].add(a);
        }

        setVariable(1, 1);
        setParent();

        // 출력
        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            String[] data = br.readLine().split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);

            sb.append(getParent(a, b)).append("\n");
        }

        System.out.println(sb);

    }

    // level, parent 초기값 생성
    private static void setVariable(int node, int index) {
        level[node] = index;

        for (int next : child[node]) {
            if (level[next] != 0) continue;

            parent[0][next] = node;
            setVariable(next, index+1);
        }
    }

    // 차수별 parent 생성
    private static void setParent() {
        for (int i = 1; i < height; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }

    private static int getParent(int a, int b) {
        int l = level[a] < level[b] ? a : b;
        int r = level[a] < level[b] ? b : a;

        // level 맞추기
        int dif = level[r]-level[l];
        for (int i = 0; i < height; i++) {
            if (((1<<i) & dif) > 0) { //결합법칙 성립 -> 각자 계산
                r = parent[i][r]; //i 차수만큼 부모 거슬러 올라가기
            }
        }

        // 특정 차수부터 부모가 같으면 그 이후로는 항상 같음 -> height인 경우의 수만 따지면 됨
        for (int i = height-1; i >= 0 && l != r; i--) {
            if (parent[i][l] != parent[i][r]) {
                l = parent[i][l];
                r = parent[i][r];
            }
        }

        // 부모가 같음 -> l == r 인지 먼저 확인
        return l == r ? l : parent[0][l];
    }
}
