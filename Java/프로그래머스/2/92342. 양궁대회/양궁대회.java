import java.util.*;

class Solution {
    
    private int maxDiff = 1;
    private int[] info;
    private int[] answer = new int[]{-1};
    private int[] lionInfo = new int[11];
    
    public int[] solution(int n, int[] info) {
        // 가중치 개념으로 못품.. (결국 화살 갯수때문에 선택못하는 경우의수 탐색해봐야함.)
        // dfs
        
        this.info = info;
        
        dfs(0, n);
        
        return answer;
        
    }
    
    private void dfs(int index, int remainArrow) {
        // 마지막
        if (index == 11) {
            int diff = 0;
            lionInfo[10] = remainArrow;
            
            for (int i = 0; i < info.length; i++) {
                if (info[i] > lionInfo[i]) {
                    diff -= 10-i;
                } else if (info[i] < lionInfo[i]) {
                    diff += 10-i;
                }
            }            
            
            
            if (maxDiff < diff) {
                maxDiff = diff;
                answer = lionInfo.clone();
            }
            
            if (maxDiff == diff && answer[0] != -1) {
                // System.out.println(Arrays.toString(lionInfo));
                // System.out.println(maxDiff);
                // System.out.println(diff);
                for (int i = 10; i >= 0; i--) {
                    if (lionInfo[i] == answer[i]) {
                        continue;
                    }
                    if (lionInfo[i] > answer[i]) {
                        answer = lionInfo.clone();
                    }
                    break;
                }
            } 
            
            lionInfo[10] = 0;
            
            return;
        }
        
        // 1.지기, 2.이기기 
        
        // 1
        dfs(index+1, remainArrow);
        
        // 2
        if (remainArrow > 0 && remainArrow > info[index]) {
            lionInfo[index] = info[index]+1;
            dfs(index+1, remainArrow - info[index] - 1);
            lionInfo[index] = 0;
        }
        
    }
}