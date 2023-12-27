import java.util.*;

class Solution {
    
    int max = 0;
    boolean[] visit;
    int[][] dgs;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        visit = new boolean[dungeons.length];
        dgs = dungeons;
        
        for (int i = 0; i < dgs.length; i++) {
            searchDungeon(i, k, 0);
        }
        
        answer = max;
        return answer;
    }
    
    private void searchDungeon(int index, int tired, int depth) {
        int remain = tired - dgs[index][1];
        if (dgs[index][0] > tired || remain < 0) {
            return;
        }
        
        visit[index] = true;
        max = Math.max(depth+1, max);
        
        for (int i = 0; i < dgs.length; i++) {
            if (visit[i] == false) {
                searchDungeon(i, remain, depth+1);
            }
        }
        
        visit[index] = false;
    }
}