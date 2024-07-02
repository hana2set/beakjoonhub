class Solution {
    public int solution(int n) {
        int answer = 0;
        int MOD = 1_000_000_007;
        long[] dp = new long[n + 1];
        long[] dp4 = new long[n + 1];
        long[] dp5 = new long[n + 1];
        long[] dp6 = new long[n + 1];
        // ㄱ자는 2개 세트, 1은 하나 세트
        // 3에서 가로길이만 늘어남 -> 전행의 비슷한 구조가 반복 (이전행들의 조합으로 결과가 나타남)
        // 4부터 3의 배수마다 새로 추가되는 타일링의 패턴이 있음. (갯수 2개, 2개, 4개)
        // 이를통해 점화식 유도할 것
        
        dp[0] = 1;
        
        
//         for (int i = 7; i <= n; i++) {
//             long temp = (dp[i-1] % mod + dp[i-2]*2 % mod + dp[i-3]*6 % mod + dp[i-4] % mod - dp[i-6] % mod) % mod;
//             // dp[i] = (int)(temp % mod);
//             dp[i] = (int) temp;
//             // dp[i] = dp[i-1] % mod;
//             // dp[i] += dp[i-2]*2 % mod;
//             // dp[i] = dp[i] % mod;
//             // dp[i] += dp[i-3]*6 % mod;
//             // dp[i] = dp[i] % mod;
//             // dp[i] += dp[i-4] % mod;
//             // dp[i] = dp[i] % mod;
//             // dp[i] -= dp[i-6] % mod;
//             // dp[i] = dp[i] % mod;
//             // dp[i] = (int) ((0L + dp[i-1] + dp[i-2]*2 + dp[i-3]*6 + dp[i-4] - dp[i-6]) % mod);
                
//         }
        
        for (int i = 1; i <= n; i++) {
            if (i - 1 >= 0) dp[i] = (dp[i] + dp[i - 1]) % MOD;
            if (i - 2 >= 0) dp[i] = (dp[i] + dp[i - 2] * 2) % MOD;
            if (i - 3 >= 0) dp[i] = (dp[i] + dp[i - 3] * 5) % MOD;
            if (i - 4 >= 0) {
                long diff = (dp[i - 4] * 2) % MOD;
                dp4[i] = (dp4[i-3] + diff) % MOD;
                dp[i] = (dp[i] + dp4[i]) % MOD;
            }

            if (i - 5 >= 0) {
                long diff = (dp[i - 5] * 2) % MOD;
                dp5[i] = (dp5[i-3] + diff) % MOD;
                dp[i] = (dp[i] + dp5[i]) % MOD;
            }

            if (i - 6 >= 0) {
                long diff = (dp[i - 6] * 4) % MOD;
                dp6[i] = (dp6[i-3] + diff) % MOD;
                dp[i] = (dp[i] + dp6[i]) % MOD;
            }
        }
        
        return (int)dp[n];
    }
}