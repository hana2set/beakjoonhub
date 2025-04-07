import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

class Main {

    static int[] arr, maxTree, minTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 세그먼트 트리 응용
        // a~b까지 빌린다고 가정하면 최솟값이 a, 최대값이 b 임으로,
        // 바꿔치기할 때 트리 변경 후,
        // 가져올때 최대값과 최솟값이 일치하는지 확인하면 됨.

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int K = Integer.parseInt(input[1]);

            arr = new int[N];
            for (int i = 1; i < arr.length; i++) {
                arr[i] = i;
            }

            maxTree = new int[N*4];
            minTree = new int[N*4];
            Arrays.fill(minTree, Integer.MAX_VALUE);


            makeTree(0, N-1, 1, Math::max, maxTree);
            makeTree(0, N-1, 1, Math::min, minTree);

            while (K-- > 0) {

                String[] data = br.readLine().split(" ");
                int Q = Integer.parseInt(data[0]);
                int A = Integer.parseInt(data[1]);
                int B = Integer.parseInt(data[2]);

                if (Q == 0) { //진일이가 교체
                    update(N, A, B);
                } else if (Q == 1) { //손님이 빌려감
                    sb.append(lent(N, A, B)).append("\n");
                }

            }
        }

        System.out.println(sb);

    }



    private static int makeTree(int start, int end, int node, BinaryOperator<Integer> func, int[] tree) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        return tree[node] = func.apply(
                makeTree(start, mid, node*2, func, tree),
                makeTree(mid+1, end, node*2+1, func, tree));

    }

    private static void update(int n, int a, int b) {
        // a 인덱스 값을 b로, b 인덱스 값을 a로
        update(0, n-1, 1, a, arr[b]);
        update(0, n-1, 1, b, arr[a]);

        // arr 값 변경
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

    private static void update(int start, int end, int node, int idx, int value) {
        if (end < idx || idx < start) return;

        if (start == end) {
            maxTree[node] = value;
            minTree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node*2, idx, value);
        update(mid+1, end, node*2+1, idx, value);

        maxTree[node] = Math.max(maxTree[node*2], maxTree[node*2+1]);
        minTree[node] = Math.min(minTree[node*2], minTree[node*2+1]);
    }


    private static String lent(int n, int a, int b) {
        if (findMin(0, n-1, 1, a, b) == a
                && findMax(0, n-1, 1, a, b) == b) {
            return "YES";
        }

        return "NO";
    }


    private static int findMin(int start, int end, int node, int left, int right) {
        if (end < left || right < start) return Integer.MAX_VALUE;

        if (start == end) return minTree[node];
        if (left <= start && end <= right) return minTree[node];

        int mid = (start + end) / 2;

        return Math.min(findMin(start, mid, node*2, left, right), findMin(mid+1, end, node*2+1, left, right));
    }

    private static int findMax(int start, int end, int node, int left, int right) {
        if (end < left || right < start) return 0;

        if (start == end) return maxTree[node];
        if (left <= start && end <= right) return maxTree[node];

        int mid = (start + end) / 2;

        return Math.max(findMax(start, mid, node*2, left, right), findMax(mid+1, end, node*2+1, left, right));
    }

}