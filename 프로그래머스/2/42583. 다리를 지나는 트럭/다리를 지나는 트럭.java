import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int sum = 0;
        int truckCount = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < truck_weights.length-1; i++) {
            answer++; //한칸 전진
            truckCount++;
            sum += truck_weights[i];
            queue.add(truck_weights[i]);
            
            // System.out.println();
            // System.out.println("answer : " + answer);
            // System.out.println("sum : " + sum);
            // System.out.println("truck_weights[i] : " + truck_weights[i]);
            // System.out.println();
            
            if (bridge_length == queue.size()) {
                truckCount--;
                int out = queue.poll();
                sum -= out;
                
            }
            
            while (true) {
                if (sum + truck_weights[i+1] > weight) {
                    answer++;
                    queue.add(0);
                } else {
                    break;
                }
                
                if (bridge_length == queue.size()) {
                    int out = queue.poll();
                    if (out > 0) {
                        truckCount--;
                        sum -= out;
                    }
                }
                
            }
            
    }

        // i일경우
        answer += bridge_length + 1;

        return answer;
    }
}