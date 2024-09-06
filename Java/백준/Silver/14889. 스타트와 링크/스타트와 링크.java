import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

class Main {

    static int N;
    static int[][] synergy;
    static boolean[] visit;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        synergy = new int[N][N];
        visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            synergy[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0, 0);

        System.out.println(min);
    }

    public static void dfs(int index, int count) {
        if (min == 0 || index == N) return;

        if (count == N/2) {
            min = Math.min(min, calcMin());
            return;
        }

        visit[index] = true;
        dfs(index+1, count+1);
        visit[index] = false;
        dfs(index+1, count);
    }

    private static int calcMin() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (visit[i] == true && visit[j] == true) {
                    total += synergy[i][j];
                    total += synergy[j][i];
                }

                if (visit[i] == false && visit[j] == false) {
                    total -= synergy[i][j];
                    total -= synergy[j][i];
                }
            }
        }

        return Math.abs(total);
    }

}
