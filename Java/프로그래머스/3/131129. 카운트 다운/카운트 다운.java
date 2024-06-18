class Solution {
    
    int[][] dp;
    
    public int[] solution(int target) {
        
        dp = new int[target+1][2];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        
        // dp[0] -> [0,0] 부터 순서대로 dp값 갱신 -> target까지
        for (int n = 0; n < target; n++) {
            
            // 싱글
            for (int i = 1; i < 21; i++) {
                if (n + i > target) break;
                
                if (dp[n][0]+1 < dp[n+i][0] // 총 횟수가 적거나
                    || (dp[n][0]+1 == dp[n+i][0] && dp[n][1]+1 > dp[n+i][1])) { // 합계가 많은경우
                    dp[n+i][0] = dp[n][0]+1;
                    dp[n+i][1] = dp[n][1]+1;
                }
            }
            
            // 더블
            for (int i = 2; i < 41; i += 2) {
                if (n + i > target) break;
                if (dp[n][0]+1 < dp[n+i][0]
                    || (dp[n][0]+1 == dp[n+i][0] && dp[n][1]+1 > dp[n+i][1])) {
                    dp[n+i][0] = dp[n][0]+1;
                    dp[n+i][1] = dp[n][1];
                }
            }
            
            // 트리플
            for (int i = 3; i < 61; i += 3) {
                if (n + i > target) break;
                if (dp[n][0]+1 < dp[n+i][0]
                    || (dp[n][0]+1 == dp[n+i][0] && dp[n][1]+1 > dp[n+i][1])) {
                    dp[n+i][0] = dp[n][0]+1;
                    dp[n+i][1] = dp[n][1];
                }
            }
            
            // 불
            int i = 50;
            if (n + i > target) continue;
            
            if (dp[n][0]+1 < dp[n+i][0]
                || (dp[n][0]+1 == dp[n+i][0] && dp[n][1]+1 > dp[n+i][1])) {
                dp[n+i][0] = dp[n][0]+1;
                dp[n+i][1] = dp[n][1]+1;
            }
        }
        
        
        return dp[target];
    }
    
}