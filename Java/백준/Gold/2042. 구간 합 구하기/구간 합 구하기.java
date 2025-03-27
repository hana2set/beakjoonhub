import java.io.*;
import java.util.*;

class Main {

    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 세그먼트 트리 예제
        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);
        int K = Integer.parseInt(data[2]);

        arr = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 트리의 사이즈는 등비수열의 합의 형태를 띔으로, 최하단 노드의 갯수(~=N)의 2배보다 1 작음.
        // N의 최소값은 (최하단 노드/2)+1 임으로, 대략 4배 보다 작음을 알 수 있다.
        tree = new long[N*4];
        
        makeTree(1, N, 1);

        for (int i = 0; i < M+K; i++) {
            data = br.readLine().split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);
            long c = Long.parseLong(data[2]);

            if (a==1) {
                long dif = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, dif);
            }
            else if (a==2) sb.append(sum(1, N, 1, b, (int) c)).append("\n");
        }

        System.out.println(sb);
    }


    private static long makeTree(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        
        int mid = (start+end) / 2;
       
        return tree[node] = makeTree(start, mid, node*2) + makeTree(mid+1, end, node*2+1);
    }

    private static void update(int start, int end, int node, int idx, long dif) {
        if (idx < start || end < idx) return;

        tree[node] += dif;

        if (start == end) return;

        int mid = (start+end) / 2;
        update(start, mid, node*2, idx, dif);
        update(mid+1, end, node*2+1, idx, dif);
    }

    // 구간 합 범위 : left ~ right
    private static long sum(int start, int end, int node, int left, long right) {
        // 범위 밖
        if (right < start || end < left) return 0;

        // 범위에 완벽히 안에 있는 경우
        if (left <= start && end <= right) return tree[node];

        // 걸쳐있는 경우
        int mid = (start+end) / 2;
        return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);

    }

}