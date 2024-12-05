import java.io.*;
import java.util.*;

class Main {

    static Info[] dp;
    static List<Integer> result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1부터 시작해서 dp 채우기
        int N = Integer.parseInt(br.readLine());
        dp = new Info[N+1];
        dp[1] = new Info(0, new ArrayList<>());
        dp[1].seq.add(1);

        for (int i = 2; i <= N; i++) {
            int before = 0;
            int minCount = Integer.MAX_VALUE;
            List<Integer> seq = new ArrayList<>();

            if (i%3 == 0) {
                if (dp[i/3].count < minCount) {
                    before = i/3;
                    minCount = dp[i/3].count;
                }
            }

            if (i%2 == 0) {
                if (dp[i/2].count < minCount) {
                    before = i/2;
                    minCount = dp[i/2].count;
                }
            }

            if (dp[i-1].count < minCount) {
                before = i-1;
                minCount = dp[i-1].count;
            }

            seq.addAll(dp[before].seq);
            seq.add(i);
            dp[i] = new Info(dp[before].count+1, seq);
        }
        

        sb.append(dp[N].count + "\n");
        Collections.sort(dp[N].seq, Comparator.reverseOrder());
        for (int val : dp[N].seq) {
            sb.append(val + " ");
        }

        System.out.println(sb.toString().trim());
    }


}

class Info {
    int count;
    List<Integer> seq;

    public Info(int count, List<Integer> seq) {
        this.count = count;
        this.seq = seq;
    }
}
