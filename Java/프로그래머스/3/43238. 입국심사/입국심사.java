import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        
        long left = 0;
        long right = times[times.length - 1] * (long)n; // 최대값

        while (true) {
                
            if (left >= right) {
                answer = right;
                break;
            }
            
            long mid = (left + right) / 2;
                        
            long total = 0;
            for (int time : times) {
                total += mid / time;
            }            
            
            if (total < n) { 
                // 시간부족
                left = mid + 1;
            } else {
                // 최소값 구해야함
                right = mid;
            }
            
        }
            
        
        return answer;
    }
}