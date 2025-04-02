import java.io.*;

class Main {

    static long[] arr, maxTree, minTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 세그먼트 트리 예제
        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);

        arr = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 트리의 사이즈는 등비수열의 합의 형태를 띔으로, 최하단 노드의 갯수(~=N)의 2배보다 1 작음.
        // N의 최소값은 (최하단 노드/2)+1 임으로, 대략 4배 보다 작음을 알 수 있다.
        maxTree = new long[N*4];
        minTree = new long[N*4];

        makeMinTree(1, N, 1);
        makeMaxTree(1, N, 1);

        for (int i = 0; i < M; i++) {
            data = br.readLine().split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);

            sb.append(minValue(1, N, 1, a, b)).append(" ").append(maxValue(1, N, 1, a, b)).append("\n");
        }

        System.out.println(sb);
    }


    private static long makeMaxTree(int start, int end, int node) {
        if (start == end) return maxTree[node] = arr[start];
        
        int mid = (start+end) / 2;
       
        return maxTree[node] = Math.max(makeMaxTree(start, mid, node*2), makeMaxTree(mid+1, end, node*2+1));
    }

    private static long makeMinTree(int start, int end, int node) {
        if (start == end) return minTree[node] = arr[start];

        int mid = (start+end) / 2;

        return minTree[node] = Math.min(makeMinTree(start, mid, node*2), makeMinTree(mid+1, end, node*2+1));
    }

    private static long minValue(int start, int end, int node, int left, long right) {
        // 범위 밖
        if (right < start || end < left) return Long.MAX_VALUE;

        // 범위에 완벽히 안에 있는 경우
        if (left <= start && end <= right) return minTree[node];

        // 걸쳐있는 경우
        int mid = (start+end) / 2;
        return Math.min(minValue(start, mid, node*2, left, right), minValue(mid+1, end, node*2+1, left, right));
    }


    private static long maxValue(int start, int end, int node, int left, long right) {
        // 범위 밖
        if (right < start || end < left) return 0;

        // 범위에 완벽히 안에 있는 경우
        if (left <= start && end <= right) return maxTree[node];

        // 걸쳐있는 경우
        int mid = (start+end) / 2;
        return Math.max(maxValue(start, mid, node*2, left, right), maxValue(mid+1, end, node*2+1, left, right));
    }

}