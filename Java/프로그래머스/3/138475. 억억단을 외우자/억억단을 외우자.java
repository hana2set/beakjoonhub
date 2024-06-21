import java.util.*;

class Solution {
    
    private class Num {
        int number, count;
        public Num(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }
    
    public int[] solution(int e, int[] starts) {
        
        int[] answer = new int[starts.length];
        Num[] dp = new Num[e];
        
        for (int i = 1; i <= e; i++) {
            dp[i-1] = new Num(i, 1);
        }
        
        // 약수 더하기
        for (int i = 1; i <= e; i++) {
            for (int j = 2; j <= e/i; j++) {
                dp[i*j - 1].count++;
            }
        }
        
        Arrays.sort(dp, (p1, p2) -> {
            if (p1.count < p2.count) return 1;
            else if (p1.count > p2.count) return -1;
            else {
                if (p1.number > p2.number) return 1;
                else return -1;
            }
        });
        
        
        
        for (int i = 0; i < starts.length; i++) {
            int start = starts[i];
            for (int j = 0; j < e; j++) {
                if (dp[j].number >= start) {
                    answer[i] = dp[j].number;
                    break;
                }
            }
        }
            
        return answer;
    }
    
}