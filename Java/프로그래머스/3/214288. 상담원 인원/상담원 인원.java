import java.util.*;

class Solution {
    
    PriorityQueue<int[]>[] q;
    int[][] reqs;
    int[] counselor;
    int answer = Integer.MAX_VALUE;
    int n, k;
    
    Queue<int[]> CounselorQ;
    
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
        
        
        CounselorQ = new LinkedList<>();
        
        addCounselorQ(new int[k], 0, n);
        
        while (!CounselorQ.isEmpty()){
            counsel(CounselorQ.poll());
        }
        
        
        
        return answer;
    }
    
    public void addCounselorQ(int[] counselor, int index, int n) {
        if (n < 0) return;
        if (index == k-1) {
            if (n==0) return;
            counselor[index] = n;
            CounselorQ.add(Arrays.copyOf(counselor, counselor.length));
            return;
        }
        
        counselor[index] = 1;
        
        for (int i = 1; i < n; i++) {
            counselor[index] = i;
            addCounselorQ(counselor, index+1, n-i);
        }
            
        
    }
    
    public void counsel(int[] counselor) {
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
    }
}