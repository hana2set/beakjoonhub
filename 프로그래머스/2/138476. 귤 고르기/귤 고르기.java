import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> catebox = new HashMap();
        
        for (int target : tangerine) {
            if (catebox.get(target) != null) {
                catebox.put(target, catebox.get(target)+1);
            } else {
                catebox.put(target, 1);
            }
        }
        
        List<Integer> cateCount = new ArrayList();
        for (int key : catebox.keySet()) {
            cateCount.add(catebox.get(key));
        }
        
        cateCount.sort(Comparator.reverseOrder());
        
        int totalCount = 0;
        for (int i : cateCount) {
            totalCount += i;
            answer++;
            if (totalCount >= k) {
                break;
            }
        }
        
        return answer;
    }
}