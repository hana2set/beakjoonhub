import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        int sum = 0;
        
        //무적권 사용할 q (최대값만)
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        for (int i = 0; i < enemy.length; i++) {
            q.add(enemy[i]);
            
            if (i < k) {
                continue;
            }
            
            n -= q.poll();
            
            if (n < 0) {
                return i;
            }
        }        
        
        return enemy.length;
    }
}