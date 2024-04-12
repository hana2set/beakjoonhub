import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] diffArr = new int[N-1];

        int beforeVal = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N-1; i++) {
            int afterVal = Integer.parseInt(st.nextToken());
            diffArr[i] = afterVal - beforeVal;
            beforeVal = afterVal;
        }

        Arrays.sort(diffArr);

        int sum = 0;
        for (int i = 0; i < diffArr.length - K + 1; i++) {
            sum += diffArr[i];
        }

        System.out.println(sum);

    }
}