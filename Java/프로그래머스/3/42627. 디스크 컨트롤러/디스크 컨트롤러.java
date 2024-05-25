import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        // 응답시간을 줄이기위해서는 작은작업부터 해야함 (합/2임으로)
        
        int time = 0;
        int totalTime = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        int index = 0;
        while (true) {
            while (index < jobs.length && jobs[index][0] <= time) {
                queue.add(jobs[index++]);
            }
            
            if (queue.isEmpty()) {
                if (index < jobs.length) {
                    time = jobs[index][0];
                    continue;
                } else {
                    break;
                }
            }
            
            int[] job = queue.poll();
            
            time += job[1];
            totalTime += time - job[0];
        }
        
        
        return totalTime / jobs.length;
    }
}