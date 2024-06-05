import java.util.*;

class Solution {
    
    int[] info;
    int[][] edges;
    boolean[] visit;
    int answer;    
    
    public int solution(int[] info, int[][] edges) {
        this.answer = 0;
        this.info = info;
        this.edges = edges;
        this.visit = new boolean[info.length];
        
        Arrays.sort(edges, (e1, e2) -> e1[0] - e2[0]);
        
        if (info[0] == 1) {
            return 0;
        }
        
        this.visit[0] = true;
        
        dfs(1, 0);
        
        return answer;
    }
    
    private void dfs(int sheep, int wolf) {
        if (sheep > this.answer) {
            this.answer = sheep;
        }
        
        for (int i = 0; i < visit.length; i++) {
            boolean isVisit = visit[i];
            
            // 지나간 곳에서 시작할 수 있음.
            if (isVisit == true) {
                
                for (int[] node : edges) {
                    if (node[0] < i) continue;
                    if (node[0] > i) break;

                    // 목표 이미 방문 -> 탐색 불필요
                    if (visit[node[1]] == true) {
                        continue;
                    }
                    
                    if (info[node[1]] == 0) { //양일 경우
                        visit[node[1]] = true;
                        dfs(sheep+1, wolf);
                        visit[node[1]] = false;
                    } else { //늑대일 경우
                        if (wolf+1 >= sheep) continue;

                        visit[node[1]] = true;
                        dfs(sheep, wolf+1);
                        visit[node[1]] = false;

                    }
                }
                
                
            }
        }
        
        
    }
}