import java.io.*;
import java.util.*;

class Main {

    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 세그먼트 트리 응용
        // swap 횟수는 해당 숫자보다 오른쪽 수 중 크기가 작은 수의 갯수로 결정된다.
        // 전체 배열을 작은 숫자부터 세그먼트 트리를 하나씩 더하면서, 구간별로 현재 값보다 작은 값의 갯수를 계산할 수 있다.
        // 트리: 구간에서 자기보다 작은 값의 갯수
        // 즉, 업데이트 직전 트리에서, [현재값의 index, N-1] 구간의 트리의 합이 swap 갯수가 됨 (작은 숫자보다 뒤로가야 함으로)
        // 시간복잡도는 O(NlogN)
        int N = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        // 업데이트할 트리에 대한 정보
        Map<Long, Queue<Integer>> index = new HashMap<>();
        for (int i = 0; i < N; i++) {
            index.computeIfAbsent(arr[i], k -> new LinkedList<>()).offer(i);
        }

        // arr를 정렬해, 작은 순으로 트리를 update함
        Arrays.sort(arr);

        tree = new long[N*4];
        long answer = 0;

        for(int i=0; i<N; i++)	{
//            for (int idx : index.get(val)) {

            int idx = index.get(arr[i]).poll();
                // 뒤에서[idx+1, N-1] 자신보다 작은 값 더하기
                answer += count(0, N-1, 1, idx+1, N-1);
                // 자기 자신 트리에 업데이트
                update(0, N-1, 1, idx);
//            }
        }

        System.out.println(answer);
    }

    // 세그먼트 트리 갱신
    private static void update(int start, int end, int node, int idx) {
        if (start == end) {
            //인덱스마다 한개의 값이 있음으로, 값은 항상 1개
            tree[node] = 1;
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid) update(start, mid, node*2, idx);
        else update(mid+1, end, node*2+1, idx);

        tree[node] = tree[node*2] + tree[node*2+1];
    }

    private static long count(int start, int end, int node, int left, int right) {
//        if (right < start || end < left) return 0;
//        if (left <= start && end <= right) return tree[node];
//
//        int mid = (start + end) / 2;
//        return count(start, mid, node*2, left, right) + count(mid+1, end, node*2+1, left, right);
        //구하는 범위를 벗어났을 때
        if(left > end || right < start) {
            return 0L;
        }
        //구하는 범위에 속했을 때
        if(left <= start && end <= right){
            return tree[node];
        }
        //하위 노드 탐색
        int mid = (start + end) / 2;
        return count(start, mid, node*2, left, right) + count(mid+1, end, node*2 + 1, left, right);
    }

}