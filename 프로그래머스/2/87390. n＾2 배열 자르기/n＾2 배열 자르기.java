class Solution {
    public long[] solution(int n, long left, long right) {
        long[] answer = new long[(int)(right-left+1)];
        // x축 y축에서 큰 숫자가 해당 값
        // a/n = 몫, 나머지중 큰 숫자 = 값
        
        for (long i = left; i < right+1; i++) {
            long x = i/n;
            long y = i%n;
            if (x > y) {
                answer[(int)(i-left)] = x+1;
            } else {
                answer[(int)(i-left)] = y+1;
            }
            
        }
        
        
        
        return answer;
    }
}