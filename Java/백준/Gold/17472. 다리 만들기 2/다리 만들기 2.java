import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visit;

    static int[][] islands;

    static int[] dx = new int[] {1, -1, 0, 0};
    static int[] dy = new int[] {0, 0, 1, -1};

    static List<Bridge> bridgeList = new ArrayList<>();

    static int[] parent;
    static int[][] roads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 섬을 구분함 (DFS)
        // 2. 각 섬에서 다른 섬까지의 모든 최단거리 계산 (N,M 값이 크지않음으로 직접계산)
        // 3. 크루스칼 알고리즘으로 최소 신장 거리 계산

        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);

        map = new int[N][M];
        visit = new boolean[N][M];
        islands = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 1. 섬 구분
        int islandIndex = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && visit[i][j] == false) {
                    divideMap(i, j, islandIndex++);
                }

                visit[i][j] = true;
            }
        }

        // 2. 각 섬에서 다른 섬까지의 모든 최단거리 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    calculateMinBridge(i, j, islands[i][j]);
                }
            }
        }

        // 3. 최소 신장 거리 계산
        int result = 0;
        parent = new int[islandIndex];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        Collections.sort(bridgeList, Comparator.comparingInt(a -> a.range));
        for (int i = 0; i < bridgeList.size(); i++) {
            Bridge bridge = bridgeList.get(i);
            if (union(bridge.start, bridge.end) == true) {
                result += bridge.range;
            }

        }

        for (int i = 2; i < parent.length; i++) {
            if (find(i) != 1) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        System.out.println(result);


    }

    private static void divideMap(int x, int y, int index) {
        if (0 > x || x >= N || 0 > y || y >= M || visit[x][y] == true || map[x][y] == 0) return;

        islands[x][y] = index;
        visit[x][y] = true;

        for (int i = 0; i < dx.length; i++) {
            divideMap(x + dx[i], y + dy[i], index);
        }

    }

    private static void calculateMinBridge(int x, int y, int islandIndex) {

        visit = new boolean[N][M];
        
        // bfs로 계산
        for (int i = 0; i < dx.length; i++) {
            int tx = x+dx[i];
            int ty = y+dy[i];
            int count = 0;
            while (0 <= tx && tx < N && 0 <= ty && ty < M && islands[tx][ty] == 0 && islands[tx][ty] != islandIndex) {
                tx += dx[i];
                ty += dy[i];
                count++;
            }

            if (0 <= tx && tx < N && 0 <= ty && ty < M && islands[tx][ty] != islandIndex && count > 1) {
                bridgeList.add(new Bridge(islandIndex, islands[tx][ty], count));
            }
        }
    }

    // 서로소 집합 계산
    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x < y) parent[y] = x;
        if (y < x) parent[x] = y;

        return true;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
}


class Bridge{
    int start;
    int end;
    int range;

    public Bridge(int start, int end, int range) {
        this.start = start;
        this.end = end;
        this.range = range;
    }
}
