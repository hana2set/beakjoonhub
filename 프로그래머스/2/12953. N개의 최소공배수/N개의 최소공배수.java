import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int max = 0;
        for (int num : arr) {
            max = Math.max(num, max);
        }
        
        for (int i = max; ;i = i + max) {
            boolean isCommon = true;
            for (int num : arr) {
                if (!(i%num == 0)) {
                    isCommon = false;
                    break;
                }
            }
            if (isCommon == true) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}