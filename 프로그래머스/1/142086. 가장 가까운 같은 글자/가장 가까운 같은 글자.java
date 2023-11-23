import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        Map<String, Integer> indexMap = new HashMap<>();
        String[] arr = s.split("");
        answer = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            if (indexMap.get(arr[i]) == null) {
                answer[i] = -1;
            } else {
                answer[i] = i - indexMap.get(arr[i]);
            }
            indexMap.put(arr[i], i);
        }
            
        return answer;
    }
}