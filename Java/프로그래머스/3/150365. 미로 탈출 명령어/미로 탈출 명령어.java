import java.util.*;

class Solution {
    
    boolean[][] visit;
    String[] dir = {"d", "l", "r", "u"}; //사전순 - dlru 
    int[] dx = new int[]{1, 0, 0, -1};
    int[] dy = new int[]{0, -1, 1, 0};
    int n, m, r, c, k;
    String answer;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.visit = new boolean[n][m];
        this.r = r;
        this.c = c;
        this.k = k;
        this.n = n;
        this.m = m;
        
        if ( (Math.abs(r-x) + Math.abs(c-y))%2 != k%2 ) {
            return "impossible";
        }
        
        dfs(x, y, 0, "");
        
        if (answer == null) {
            return "impossible";
        }
        
        return answer;
    }
    
    private void dfs(int sX, int sY, int moveLength, String move) {
        if (answer != null) return;
        if (sX > n || sX < 1 || sY > m || sY < 1) return;
        if (moveLength == k) {
            if (sX == r && sY == c) {
                answer = move;
            }
            return;
        }
        
        // 남은 이동거리로 못감
        if (k - moveLength < Math.abs(sX - r) + Math.abs(sY - c) ) {
            return;
        }
        
        
        for (int i = 0; i < dx.length; i++) {
            dfs(sX+dx[i], sY+dy[i], moveLength+1, move+dir[i]);
        }
        
    }
}