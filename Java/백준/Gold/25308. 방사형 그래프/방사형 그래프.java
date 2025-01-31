import java.io.*;
import java.util.*;

class Main {

    static int[] value;
    static int[] seq = new int[8];
    static boolean[] visit = new boolean[8];
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        value = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 기하학 알고리즘 CCW
        // 8개의 좌표에서 순서대로 세 점의 외적을 통해, 한쪽 방향이면 볼록, 하나라도 바뀐다면 오목.
        // 좌표가 8개임으로, 경우의수는 8!으로 제한되어있음으로 직접 세어봄.
        // 대각선의 좌표는 길이/루트(2) 임으로, 세점이 "같은 라인에 있음"을 배제하고 계산 (double로 인한 오차 무시)
        // 벡터의 뺄셈을 통해 기울기의 흐름으로 계산하려 했으나, 무한대 값에 의한 변경 트리거가 2개 있어야 함으로, 위 방식으로 계산

        backtracking(0, 0);

        System.out.println(count);
    }

    private static void backtracking(int index, int dir) {
        if (index == 8) {
            if (dir != getDir(7)
                || dir != getDir(8)
                || dir != getDir(9)) return;

            count++;
            return;
        }

        int curDir = getDir(index-1);

        if (index > 3 && dir != curDir) return;

        for (int i = 0; i < 8; i++) {
             if (visit[i] == true) continue;

            visit[i] = true;
            seq[index] = value[i];

            backtracking(index+1, curDir);

            seq[index] = 0;
            visit[i] = false;

        }

    }

    private static int getDir(int index) {
        if (index < 2) return 0;

        int a = index%8;
        int b = (index-1)%8;
        int c = (index-2)%8;

        double ccw = getCcw(a, b, c);

        if (ccw > 0) return 1;
        else return -1;
    }

    private static double getCcw(int a, int b, int c) {
        // CCW만 계산하면 됨으로, 좌표는 제1사분면으로 고정
        // a 1점 : p[i], 0
        // b 2점 : p[i-1]/root(2) , p[i-1]/root(2)
        // c 3점 : 0, p[i-2]
        double x1 = seq[a];
        double y1 = 0;
        double x2 = seq[b] / Math.sqrt(2);
        double y2 = seq[b] / Math.sqrt(2);
        double x3 = 0;
        double y3 = seq[c];

        return (x2-x1)*(y3-y2) - (x3-x2)*(y2-y1);

    }
}