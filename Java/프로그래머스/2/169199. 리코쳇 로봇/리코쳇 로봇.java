// import java.util.*;

class Solution {
    
    int[][] visitCount;
    char[][] boards;
    
    public int solution(String[] board) {
        int answer = 0;
        
        visitCount = new int[board.length][];
        boards = new char[board.length][];
        
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        
        for (int i = 0; i < boards.length; i++) {
            boards[i] = board[i].toCharArray();
            visitCount[i] = new int[boards[i].length];
            for (int j = 0; j < boards[i].length; j++) {
                if (boards[i][j] == 'R') {
                    startPoint[0] = i;
                    startPoint[1] = j;
                } else if (boards[i][j] == 'G') {
                    endPoint[0] = i;
                    endPoint[1] = j;
                }
            }
        }
        
        dfs(startPoint[0], startPoint[1], 1);
        
        // System.out.println(Arrays.deepToString(boards));
        
        answer = visitCount[endPoint[0]][endPoint[1]] != 0 ? visitCount[endPoint[0]][endPoint[1]] : -1;
        
        return answer;
    }
    
    public void dfs(int x, int y, int count) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        for (int i = 0; i < dx.length; i++) {
            int cx = x;
            int cy = y;
            
            // 유효한 포인트까지 진행
            while(isPointValid(cx + dx[i], cy + dy[i])) {
                cx += dx[i];
                cy += dy[i];
            }
            
            if (x == cx && y == cy) {
                continue;
            }
            
            // 지나간 포인트가 아니면 그위치에서 다시 검색
            if (visitCount[cx][cy] > 0 && visitCount[cx][cy] < count) {
                continue;
            }
            
            visitCount[cx][cy] = count;
            
            // G인지 확인 
            if (boards[cx][cy] == 'G') {
                break;
            }
            
            dfs(cx, cy, count + 1);
        }
    }
    
    public boolean isPointValid(int x, int y) {
        if (x < 0 || x >= boards.length
           || y < 0 || y >= boards[x].length
           || boards[x][y] == 'D') {
            return false;
        }
        return true;
    }
}