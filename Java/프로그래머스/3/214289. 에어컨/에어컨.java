import java.util.*;

class Solution {
    
    
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = Integer.MAX_VALUE;
        int N = onboard.length;
        int[][] dp = new int[N+1][51];; //i 인덱스에 j온도를 맞추는 최소값
        
        // 온도 범위 -10 ~ 40 -> 0 ~ 50 (dp 인덱스 맞추기위해);
        temperature = temperature + 10;
        t1 = t1 + 10;
        t2 = t2 + 10;
        
        // dp 초기화
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][temperature] = 0;
        
        // 가동할 필요 없음
        if (temperature >= t1 && temperature <= t2) {
            return 0;
        }
        
        // dp 채우기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= 50; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) continue; // 도달 불가
                if (onboard[i] == 1 && (j < t1 || j > t2)) continue; //실내온도를 맞출 수 없음
                
                // 에어컨 가동
                if (j > 0) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j] + a); // 내리기
                if (j < 50) dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j] + a); // 올리기
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + b); // 유지
                
                // 에어컨 끔
                if (j == temperature) dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]); // 실외온도랑 일치 
                else if (j > temperature) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]); // 실외온도
                else if (j < temperature) dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]); // 실외온도
                
            }
        }
        
        for (int i = 0; i < dp[N-1].length; i++) {
            if ((i >= t1 && i <= t2) || onboard[N-1] == 0) answer = Math.min(dp[N-1][i], answer);
        }
        
        return answer;
    }
    
}