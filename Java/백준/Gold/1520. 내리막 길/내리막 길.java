import java.io.*;
import java.util.*;

class Main {
    static int M, N;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};
    static int[][] dp;
    static int[][] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        // dfs -> 최대 (4 방향)^500*500 탐색 -> 시간초과
        // 특정 경로까지 갈수있는 경우의 수가 동일함으로, dp를 통해 누적시키는 방식이 적절
        // 그러나, 각 지점에서 끝지점에 도달한 경우만 합해야 함으로
        // dfs를 통해 도착정보를 반환 받아 한 지점에서 합해줘야함.
        // ex) 2  1  1  1
        //     1 -1 -1  1
        //     1  1  1  0
        height = new int[M][N]; // (y,x)의 높이
        dp = new int[M][N]; // (y,x)에서 목적지(M,N)까지 도달하는 경우의 수

        for (int i = 0; i < M; i++) {
            height[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], -1); //도달하지 않음 -> -1, 방문 -> 0
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int y, int x) { //y,x 지점에서 M,N 지점까지 가는 경우의 수
        if (y == M-1 && x == N-1) return 1; //도착

        if (dp[y][x] != -1) return dp[y][x]; // 이미 방문하여 계산됨

        dp[y][x] = 0; //방문 여부 변경

        for (int i = 0; i < dx.length; i++) {
            int tarY = y+dy[i];
            int tarX = x+dx[i];

            if (tarY < 0 || tarY >= M) continue;
            if (tarX < 0 || tarX >= N) continue;
            if (height[y][x] <= height[tarY][tarX]) continue;

            // 각 경로별 dfs값 합쳐서 dp계산
            dp[y][x] += dfs(tarY, tarX);
        }

        return dp[y][x]; //
    }
}
