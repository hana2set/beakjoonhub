import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] roads = new int[n+1][n+1];
        int[][] before = new int[n+1][n+1]; //i->j에서 j 직전의 위치

        for (int i = 1; i < roads.length; i++) {
            for (int j = 1; j < roads[i].length; j++) {
                roads[i][j] = i==j ? 0 : Integer.MAX_VALUE;
            }
        }

        while (m-- > 0) {
            int[] bus = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            roads[bus[0]][bus[1]] = Math.min(roads[bus[0]][bus[1]], bus[2]);
            before[bus[0]][bus[1]] = bus[0];
        }

        int count = 0;
        while (count++ < n) {

            // 행렬곱
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {

                    for (int z = 1; z <= n; z++) {

                        if (roads[i][z] == Integer.MAX_VALUE || roads[z][j] == Integer.MAX_VALUE) continue;
                        if (roads[i][z] + roads[z][j] >= roads[i][j]) continue;

                        roads[i][j] = roads[i][z] + roads[z][j];
                        before[i][j] = before[z][j]; //직전 위치로 역추적하도록 구성
                    }
                }

            }

        }

        StringBuilder sb = new StringBuilder();
        // i, j 최소 비용
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(roads[i][j] == Integer.MAX_VALUE ? 0 : roads[i][j]).append(" ");
            }
            sb.setLength(sb.length()-1);
            sb.append("\n");
        }

        // i -> j 최소비용에 포함된 도시 갯수, 도시
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (i == j || roads[i][j] == Integer.MAX_VALUE) {
                    sb.append(0).append("\n");
                    continue;
                }

                // before로 경로 역추적
                Stack<Integer> s = new Stack<>();
                s.push(j);
                do {
                    s.push(before[i][s.peek()]);
                } while (s.peek() != i);

                sb.append(s.size()).append(" ");
                while (!s.isEmpty()) {
                    sb.append(s.pop()).append(" ");
                }
                sb.setLength(sb.length()-1);
                sb.append("\n");

            }
        }


        System.out.println(sb);
    }

}
