import java.util.*;

class Solution {
    
    long answer;
    long[] longA;
    boolean[] visit;
    List<Integer>[] child;
    
    public long solution(int[] a, int[][] edges) {
        answer = 0;
        longA = new long[a.length];
        visit = new boolean[a.length];
        child = new ArrayList[a.length];
        
        long total = 0;
        for (int i = 0; i < a.length; i++) {
            total += a[i];
            longA[i] = a[i];
            child[i] = new ArrayList<>();
        }
        
        if (total != 0) {
            return -1;
        }
        
        for (int i = 0; i < edges.length; i++) {
            child[edges[i][0]].add(edges[i][1]);
            child[edges[i][1]].add(edges[i][0]);
        }
        
        dfs(0);
        
        return answer;
    }
    
    private long dfs(int point) {
        visit[point] = true;
                
        for (int i = 0; i < child[point].size(); i++) {
            int v = child[point].get(i);
            if (visit[v] == false) longA[point] += dfs(v);
        }
        
        answer += Math.abs(longA[point]);
        
        
        return longA[point];
    }
}