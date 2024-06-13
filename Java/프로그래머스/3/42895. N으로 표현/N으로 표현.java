import java.util.*;

class Solution {
    public int solution(int N, int number) {
        // DP문제
        // N에 따라 표현 가능한 수 구하면됨
        
        List<Set<Integer>> list = new ArrayList<>();
        
        
        for (int i = 0; i < 8; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < i; j++) {
                set.addAll(unionSet(list.get(j), list.get(i-j-1)));
            }
            
            set.add(N * Integer.parseInt("1".repeat(i+1)));
            
            if (set.contains(number)) {
                return i+1;
            }
            
            list.add(set);
        }
        
        // System.out.println(list);
        
        return -1;
    }
    
    private Set<Integer> unionSet(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> returnSet = new HashSet<>();
        
        for (int num1 : set1) {
            for (int num2 : set2) {
                returnSet.add(num1 + num2);
                returnSet.add(num1 - num2);
                returnSet.add(num1 / num2);
                returnSet.add(num1 * num2);
            }
        }
        
        returnSet.remove(0);
        
        return returnSet;
    }
    
}