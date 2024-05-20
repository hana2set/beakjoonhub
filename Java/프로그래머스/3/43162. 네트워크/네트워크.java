class Solution {
    boolean[] visit;
    int answer = 0;
    int n;
    int[][] computers;
    
    public int solution(int n, int[][] computers) {
        this.visit = new boolean[n];
        this.n = n;
        this.computers = computers;
        
        for (int i = 0; i < n; i++) {
            if (visit[i] == true) continue;
            
            dfs(i);
            answer++;
        }
        
        return answer;
    }
    
    private void dfs(int start) {
        
        visit[start] = true;
        
        for (int target = 0; target < n; target++) {
            if (start == target) continue;
            
            if (computers[start][target] == 1 && visit[target] == false) {
                dfs(target);
            }
        }
        
    }
}