import java.io.*;

class Main {

    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 세그먼트 트리 응용
        // k번 째 값 찾기 -> 인덱스를 세그먼트 트리로 구성
        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int K = Integer.parseInt(data[1]);
        tree = new int[N*4];

        makeTree(N);

        sb.append("<");
        int next = 1;
        for (int i = 0; i < N; i++) {
            next += K-1; // 이전 값에서 K번 다음으로 큰 수
            next %= N-i;
            if (next == 0) next = N-i;

            sb.append(query(1, N, 1, next)).append(", ");
        }
        sb.setLength(sb.length()-2);
        sb.append(">");

        System.out.println(sb);

    }

    private static void makeTree(int n) {
        for (int i = 1; i <= n; i++) {
            update(1, n, 1, i);
        }
    }

    private static void update(int start, int end, int node, int x) {
        if (x < start || x > end) return;

        tree[node]++;

        if (start == end) return;

        int mid = (start+end) / 2;
        update(start, mid, node*2, x);
        update(mid+1, end, node*2+1, x);

    }

    // k번째 숫자 찾고 tree에서 지우기
    private static int query(int start, int end, int node, int k) {
        tree[node]--;

        if (start == end) return start;

        int mid = (start+end) / 2;
        if (k <= tree[node*2]) { //k가 1~mid 총 갯수보다 작거나 같으면
            return query(start, mid, node*2, k);
        } else {
            return query(mid+1, end, node*2+1, k - tree[node*2]);
        }
    }


}