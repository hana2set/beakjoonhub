import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 음수 간선 -> 벨만 포드 알고리즘
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        long[] dist = new long[N+1];
        List<Road>[] roads = new ArrayList[N+1]; // 각 도시에 연결된 도로

        long INF = 500*6000*10000L;

        for (int i = 1; i <= N; i++) {
            roads[i] = new ArrayList<>();
            dist[i] = INF;
        }

        dist[1] = 0; //시작점

        while (M-- > 0) {
            input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            int C = Integer.parseInt(input[2]);

            roads[A].add(new Road(B, C));
        }


        // N-1회 동안
        // 모든 간선을 돌며 dist를 초기화
        for (int i = 1; i < N; i++) { // N-1회

            boolean update = false; //변경 여부

            for (int j = 1; j <= N; j++) { // 모든 간선 루프
                for (Road road : roads[j]) {
                    if (dist[j] == INF) continue; // 도달 못함

                    if (dist[road.t] > dist[j] + road.w) {
                        dist[road.t] = dist[j] + road.w;
                        update = true;
                    }
                }
            }

            if (update == false) break;

        }


        // update가 아직 일어날 경우 사이클이 있다고 판단
        for (int j = 1; j <= N; j++) {
            for (Road road : roads[j]) {
                if (dist[j] == INF) continue; // 도달 못함

                if (dist[road.t] > dist[j] + road.w) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }


        for (int i = 2; i <= N; i++) {
            long value = dist[i];
            if (value == INF) value = -1;

            sb.append(value + "\n");
        }

        System.out.println(sb);
    }
}

class Road {
    int t; // 목표 도시
    int w; // 가중치
    public Road(int t, int w) {
        this.t = t;
        this.w = w;
    }

}