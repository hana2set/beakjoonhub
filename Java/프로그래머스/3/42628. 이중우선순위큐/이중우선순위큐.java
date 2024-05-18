import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        Queue<Integer> maxQueue = new PriorityQueue(Comparator.reverseOrder());
        Queue<Integer> minQueue = new PriorityQueue();
        
        int length = 0;
        
        for (String oper : operations) {
            String[] operArr = oper.split(" ");
            if (operArr[0].equals("I")) {
                maxQueue.add(Integer.parseInt(operArr[1]));
                minQueue.add(Integer.parseInt(operArr[1]));
                length++;
            } else if (operArr[0].equals("D")) {
                if (length == 0) continue;
                
                if (operArr[1].equals("-1")) {
                    minQueue.poll();
                } else {
                    maxQueue.poll();
                }
                
                 length--;
                
                if (length == 0) {
                    minQueue.clear();
                    maxQueue.clear();
                }
            }
        }
        
        if (length == 0) return answer;
        
        answer[1] = minQueue.poll();
        answer[0] = maxQueue.poll();
        
        return answer;
    }
}