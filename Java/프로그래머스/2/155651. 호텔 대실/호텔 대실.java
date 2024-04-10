import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        PriorityQueue<Integer> startQ = new PriorityQueue<>();
        PriorityQueue<Integer> endQ = new PriorityQueue<>();
        
        for (String[] time : book_time) {
            String[] timeString1 = time[0].split(":");
            startQ.add(Integer.parseInt(timeString1[0])*60 + Integer.parseInt(timeString1[1]));
            
            String[] timeString2 = time[1].split(":");
            endQ.add(Integer.parseInt(timeString2[0])*60 + Integer.parseInt(timeString2[1]) + 10);
        }
        
        int count = 0;
        int max = 0;
                                
        while (!startQ.isEmpty()) {
            
            if (startQ.peek() < endQ.peek()) {
                startQ.poll();
                count++;
                max = Math.max(count, max);
            } else {
                endQ.poll();
                count--;
            }
        }
        
        answer = max;
        
        return answer;
    }
}