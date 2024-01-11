import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        int diffCount = 0;
        
        Set<Integer> set = new HashSet();
        Map<Integer, Integer> map = new HashMap();
        for (int num : topping) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        // for (int i = 0; i < topping.length; i++) {
        
        for (int num : topping) {
            set.add(num);
            if (map.get(num) == 1) {
                map.remove(num);
            } else {
                map.put(num, map.get(num)-1);
            }
            
            if (set.size() == map.size()) {
                answer++;
            }
            
        }
        
        
        return answer;
    }
}