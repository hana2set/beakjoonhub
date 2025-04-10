import java.io.*;

class Main {

    static int[] tree;
    static int N;
    static final int MAX_VALUE = 2_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 세그먼트 트리 응용
        // 값이 큼 - k번째 수를 구하는데 시간이 오래걸릴 수 있음
        // X의 크기가 최대 2_000_000임으로, 그 수에 해당하는 갯수를 세그먼트 트리에 저장해, logN 의 복잡도로 해당 수를 찾아낼 수 있음.
        // 즉 해당 구간(숫자)에 포함되는 갯수를 세그먼트 트리를 만들 것
        N = Integer.parseInt(br.readLine());
        tree = new int[MAX_VALUE *4];

        while (N-- > 0) {
            String[] input = br.readLine().split(" ");
            int T = Integer.parseInt(input[0]);
            int X = Integer.parseInt(input[1]);

            if (T == 1) {
                update(1, MAX_VALUE, 1, X);
            } else if (T == 2) {
                sb.append(query(1, MAX_VALUE, 1, X)).append("\n");
            }
        }

        System.out.println(sb);

    }


    // idx번째 숫자 찾기 and idx번째 숫자 빼기
    private static int query(int start, int end, int node, int idx) {
        tree[node]--;

        if (start == end) return start;

        int mid = (start+end) / 2;
        if (tree[node*2] < idx) {
            // 앞 숫자 갯수가 idx 보다 작다면,
            // mid에서 end 노드를 검색, idx-앞숫자갯수 만큼 번호로 찾기
            return query(mid+1, end, node*2+1, idx - tree[node*2]);
        } else {
            return query(start, mid, node*2, idx);
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


}