import java.util.*;

class Solution {
    
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        Map<Integer, Set<Integer>> winMap = new HashMap<>();
        Map<Integer, Set<Integer>> loseMap = new HashMap<>();
        
        for (int i = 0; i < results.length; i++) {
            if (winMap.get(results[i][0]) == null) winMap.put(results[i][0], new HashSet<>());
            if (loseMap.get(results[i][1]) == null) loseMap.put(results[i][1], new HashSet<>());
            
            winMap.get(results[i][0]).add(results[i][1]);
            loseMap.get(results[i][1]).add(results[i][0]);
        }
        
        for (int i = 0; i < n; i++) {
            Set<Integer> relSet = new HashSet<>();
            Set<Integer> winSet = new HashSet<>();
            Set<Integer> loseSet = new HashSet<>();
            
            addRelSet(winSet, winMap, i+1);
            addRelSet(loseSet, loseMap, i+1);
            
            relSet.addAll(winSet);
            relSet.addAll(loseSet);
            
            relSet.remove(i+1);
            
            if (relSet.size() >= n-1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private void addRelSet(Set<Integer> relSet, Map<Integer, Set<Integer>> searchMap, int person) {
        
        if (relSet.contains(person)) {
            return;
        }
        
        relSet.add(person);
        
        if (searchMap.get(person) != null) {
            for (Integer target : searchMap.get(person)) {
                addRelSet(relSet, searchMap, target);
            }
        }
    }
}