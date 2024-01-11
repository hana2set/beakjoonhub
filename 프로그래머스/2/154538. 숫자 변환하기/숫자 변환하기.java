import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        Set<Integer> set = new HashSet();
        set.add(x);
        
        while(true) {
            if (set.size() == 0) {
                return -1;
            }
            
            for (Integer value : set) {
                if (value == y) {
                    return answer;
                }
            }
            
            answer++;
            
            List<Integer> a = new ArrayList<>(set);
            
            for (Integer value : a) {
                if (value+n <= y) set.add(value+n);
                if (value*2 <= y) set.add(value*2);
                if (value*3 <= y) set.add(value*3);
                set.remove(value);
            }
        }
    }
}