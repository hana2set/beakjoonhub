import java.util.*;

class Solution {
    public long solution(long k, long d) {
        long answer = 0;
        
        for (long i = 0; i <= d; i = i+k) {
            answer += ((long) Math.sqrt(d*d - i*i) / k) + 1;
        }
        
        return answer;
    }
}