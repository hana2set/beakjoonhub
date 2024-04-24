import java.util.*;

class Solution {
    
    ArrayList<int[]> moves = new ArrayList<>();
        
    public int[][] solution(int n) {
        
        // 해보면 좌우 번갈아가면서 전 횟수방식 + 마지막 하나 옮기기 (점화식)
        // n = 1 : [1,3]
        // n = 2 : [1,2], [1,3], [2,3]
        // n = 3 : [1,3], [1,2], [3,2], // [1,3], // [2,1], [2,3], [1,3]
        // n = 4 : [1,2], [1,3], [2,3], // [1,2], // [3,1], [3,2], [1,2], // [1,3], // [2,3], [2,1], [3,1], // [1,2], // [1,2], [1,3], [2,3]
        // ...
        
        
        hanoi(n, 1, 3, 2);
        
        int[][] answer = new int[moves.size()][];
        for (int i = 0; i < moves.size(); i++) {
            answer[i] = moves.get(i);
        }
        
        return answer;
    }
    
    private void hanoi(int n, int start, int target, int dummy) {
        if (n == 1) {
            moves.add(new int[]{start, target});
            return;
        }
        
        // (dummy로) n-1개 옮기기 + (target으로) 마지막꺼 옮기기 + (dummy에서 target으로) n-1개 옮기기 반복
        hanoi(n-1, start, dummy, target);
        moves.add(new int[]{start, target});
        hanoi(n-1, dummy, target, start);
        
    }
    
    
}