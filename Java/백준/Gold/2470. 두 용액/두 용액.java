import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] seq = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        Arrays.sort(seq);

        int resultL = 0;
        int resultR = N-1;
        long minSum = Long.MAX_VALUE;

        int l = 0;
        int r = N-1;
        while (l < r) {
            long sum = seq[l] + seq[r];

            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);

                resultL = l;
                resultR = r;

                if (sum == 0) break;
            }

            if (sum < 0) l++;
            else r--;

        }

        System.out.println(seq[resultL] + " " + seq[resultR]);
    }
}

