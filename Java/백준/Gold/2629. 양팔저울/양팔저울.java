import java.io.*;
import java.util.*;

class Main {
    static boolean[][] visit;
    static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int M = Integer.parseInt(br.readLine());
        int[] list = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // "해당 추를 추가했을 때" 겹치는 경우의 수 제거 -> 이차원배열
        visit = new boolean[N+1][40001];

        // 추 하나씩 추가하면서 비교 가능한 dp 계산
        dfs(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int value : list) {
            sb.append((visit[N][value] == true ? "Y " : "N "));
        }

        System.out.println(sb.toString().trim());

    }

    private static void dfs(int index, int curWeight) {
        if (visit[index][curWeight] == true) return; //이미 확인함

        visit[index][curWeight] = true;

        if (index >= weights.length) return;

        dfs(index+1, curWeight); // 0에서 출발
        dfs(index+1, curWeight + weights[index]);
        dfs(index+1, Math.abs(curWeight - weights[index]));
    }
}
