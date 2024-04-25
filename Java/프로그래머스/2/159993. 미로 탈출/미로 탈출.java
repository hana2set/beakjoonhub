import java.util.*;

class Solution {
    
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    
    boolean[][] visit;
    char[][] mapInfo;
    
    public int solution(String[] maps) {
        int answer = 0;
        
        visit = new boolean[maps.length][maps[0].length()];
        mapInfo = new char[maps.length][maps[0].length()];
            
        int start_x = 0, start_y = 0, lever_x = 0, lever_y = 0, end_x = 0, end_y = 0;
        
        //맵 정보 채우기
        for (int i = 0; i < maps.length; i++) {
            char[] map = maps[i].toCharArray();
            for (int j = 0; j < map.length; j++) {
                mapInfo[i][j] = map[j];
                if (map[j] == 'S') {
                    start_x = i;
                    start_y = j;
                } else if (map[j] == 'L') {
                    lever_x = i;
                    lever_y = j;
                } else if (map[j] == 'E') {
                    end_x = i;
                    end_y = j;
                }
            }
        }
        
        int lever_count = bfs(start_x, start_y, lever_x, lever_y, 0);
        
        visit = new boolean[maps.length][maps[0].length()];
        
        answer = bfs(lever_x, 
                     lever_y, 
                     end_x, 
                     end_y, 
                     lever_count
                    );
        
        
        
        return answer;
    }
    
    public int bfs (int s_x, int s_y, int e_x, int e_y, int count) {
        if (count == -1) {
            return -1;
        }
        
        Queue<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < dx.length; i++) {
            q.add(new int[]{s_x + dx[i], s_y + dy[i], count+1});
        }
        
        visit[s_x][s_y] = true;
        
        while (!q.isEmpty()) {
            int[] q_value = q.poll();
            int x = q_value[0];
            int y = q_value[1];
            int t_count = q_value[2];
            
            if (x == e_x && y == e_y) {
                return t_count;
            }
            
            // 불가 조건
            if (x < 0 || x >= visit.length 
                    || y < 0 || y >= visit[0].length
                    || visit[x][y] == true 
                    || mapInfo[x][y] == 'X') {
                continue;
            }
            
            for (int i = 0; i < dx.length; i++) {
                q.add(new int[]{x + dx[i], y + dy[i], t_count+1});
            }
            
            visit[x][y] = true;
            
        }
        
        return -1;
    }
}