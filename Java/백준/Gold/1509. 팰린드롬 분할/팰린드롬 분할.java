import java.io.*;
import java.util.*;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 팰린드롬 문제. DP로 풀이
        // i~j 구간이 팰린드롬인지 확인한 후, 문자열을 하나씩 늘리며 팬린드롬의 최소값을 계산하면 됨.
        char[] str = br.readLine().toCharArray();

        int[] dp = new int[str.length];
        boolean[][] isPalindrome = new boolean[str.length][str.length];

        // i~j 구간 팰린드롬 체크
        for (int i = 0; i < str.length; i++) {
            for (int j = i; j < str.length; j++) {
                int l = i;
                int r = j;
                boolean pass = true;
                while (l <= r) {
                    if (str[l] != str[r]) {
                        pass = false;
                        break;
                    }
                    l++; r--;
                }

                if (pass) isPalindrome[i][j] = true;
            }
        }

        // dp 계산
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 1; //문자열 1개

        // 0 ~ i-1, i ~ j 구간을 비교, i~j구간이 팰린드롬일 경우, dp[j] = dp[i-1] (앞부분) + 1 (팰린드롬)
        // i를 0->j로 순차적으로 올리며 계산하면 모든 경우를 아우를수있음.
        // 다만 i==0일 경우, dp[j] = 1로 계산하면 된다.
        for (int j = 1; j < str.length; j++) {
            if (isPalindrome[0][j]) {
                dp[j] = 1;
                continue;
            }

            for (int i = 1; i <= j; i++) {
                if (isPalindrome[i][j])
                    dp[j] = Math.min(dp[i-1]+1, dp[j]);
            }
        }


        System.out.println(dp[str.length-1]);

    }

}