import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[] answer = {};
        
        List<List<Integer>> frmd = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            frmd.add(new ArrayList<>());
        }
        
        int k = n;
        int count = 1;
        int arrIndex = 0;
        
        while (k > 0) {
            int start = count;
            if (n%3 == k%3) {
                for (int i = start; i < start+k; i++) {
                    frmd.get(arrIndex++).add(n/3 - k/3, count++);
                }
                
                arrIndex--;
            }
            
            if (n%3 == (k+1)%3) {
                for (int i = start; i < start+k; i++) {
                    frmd.get(arrIndex).add(n/3 - (k+1)/3 + 1 + i - start, count++);
                }
            }
            
            if (n%3 == (k+2)%3) {
                for (int i = start; i < start+k; i++) {
                    frmd.get(--arrIndex).add(frmd.get(arrIndex).size() - (n/3 - (k+2)/3),count++);
                }
                
                arrIndex++;
            }
            
            k--;
        }
        
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            resultList.addAll(frmd.get(i));
        }
        
        
        return resultList.stream().mapToInt(i -> i).toArray();
    }
}