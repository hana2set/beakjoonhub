import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {

    static int[] parent;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int F = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();

            parent = new int[F*2];
            count = new int[F*2];

            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                count[i] = 1;
            }

            int index = 0;
            while (F-- > 0) {
                String[] input = br.readLine().split(" ");

                if (map.get(input[0]) == null) map.put(input[0], index++);
                if (map.get(input[1]) == null) map.put(input[1], index++);

                sb.append(union(map.get(input[0]), map.get(input[1]))).append("\n");

            }


        }

        System.out.println(sb);


    }

    private static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
            count[x] += count[y];
            count[y] = 1;
        }


        return count[x];

    }

    private static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }
}
