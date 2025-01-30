import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] point = new int[4][2];

        // 기하학 알고리즘 CCW
        // 행렬의 외적을 통해 방향을 알 수 있음.
        for (int i = 0; i < 3; i++) {
            String[] data = br.readLine().split(" ");
            point[i][0] = Integer.parseInt(data[0]);
            point[i][1] = Integer.parseInt(data[1]);
        }

        point[3][0] = point[0][0];
        point[3][1] = point[0][1];

        int sum = 0;

        for (int i = 0; i < 3; i++) {
            sum += point[i][0] * point[i+1][1];
            sum -= point[i][1] * point[i+1][0];
        }

        if (sum == 0) System.out.println(0);
        if (sum > 0) System.out.println(1);
        if (sum < 0) System.out.println(-1);
    }
}