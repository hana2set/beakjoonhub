import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

class Main {

    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 직선임으로 그리디알고리즘 적용
        // 정렬 후 연결 가능한 선 이어서 계산하여 합산

        int N = Integer.parseInt(br.readLine());
        int[][] lines = new int[N][2];


        for (int i = 0; i < N; i++) {
            String[] data = br.readLine().split(" ");
            int x = Integer.parseInt(data[0]);
            int y = Integer.parseInt(data[1]);

            lines[i] = new int[]{x, y};
        }

//        Arrays.sort(lines, Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]));


        //x좌표 오름차순 정렬. x좌표 같으면 y좌표 오름차순 정렬
        Arrays.sort(lines, (o1, o2) -> {
            if(o1[0] == o2[0])  return o1[1] - o2[1];
            else  return o1[0] - o2[0];
        });

        int max = lines[0][1];
        int sum = max-lines[0][0];

        for (int i = 1; i < N; i++) {
            if (lines[i][1] < max) continue;
            else if (lines[i][0] < max) sum += lines[i][1]-max;
            else sum += lines[i][1]-lines[i][0];

            max = lines[i][1];

        }

        System.out.println(sum);

    }

}