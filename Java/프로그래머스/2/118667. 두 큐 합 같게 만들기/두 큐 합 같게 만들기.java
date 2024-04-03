import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for (int element : queue1) {
            q1.add(element);        
        }
        
        for (int element : queue2) {
            q2.add(element);        
        }
        
        int lSum = calc(q1,q2);
        if (lSum == -1) {
            return -1;
        }
        int rSum = calc(q2,q1);
            
        return lSum > rSum ? lSum : rSum;
    }
    
    public int calc(Queue<Integer> q1, Queue<Integer> q2) {
        // 합계
        long q1Sum = q1.stream().mapToInt(i->i).sum();
        long sum = q1Sum + q2.stream().mapToInt(i->i).sum();
        int roopSize = q1.size() * 3;
        
        int count = 0;
        
        if (sum%2 != 0) {
            return -1;
        }
        
        for (int i = 0; i < roopSize + 1; i++) {
            int val;
            if (q1Sum > sum / 2.0) {
                val = q1.poll();
                q2.add(val);
                q1Sum -= val;
                count++;
            } else if (q1Sum < sum / 2.0) {
                val = q2.poll();
                q1.add(val);
                q1Sum += val;
                count++;
            } else {
                return count;
            }
            
            
            if (i == roopSize) {
                return -1;
            }
        }
        
        return -1;
    }
}
