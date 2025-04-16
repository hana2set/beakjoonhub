import java.io.*;
import java.util.*;

class Main {

    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 스위핑, 세그먼트 트리 응용
        // 남,동쪽으로만 움직일 수 있음으로, 순서만 바뀌지 않으면 스케일링해도 문제 없음.
        // -> y축으로 정렬한 후 1~t로 스케일링
        // -> 다시 x축으로 정렬한 후, 스케일링한 y축을 기준으로 x분포 세그먼트 트리를 만들 수 있음.
        // -> 세그먼트 트리를 하나씩 추가하면서, 아래 있는 갯수 (방문가능한 갯수)를 카운팅하면 방문 가능 갯수를 계산 가능
        // -> 반복하면서 x~t까지 갯수를 구하면 시간복잡도 log n * n으로 계산 가능

        int testCount = Integer.parseInt(br.readLine());
        while (testCount-- > 0) {
            int n = Integer.parseInt(br.readLine());


            int[][] island = new int[n][2];

            // 좌표 정렬
            for (int i = 0; i < n; i++) {
                String[] coor = br.readLine().split(" ");
                int x = Integer.parseInt(coor[0]);
                int y = Integer.parseInt(coor[1]);

                island[i] = new int[]{x, y};
            }

            // y축 정렬 후 스케일링 (역순으로 정렬해 남쪽방향 설계)
            Arrays.sort(island, Comparator.<int[]>comparingInt(a -> a[1]).reversed());

            int bValue = island[0][1];
            int yIndex = 1;
            for (int i = 0; i < n; i++) {
                if (bValue == island[i][1]) {
                    island[i][1] = yIndex;
                } else {
                    bValue = island[i][1];
                    island[i][1] = ++yIndex;
                }
            }


            // x축 정렬 후 세그먼트 트리 생성
            Arrays.sort(island, (a,b) -> {
                if (a[0]==b[0]) return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0],b[0]);
            });

            tree = new int[4*n];

            long count = 0;
            for (int i = 0; i < n; i++) {
                // 오름차순 정렬 -> x가 커지는 순서대로 루프를 돔으로,
                // 트리에 입력되어있는 값 중 y가 작은 값만 고려하면 됨 (해당 섬으로 여행 가능한 섬 계산)
                count += query(1, yIndex, 1, island[i][1], 1);
                update(1, yIndex, island[i][1], 1);
            }

            sb.append(count).append("\n");
        }


        System.out.println(sb);
    }

    private static int query(int start, int end, int left, int right, int node) {
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