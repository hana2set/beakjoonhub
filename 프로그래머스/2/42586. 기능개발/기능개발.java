import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int day = 0;
        for (int i = 0; i < progresses.length; i++) {
            int subDay = progresses[i] % speeds[i] != 0 ? 1 : 0;
            day = ((100 - progresses[i]) / speeds[i]) + subDay;
                        
            int completeCount = 1;
            while (i+1 < progresses.length && progresses[i+1] + (day * speeds[i+1]) >= 100) {
                i++;
                completeCount++;
            }
            
            answer.add(completeCount);
        }
        
        return answer;
    }
}