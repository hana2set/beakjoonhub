import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sums = new int[NM[0]+1];

        // 구간합 -> j까지의 합 - i까지의 합
        // 누적합을 구해두면 편함
        sums[0] = nums[0];
        for (int i = 1; i < NM[0]; i++) {
            sums[i] = sums[i-1] + nums[i];
        }

        while (NM[1]-- > 0) {
            int[] se = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 구간합 계산
            int sum = sums[se[1]-1];
            if (se[0] != 1) sum -= sums[se[0]-2];
            
            System.out.println(sum);

        }
    }

}
