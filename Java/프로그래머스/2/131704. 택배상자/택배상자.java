import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> sub = new Stack<>();
        
        int orderIndex = 0;
        // i = box index
        for (int i = 1; i <= order.length; i++) {
            sub.push(i);
            
            while (sub.size() > 0 && sub.peek() == order[orderIndex]) {
                sub.pop();
                answer++;
                orderIndex++;
            }
        }
        
        return answer;
    }
}