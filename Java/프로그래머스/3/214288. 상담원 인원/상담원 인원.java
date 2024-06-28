import java.util.*;

class Solution {
    
    PriorityQueue<int[]>[] q;
    int[][] reqs;
    int[] counselor;
    int answer = Integer.MAX_VALUE;
    int n, k;
    
    public int solution(int k, int n, int[][] reqs) {
        
        this.q = new PriorityQueue[k];
        this.counselor = new int[k];
        this.reqs = reqs;
        this.k = k;
        this.n = n;
        
        //초기화
        for (int i = 0; i < k; i++) {
            q[i] = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
            counselor[i] = 1;
        }
        
        counselor[0] += n-k;
        
        counsel(0);
        
        
        
        return answer;
    }
    
    public void counsel(int index) {
        int waitingTime = 0;
        
        for (int[] req : reqs) {
            q[req[2]-1].add(req);
        }
        
        for (int i = 0; i < k; i++) {
            PriorityQueue<int[]> c = q[i];
            int[] counselerTime = new int[counselor[i]];
            
            if (waitingTime >= answer) {
                c.clear();
                continue;
            }
            
            // 상담 유형당 시간 계산
            while (!c.isEmpty()) {
                int[] req = c.poll();
                
                Arrays.sort(counselerTime);
                
                if (counselerTime[0] > req[0]) {
                    waitingTime += counselerTime[0] - req[0];
                    counselerTime[0] += req[1];
                } else {
                    counselerTime[0] = req[0] + req[1];
                }
            }
            
        }
        
        answer = Math.min(waitingTime, answer);
        
        if (index < k-1) {
            while (counselor[index] > 1) {
                counselor[index]--;
                counselor[index+1]++;
                int next = counselor[index+1];
                
                counsel(index+1);
                
                counselor[index+1] = next;
                
                for (int i = index+2; i < k; i++) {
                    counselor[i] = 1;
                }
                
            }
        }
        
    }
}