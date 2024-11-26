import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 모든 간선 -> 플로이드-워셜 알고리즘
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] roads = new int[N+1][N+1];
        int INF = 100_000 * 99 + 1;

        
        for (int i = 1; i <= N; i++) {
            Arrays.fill(roads[i], INF);
            roads[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            String[] roadData = br.readLine().split(" ");
            int a = Integer.parseInt(roadData[0]);
            int b = Integer.parseInt(roadData[1]);
            int c = Integer.parseInt(roadData[2]);
            
            roads[a][b] = Math.min(c, roads[a][b]);
        }

        // k를 거쳐가는 roads 초기화
        for (int k = 1; k <= N; k++) {

            // (k를 거쳐) i에서 j로 가는 roads
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    roads[i][j] = Math.min(roads[i][j], roads[i][k] + roads[k][j]);
                }
            }
            
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append((roads[i][j] == INF ? 0 : roads[i][j]) + " ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
