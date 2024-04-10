import java.util.*;

class Solution {
    int[][] roads;
    int[] visit;
    int totalN;
    int totalK;
    int count = 0;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        roads = new int[N][N];
        visit = new int[N];
        totalN = N;
        totalK = K;
        
        for (int[] roadarr : road) {
            int a = roadarr[0];
            int b = roadarr[1];
            int v = roadarr[2];
            
            if (roads[a-1][b-1] == 0) {
                roads[a-1][b-1] = v;
                roads[b-1][a-1] = v;
            } else if (roads[a-1][b-1] > v) {
                roads[a-1][b-1] = v;
                roads[b-1][a-1] = v;
            }
        }
        
        count++;
        dfs(0);

        return count;
    }
    
    public void dfs(int start) {
        int n = visit[start];
        
        for (int i = 1; i < totalN; i++) { 
            
            // System.out.println("start : " + (start+1) + ", end : " + (i+1));
            // System.out.println("n : " + (n) + ", roads[start][i] : " + (roads[start][i]) + ", visit[] : " + visit[i]);
            
            if (start == i
                || roads[start][i] == 0 
                || n+roads[start][i] > totalK) continue;
            
            if (visit[i] == 0) {
                count++;
            } else if (visit[i] <= n+roads[start][i]) {
                continue;
            }
            
            visit[i] = n+roads[start][i];
            dfs(i);

        }
        
    }
}