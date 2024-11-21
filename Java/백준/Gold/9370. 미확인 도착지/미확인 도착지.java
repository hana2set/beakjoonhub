import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트케이스

        // 가중치 있는 도로의 최단거리 -> 다익스트라
        // 다익스트라로 탐색 후 목적지가 최단경로로 통과가 가능한 경우에서,
        // g,h를 포함한 경우가 있는 경우의 갯수를 구함
        // -> 이 방법은 "같은 최단 경로"인 경우, 간선을 통과할 여지가 있는 지 파악하기위해 큐에서 제외하는 것이 불가. (비효율적)
        // -> 따라서 "g,h를 포함한 간선의 가중치를 낮추는 방법"으로 계산하면 효율적으로 계산 가능 (메모리, 시간 초과)
        // -> 따라서 모든 (간선 가중치 * 2) 와 (g,h 간선 가중치*2 - 1)을 적용하여 계산하면 효율적이게 됨
        // -> 이를위해, Integer.MAX_VALUE가 아닌 100_000_000으로 초기화함
        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]); // 교차로
            int m = Integer.parseInt(input[1]); // 도로
            int t = Integer.parseInt(input[2]); // 목적지 후보 갯수

            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]); // 시작점
            int g = Integer.parseInt(input[1]); // 지나간 교차로
            int h = Integer.parseInt(input[2]); // 지나간 교차로

           List<Road>[] roads = new ArrayList[n+1]; // 각 교차로에 연결된 도로
//            int[][] roads = new int[n+1][n+1];
            int[] pointDist = new int[n+1]; // 방문 최단 거리
            Set<Integer> target = new HashSet<>(); // 목적지 후보군
            Set<Integer> result = new HashSet<>();

            for (int i = 0; i <= n; i++) {
               roads[i] = new ArrayList<>(); // 각 교차로에 연결된 도로
//               pointDist[i] = Integer.MAX_VALUE;
                pointDist[i] = 100_000_000;
//                Arrays.fill(roads[i], 1_000_000_000);
            }

            for (int i = 0; i < m; i++) {
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]); // 교차로
                int b = Integer.parseInt(input[1]); // 교차로
                int c = Integer.parseInt(input[2])*2; // 가중치

                // 해당 간선 가중치 낮추기
                if (a==g && b==h) c--;
                if (a==h && b==g) c--;

               roads[a].add(new Road(b, c));
               roads[b].add(new Road(a, c));
//                roads[a][b] = roads[b][a] = c;
            }

            for (int i = 0; i < t; i++) {
                target.add(Integer.parseInt(br.readLine()));
            }

            ///// 다익스트라
            PriorityQueue<Data> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));
            q.add(new Data(s, s, 0, false));
            pointDist[s] = 0;

            while (!q.isEmpty()) {
                Data data = q.poll();
                int start = data.e;
                int dist = data.dist;

                if (pointDist[start] < dist) continue; // 최단경로 아님

                if (start == g && data.before == h
                        || start == h && data.before == g)
                    data.passGH = true;

                if (target.contains(start)) { //목적지 도달
                    // 두점을 통과했고, 이미 포함된 결과가 아니면
                    if (data.passGH && !result.contains(start)) result.add(start);
                }

                for (Road goal : roads[start]) {

                    if (pointDist[goal.e] <= dist + goal.w) continue; // 최단경로 아님
                    pointDist[goal.e] = dist + goal.w;

                    q.add(new Data(goal.e, start, dist + goal.w, data.passGH));
                }
//                for (int i = 1; i <= n; i++) {
//                    if (pointDist[i] < dist + roads[start][i]) continue;
//                    pointDist[i] = dist + roads[start][i];
//
//                    q.add(new Data(i, start, dist + roads[start][i], data.passGH));
//                }

            }

            sb.append( result.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")) + "\n" );
        }

        System.out.println(sb);
    }
}

class Road {
    int e; // 교차로
    int w; // 가중치
    public Road(int e, int w) {
        this.e = e;
        this.w = w;
    }

}

class Data {
    int e; // 교차로
    int before; //기존값
    int dist; // 지나온 거리
    boolean passGH; //s 통과 여부

    public Data(int e, int before, int dist, boolean passGH) {
        this.e = e;
        this.before = before;
        this.dist = dist;
        this.passGH = passGH;
    }

}


