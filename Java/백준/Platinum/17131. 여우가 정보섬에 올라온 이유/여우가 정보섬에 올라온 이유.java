import java.io.*;
import java.util.*;

class Main {

    static int[] tree;
    static long mod = (long) 1e9+7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 스위핑, 세그먼트 트리 응용
        // V 모양임으로, y축으로 정렬한 뒤
        // 아래로 내려가면서 입력되는 점의 좌우 x의 갯수를 서로 곱하면 (추가점 기준으로 좌우에서 점 하나씩 선택) 쉽게 카운팅 가능
        // 주의점은 같은 높이의 y는 동시에 입력해야, 세그먼트 트리 계산 시 꼬이지 않음

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        // 좌표 정렬
        for (int i = 0; i < N; i++) {
            String[] coor = br.readLine().split(" ");
            int x = Integer.parseInt(coor[0]);
            int y = Integer.parseInt(coor[1]);

            arr[i] = new int[]{x, y};
        }

        // x축 정렬 후 스케일링
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int before = arr[0][0];
        int xIdx = 1;
        for (int i = 0; i < N; i++) {
            if (before == arr[i][0]) {
                arr[i][0] = xIdx;
            } else {
                before = arr[i][0];
                arr[i][0] = ++xIdx;
            }
        }

        // y축으로 내림차순 정렬
        Arrays.sort(arr, (a,b) -> {
            if (a[1]==b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1],a[1]);
        });

        tree = new int[4*N];

        long count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            long left = query(1, N, 1, arr[i][0]-1, 1);
            long right = query(1, N, arr[i][0]+1, N, 1);

            count += (left*right) % mod;
            count %= mod;

            q.add(arr[i][0]);
            // 다음 인덱스가 마지막이거나 다음 값과 y가 다름
            if (i+1 >= N || arr[i][1] != arr[i+1][1]) {
                while (!q.isEmpty()) {
                    update(1, N, q.poll(), 1);
                }
            }
        }

        System.out.println(count);

    }

    private static long query(int start, int end, int left, int right, int node) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start+end) / 2;

        return query(start, mid, left, right, node*2) + query(mid+1, end, left, right, node*2+1);
    }

    private static void update(int start, int end, int value, int node) {
        if (value < start || end < value) return;

        tree[node]++;

        if (start == end) return;

        int mid = (start+end) / 2;

        update(start, mid, value, node*2);
        update(mid+1, end, value, node*2+1);
    }

}