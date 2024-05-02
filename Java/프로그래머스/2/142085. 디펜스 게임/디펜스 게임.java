import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        int sum = 0;
        
        //무적권 사용한 q
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        for (int i = 0; i < enemy.length; i++) {
            q.add(enemy[i]);
            
            if (i < k) {
                continue;
            }
            
            n -= q.poll();
            
            
            if (n < 0) {
                answer = i;
                break;
            }
        }
        
        if (n >= 0) {
            answer = enemy.length;
        }
        
        
        return answer;
    }
}