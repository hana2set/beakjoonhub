import java.io.*;

class Main {

    static long[] arr, tree;
    static long mod = 1_000_000_007;

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
                arr[b] = c;
                update(1, N, 1, b, c);
            }
            else if (a==2) sb.append(multiple(1, N, 1, b, (int) c)).append("\n");
        }

        System.out.println(sb);
    }


    private static long makeTree(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        
        int mid = (start+end) / 2;
       
        return tree[node] = makeTree(start, mid, node*2) * makeTree(mid+1, end, node*2+1) % mod;
    }

    private static long update(int start, int end, int node, int idx, long val) {
        // 곱임으로, makeTree 방식으로 포함되는 인덱스를 다시 계산해야함

        // 범위 밖이면 스킵
        if (idx < start || end < idx) return tree[node];

        // 해당 값인 경우
        if (start == end) return tree[node] = arr[start];

        // 해당값 포함인 경우 다시 계산
        int mid = (start+end) / 2;
        return tree[node] = update(start, mid, node*2, idx, val) * update(mid+1, end, node*2+1, idx, val) % mod;
    }

    // 구간 합 범위 : left ~ right
    private static long multiple(int start, int end, int node, int left, long right) {
        // 범위 밖
        if (right < start || end < left) return 1;

        // 범위에 완벽히 안에 있는 경우
        if (left <= start && end <= right) return tree[node];

        // 걸쳐있는 경우
        int mid = (start+end) / 2;
        return multiple(start, mid, node*2, left, right) * multiple(mid+1, end, node*2+1, left, right) % mod;

    }

}