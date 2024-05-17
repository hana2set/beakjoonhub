import java.util.*;

class Solution {
    
    char[][] cGrid;
    boolean[][][] visit;
    int[] dx = new int[]{1, 0, -1, 0}; //우상좌하
    int[] dy = new int[]{0, 1, 0, -1};
    
    List<Integer> answerList = new ArrayList<>();
    
    public int[] solution(String[] grid) {
        
        cGrid = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            cGrid[i] = grid[i].toCharArray();
        }
        
        // 각 방향별 방문 여부
        visit = new boolean[cGrid.length][cGrid[0].length][4];
        
        for (int i = 0; i < cGrid.length; i++) {
            for (int j = 0; j < cGrid[0].length; j++) {
                for (int k = 0; k < dx.length; k++) {
                    
                    int count = dfs(i, j, k, 0);
                    if (count == 0) continue;
                    
                    answerList.add(count);
                }
            }
        }
        
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    // 재귀 사용시 런타임에러
    private int dfs(int x, int y, int dir, int length) {
        
        while(true) {
        
            if (cGrid[x][y] == 'L') dir = (dir+3) % 4;
            if (cGrid[x][y] == 'R') dir = (dir+1) % 4;

            if (visit[x][y][dir] == true) {
                return length;
            }

            visit[x][y][dir] = true;

            // int targetX = (x+dx[dir]+cGrid.length) % cGrid.length;
            // int targetY = (y+dy[dir]+cGrid[x].length) % cGrid[x].length;
            x = (x+dx[dir]+cGrid.length) % cGrid.length;
            y = (y+dy[dir]+cGrid[x].length) % cGrid[x].length;
            
            length++;
        }
        
        // return dfs( targetX, targetY, dir, length + 1 );
    }
    
}