import java.io.*;
import java.util.*;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 2차원 DP로 최대길이 계산 (LCS)
        // i==j일 경우, (i-1, j-1) + 1
        // i!=j일 경우, (i-1, j), (i, j-1) 의 최대값 출력
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int[][] dp = new int[str1.length+1][str2.length+1];

        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i-1] == str2[j-1]) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        // DP 인덱스로 최대길이 문자열 계산
        // DP가 대각선으로 증가할때의 값을 찾으면 공통 부분 문자열 찾을 수 있음.
        // 예를들어, (ABC), (EFC) 라면, 2차원 DP에서 (AB), (EF)문자열에 각각 C가 추가될 때
        // 대각으로 이동하며 LCS+1이 되기 때문.
        // 따라서, 마지막 DP에서부터 값이 같은 좌측상단 값들을 모두 구하면 된다.
        int x=str1.length, y=str2.length;
        Stack<Character> s = new Stack<>();
        while(x > 0 && y > 0 && dp[x][y] != 0) {
            while (dp[x][y] == dp[x][y-1]) y--;
            while (dp[x][y] == dp[x-1][y]) x--;

            s.push(str1[x-1]);
            x--;
        }

        while (!s.isEmpty()) {
            sb.append(s.pop());
        }

        System.out.println(dp[str1.length][str2.length]);
        System.out.println(sb);
    }


}