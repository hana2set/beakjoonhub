import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] roads = new int[n+1][n+1];
        StringBuilder[][] way = new StringBuilder[n+1][n+1];

        for (int i = 1; i < roads.length; i++) {
            for (int j = 1; j < roads[i].length; j++) {
                roads[i][j] = i==j ? 0 : Integer.MAX_VALUE;
                way[i][j] = new StringBuilder();
            }
        }

        while (m-- > 0) {
            int[] bus = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (roads[bus[0]][bus[1]] > bus[2]) {
                roads[bus[0]][bus[1]] = bus[2];
            }
        }

        int count = 0;
        while (count < n) {
            count++;

            // 행렬곱
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {

                    for (int z = 1; z <= n; z++) {

                        if (roads[i][z] == Integer.MAX_VALUE || roads[z][j] == Integer.MAX_VALUE) continue;
                        if (roads[i][z] + roads[z][j] >= roads[i][j]) continue;

                        roads[i][j] = roads[i][z] + roads[z][j];
                        way[i][j].setLength(0);
                        way[i][j].append((way[i][z] + " " + z + " " + way[z][j]).trim());
                    }
                }

            }

        }

        StringBuilder sb = new StringBuilder();
        // i, j 최소 비용
        for (int i = 1; i < roads.length; i++) {
            for (int j = 1; j < roads[i].length; j++) {
                sb.append(roads[i][j] == Integer.MAX_VALUE ? 0 : roads[i][j]).append(" ");
            }
            sb.setLength(sb.length()-1);
            sb.append("\n");
        }

        // i -> j 최소비용에 포함된 도시 갯수, 도시
        for (int i = 1; i < way.length; i++) {
            for (int j = 1; j < way[i].length; j++) {

                if (i == j || roads[i][j] == Integer.MAX_VALUE) {
                    sb.append(0).append("\n");
                    continue;
                }


                String[] city = way[i][j].toString().trim().split(" ");
                if (city[0].equals("")) city = new String[0];
                sb.append(city.length + 2);
                sb.append(" ").append(i).append(" ");
                for (int k = 0; k < city.length; k++) {
                    sb.append(city[k]).append(" ");
                }
                sb.append(j);
                sb.append("\n");

            }
        }


        System.out.println(sb);
    }

}
