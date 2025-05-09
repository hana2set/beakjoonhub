class Solution {
    public int solution(int n) {
        int answer = 0;
        int mod = 1_000_000_007;
        long[] dp = new long[100001];
        // ㄱ자는 2개 세트, 1은 하나 세트
        // 3에서 가로길이만 늘어남 -> 전행의 비슷한 구조가 반복 (이전행들의 조합으로 결과가 나타남)
        // 4부터 3의 배수마다 새로 추가되는 타일링의 패턴이 있음. (갯수 2개, 2개, 4개)
        // 이를통해 점화식 유도할 것
        
        //dp[i] = dp[i-1] + dp[i-2] * 2 + dp[i-3] * 5 + dp[i-4] * 2 + dp[i-5] * 2 + dp[i-6] * 4 ... 계수 2,2,4 반복
        //dp[i-3] = dp[i-4] + dp[i-5] * 2 + dp[i-6] * 5 + dp[i-7] * 2 + dp[i-8] * 2 + dp[i-9] * 4 ...
        //dp[i] - dp[i-3] = dp[i-1] + dp[i-2] * 2 + dp[i-3] * 5 +dp[i-4] - dp[i-6]
        //dp[i] = dp[i-1] + dp[i-2] * 2 + dp[i-3] * 6 + dp[i-4] - dp[i-6]
        
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;
        
        for (int i = 7; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]*2 + dp[i-3]*6 + dp[i-4] - dp[i-6] + mod) % mod;
        }
        
        return (int)dp[n];
    }
}