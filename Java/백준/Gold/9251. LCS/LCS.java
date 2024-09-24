import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] A = br.readLine().split("");
        String[] B = br.readLine().split("");

        // 수열의 순서가 지켜짐으로 DP로 해결 가능
        // 반복문에서 첫번째도 이전값을 참조할 수 있도록 인덱스 1부터 시작
        int[][] dp = new int[A.length+1][B.length+1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i-1].equals(B[j-1])) // 같은 경우, 공통 부분수열에 추가 -> (i-1, j-1)의 값 + 1
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }

        // 없애야 되는 전깃줄 개수 출력
        System.out.println(dp[A.length][B.length]);
    }

}
