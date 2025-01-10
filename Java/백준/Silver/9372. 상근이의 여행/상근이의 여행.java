import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {

            // 최소 신장 트리의 성질 이용
            // n개가 연결되어있는 최소 간선의 갯수를 구하면 됨
            // 모든 국가가 연결되어 있는 간선의 최소 갯수는 N-1개이다.
            // 즉, M과 관계없이 답도 N-1
            String[] data = br.readLine().split(" ");
            int N = Integer.parseInt(data[0]);
            int M = Integer.parseInt(data[1]);

            while (M-- > 0) {
                br.readLine();
            }

            sb.append(N-1).append("\n");


        }

        System.out.println(sb);

    }
}
