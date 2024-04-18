class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        double ratio = 1.0 * h / w ;
        
        
        for (long i = 1; i <= w; i++) {
            long right = i * 1.0 * h / w == (long) (i * 1.0 * h / w) ? (long)(i * 1.0 * h / w) : (long)(i * 1.0 * h / w) + 1;
            long left = (long) ((i-1) * 1.0 * h / w);
            
            answer += h - (right - left);
        }
        
        return answer;
    }
}