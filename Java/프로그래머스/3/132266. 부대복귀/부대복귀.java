import java.util.*;

class Solution {
    
    Map<Integer, List<Integer>> roadMap = new HashMap<>();
    Queue<Integer> q = new LinkedList<>();
    int[] range;
        
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        //역방향으로 destination에서 source까지 거리를 계산 (bfs방식)
        
        for (int[] road : roads) {
            if (roadMap.get(road[0]) == null) roadMap.put(road[0], new ArrayList<>());
            if (roadMap.get(road[1]) == null) roadMap.put(road[1], new ArrayList<>());
            
            roadMap.get(road[1]).add(road[0]);
            roadMap.get(road[0]).add(road[1]);
        }
        
        range = new int[n+1];
        q.add(destination);

        int count = 0;
        while (!q.isEmpty()) {
            int next = q.poll();
            count = range[next] + 1;
            
            if (roadMap.get(next) == null) continue;
            
            for(int road : roadMap.get(next)) {
                if (road == destination || range[road] > 0) {
                    continue;
                }
                
                range[road] = count;
                q.add(road);
            }

        }
        
        for (int i = 0; i < sources.length; i++) {
            if (sources[i] == destination) {
                answer[i] = 0;
                continue; 
            }
            
            answer[i] = range[sources[i]] == 0 ? -1 : range[sources[i]];
        }
        
        return answer;
    }
    
}