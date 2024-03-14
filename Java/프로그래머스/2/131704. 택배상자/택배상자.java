import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> sub = new Stack<>();
        
        int boxNum = 1;
        for (int i = 0; i < order.length; i++) {
            
            while (sub.size() > 0 && sub.peek() == order[i]) {
                sub.pop();
                answer++;
                i++;
                if (i >= order.length) {
                    break;
                }
            }
            
            boolean isEnd = true;
            for (; boxNum <= order.length; boxNum++) {
                if (order[i] == boxNum) {
                    answer++;
                    boxNum++;
                    isEnd = false;
                    break;
                } else {
                    sub.push(boxNum);
                }
            }
            
            if (isEnd) {
                break;
            }

        }
        
        return answer;
    }
}