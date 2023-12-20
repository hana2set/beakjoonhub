import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        
        int max = 0;
        for (int i = 0; i < citations.length; i++) {
            int index = citations.length-i-1;
            max = citations[index];
            
            if (i+1 >= max) {
                answer = Math.max(i, max);
                break;
            }
            
            //문제가 이상해요
            if (index == 0 && max > citations.length) {
                answer = citations.length;
            }
        }
        
        
        return answer;
    }
}