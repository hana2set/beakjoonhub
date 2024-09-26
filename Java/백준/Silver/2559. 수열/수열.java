import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] NK = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sums = new int[NK[0]];

        sums[0] = temp[0];
        for (int i = 1; i < NK[0]; i++) {
            sums[i] = sums[i-1] + temp[i];
        }

        // 연속적인 K일의 온도의 합
        int[] kSums = new int[NK[0]-NK[1]+1];

        kSums[0] = sums[NK[1]-1];
        for (int i = 1; i <= NK[0]-NK[1]; i++) {
            kSums[i] = sums[i+NK[1]-1] - sums[i-1];
        }

        Arrays.sort(kSums);

        System.out.println(kSums[kSums.length-1]);
    }

}
