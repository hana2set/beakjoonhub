import java.util.*;

class Solution {
    
    Map<Integer, List<Integer>> treeMap = new HashMap<>();
    
    public int solution(int n, int[][] wires) {
        int min = n;
        
        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            if (treeMap.get(a) == null) {
                treeMap.put(a, new ArrayList<>());
            }
            if (treeMap.get(b) == null) {
                treeMap.put(b, new ArrayList<>());
            }
            
            treeMap.get(a).add(b);
            treeMap.get(b).add(a);
        }
        
        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            treeMap.get(a).remove(Integer.valueOf(b));
            treeMap.get(b).remove(Integer.valueOf(a));
            
            min = Math.min(min, dfs(a, n));
            
            treeMap.get(a).add(b);
            treeMap.get(b).add(a);
        }
        
        
        return min;
    }
    
    private int dfs(int start, int n) {
        
        boolean[] visit = new boolean[n];
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit[start-1] = true;
        int treeSize = 1;
        
        while (!queue.isEmpty()) {
            int left = queue.poll();
            for (int right : treeMap.get(left)) {
                if (visit[right-1] == true) continue;

                queue.add(right);
                visit[right-1] = true;
                treeSize++;
            }
        }
        
        return Math.abs(n-2*treeSize); // n-count, count의 차이
    }
}