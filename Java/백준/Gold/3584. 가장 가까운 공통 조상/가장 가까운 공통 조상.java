import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] parent; // 부모 노드
    static List<Integer>[] child;
    static int[] depth; // 깊이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // LCA, 공통 조상
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            parent = new int[N+1]; // 부모 노드
            child = new List[N+1];
            depth = new int[N+1]; // 깊이

            for (int i = 1; i <= N; i++) {
                child[i] = new ArrayList<>();
            }

            for (int i = 1; i < N; i++) { // N-1개의 간선이 주어짐
                String[] data = br.readLine().split(" ");
                int A = Integer.parseInt(data[0]);
                int B = Integer.parseInt(data[1]);

                parent[B] = A;
                child[A].add(B);
            }

            // 루트 구하기
            int root = 1;
            while (parent[root] != 0) {
                root = parent[root];
            }

            // 깊이 입력하기
            setDepth(0, root);

            String[] data = br.readLine().split(" ");
            int A = Integer.parseInt(data[0]);
            int B = Integer.parseInt(data[1]);

            // 공통 조상 구하기
            System.out.println(getLCA(A, B));


        }




    }

    private static int getLCA(int a, int b) {

        while (depth[a] != depth[b]) {
            if (depth[a] > depth[b]) a = parent[a];
            else b = parent[b];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void setDepth(int index, int nodeNum) {
        depth[nodeNum] = index;

        for (int next : child[nodeNum]) {
            setDepth(index+1, next);
        }
    }

}
