import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        for (int priority : priorities) {
            queue.add(priority);
        }
        
        Arrays.sort(priorities);
        
        
        int index = -1;
        int count = 0;
        for (int i = priorities.length-1; i >= 0; i--) {
            while (true) {
                index++;
                int priority = queue.poll();
                if (priority == priorities[i]) {
                    count++;
                    if (location == index%priorities.length) {
                        return count;
                    } else {
                        queue.add(-1);
                        break;
                    }
                } else {
                    queue.add(priority);
                }
            }
        }
        
        return answer;
    }
}
