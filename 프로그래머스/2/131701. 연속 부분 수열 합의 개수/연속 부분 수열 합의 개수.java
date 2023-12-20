import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        
        // i 인덱스에서 j개의 합을 구함
        for (int i = 0; i < elements.length; i++) {
            for (int j = 1; j <= elements.length; j++) {
                int sum = 0;
                for (int k = i; k < i+j; k++) {
                    int index = k % elements.length;
                    sum += elements[index];
                }
                set.add(sum);
            }
        }
        
        answer = set.size();
        
        return answer;
    }
}