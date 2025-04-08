import java.io.*;

class Main {

    static long[] tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 세그먼트 트리 응용
        // 직접 수정할 경우 시간복잡도 초과 (O(N))
        // 특정 구간을 통째로 수정함으로 세그먼트 트리로 시간복잡도를 줄일 수 있으나( O(logN) )
        // 최악의 경우 시간초과 (NM + K(logN) > 10억)
        // 따라서 펜윅 트리나 lazy propagation로 풀이.

        // 변형하여 (인접한 값들의 차)로 펜윅 트리를 구성하여 풀이.
        // 인접한 값들의 차이로 트리를 구성했음으로, i-j 구간 변경 시, 배열 (i), (j+1) 값만 변경하면 됨
        // 구간합[1,x] -> 배열 [x] 의 값
        N = Integer.parseInt(br.readLine());
        tree = new long[N+1];
        String[] data = br.readLine().split(" ");

        int prev = 0;
        for (int i = 1; i <= N; i++) {
            int curv = Integer.parseInt(data[i-1]);
            update(i, curv - prev);
            prev = curv;
        }


        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            String[] input = br.readLine().split(" ");
            int q = Integer.parseInt(input[0]);

            if (q == 1) {
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                int k = Integer.parseInt(input[3]);

                update(a, k);
                update(b+1, -k);

            } else if (q == 2) {
                int x = Integer.parseInt(input[1]);
                sb.append(getPrefixSum(x)).append("\n");
            }
        }

        System.out.println(sb);

    }

    private static long getPrefixSum(int x) {
        long sum = 0;
        while (x > 0) {
            sum += tree[x];
            x -= (x & -x);
        }

        return sum;
    }

    private static void update(int x, int value) {
        while (x <= N) {
            tree[x] += value;
            x += (x & -x);
        }
    }


}