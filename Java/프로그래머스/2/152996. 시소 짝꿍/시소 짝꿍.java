import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Arrays.sort(weights);
        
        int left = 0;
        int right = 1;
        
        int pValue = 0; // left값에 대한 갯수
        
        while (left < weights.length) {
            // System.out.println(left);
            // System.out.println(right);
            // System.out.println("pValue:"+pValue);
            
            if (right >= weights.length
                    || weights[left] * 2 < weights[right] ) {
                answer += pValue;
                left++;
                while (left < right
                       && weights[left] == weights[left-1]) {
                    answer += --pValue;
                    left++;
                }
                right = left+1;
                pValue = 0;
                
                continue;
            }
            
            // 1배, 1.33배, 1.5배. 2배
            if (weights[left] == weights[right]
                || weights[left] * 4 == weights[right] * 3
                || weights[left] * 3 == weights[right] * 2
                || weights[left] * 2 == weights[right]) {
            // System.out.println("weights[left]:"+weights[left]);
            // System.out.println("weights[right]:"+weights[right]);
                pValue++;
            }
            
            
            right++;
            
        }
        
        return answer;
    }
}