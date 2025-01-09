import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사이클 여부 -> union find 방식에서, union되지 않으면 (루트노드가 같음) "사이클 발생"
        // (이미 연결되어 있는걸 연결하는 꼴임)
        // -> 이 때의 번호를 반환할 것
        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= m; i++) {

            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            if (union(x, y) == false) {
                System.out.println(i);
                return;
            }
        }


        System.out.println(0);

    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }

        return true;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

}
