import java.io.*;

class Main {

    static int N;
    static int[] map;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // N개를 N*N에 둬야함으로 라인당 하나씩
        // DFS 방식으로
        // map[row] = col 방식으로 위치 표시 (변경할 수 있어야함)
        map = new int[N+1];

        dfs(1);

        System.out.println(count);
    }

    private static void dfs(int row) {
        if (row > N) {
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (isItPossibleCol(row, i)) {
                map[row] = i;
                dfs(row+1);
                map[row] = 0;
            }
        }
    }

    private static boolean isItPossibleCol(int row, int col) {
        for (int i = 1; i < row; i++) {
            if (map[i] == col) return false;
            if (map[i] + row-i == col || map[i] + i-row == col) return false; //대각선
        }
        return true;
    }

}