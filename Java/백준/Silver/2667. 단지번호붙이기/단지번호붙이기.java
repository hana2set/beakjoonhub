import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[][] building;
    static boolean[][] visit;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        building = new int[N][N];
        visit = new boolean[N][N];
        List<Integer> buildingSize = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            building[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }


        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] == true || building[i][j] == 0) continue;

                // 관계있는 건물 visit 제거
                buildingSize.add(dfs(i, j));
                count++;
            }
        }

        Collections.sort(buildingSize);

        System.out.println(count);
        for (int size: buildingSize) {
            System.out.println(size);
        }
    }

    private static int dfs(int x, int y) {
        if (visit[x][y] == true || building[x][y] == 0) return 0;

        int buildingCount = 1;
        visit[x][y] = true;

        for (int i = 0; i < dx.length; i++) {
            if (0 <= x+dx[i] && x+dx[i] < N && 0 <= y+dy[i] && y+dy[i] < N)
                buildingCount += dfs(x + dx[i], y+dy[i]);
        }

        return buildingCount;
    }

}