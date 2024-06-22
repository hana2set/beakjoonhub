class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        
        // 이분 탐색
        // 
        // 제한시간(time) 안에 
        // 1) a <= 최대 운반 가능한 금 (gm)
        // 2) b <= 최대 운반 가능한 은 (sm)
        // 3) a+b <= 최대 운반 가능한 금+은 (gsm)
        // 조건을 만족하는 최소값 T가 정답
        
        long answer = -1;
        long l = 0, r = (long)(10e9 * 2 * 10e5 * 2); //최대값
        
        while (l <= r) {
            long time = (l+r) / 2;
            long gm = 0, sm = 0, gsm = 0;
            
            for (int i = 0; i < g.length; i++) {
                // 왕복 횟수, 편도 횟수 포함, 0.5 -> 1회
                long cnt = Math.round((double)time / (t[i] * 2)); 
                
                gm += Math.min(g[i], w[i]*cnt);
                sm += Math.min(s[i], w[i]*cnt);
                gsm += Math.min(g[i] + s[i], w[i]*cnt);
                
            }
            
            if (a <= gm && b <= sm && a+b <= gsm) {
                answer = time;
                r = time - 1;
            } else {
                l = time + 1;
            }
        }
        
        return answer;
    }
}