import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 자기자신 최단거리 ->
        // 모든 간선 계산 -> 플로이드-워셜 알고리즘
        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        int[][] roads = new int[V+1][V+1];
        int INF = 400 * 10000 + 1;

        
        for (int i = 1; i <= V; i++) {
            Arrays.fill(roads[i], INF);
        }

        for (int i = 0; i < E; i++) {
            String[] roadData = br.readLine().split(" ");
            int a = Integer.parseInt(roadData[0]);
            int b = Integer.parseInt(roadData[1]);
            int c = Integer.parseInt(roadData[2]);
            
            roads[a][b] = c;
        }

        // k를 거쳐가는 roads 초기화
        for (int k = 1; k <= V; k++) {

            // (k를 거쳐) i에서 j로 가는 roads
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    roads[i][j] = Math.min(roads[i][j], roads[i][k] + roads[k][j]);
                }
            }
            
        }

        int minValue = INF;
        for (int i = 1; i <= V; i++) {
            if (roads[i][i] != 0) minValue = Math.min(minValue, roads[i][i]);
        }

        System.out.println(minValue == INF ? -1 : minValue);
    }
}
