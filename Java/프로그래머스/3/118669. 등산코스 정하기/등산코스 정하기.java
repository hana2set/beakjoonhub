import java.util.*;

class Solution {
    
    Map<Integer, List<Path>> map = new HashMap<>();
    Set<Integer> gates = new HashSet<>();;
    Set<Integer> summits = new HashSet<>();;
    int answerSummit;
    int answerIntensity = Integer.MAX_VALUE;
    int[] dist = new int[50001];
    
    private static class Path<goal, intensity> {
        int goal, intensity;
        
        public Path(int goal, int intensity) {
            this.goal = goal;
            this.intensity = intensity;
        }
        
        public int getGoal() {
            return this.goal;
        }
        
        public int getIntensity() {
            return this.intensity;
        }
        
    }
    
    
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        
        for (int gate : gates) {
            this.gates.add(gate);
        }
        for (int summit : summits) {
            this.summits.add(summit);
        }
        
        for (int i = 0 ; i < paths.length; i++) {
            int[] path = paths[i];
            
            map.computeIfAbsent(path[0], k -> new ArrayList<>());
            map.computeIfAbsent(path[1], k -> new ArrayList<>());
            
            map.get(path[0]).add(new Path(path[1], path[2]));
            map.get(path[1]).add(new Path(path[0], path[2]));
        }
        
        Arrays.sort(summits);
        for (int summit : summits) {            
            dfs(summit, summit);
        }
        
        
        return new int[]{answerSummit, answerIntensity};
    }
    
    private void dfs(int start, int summit) {
        
        for (Path path : map.get(start)) {            
            int end = path.getGoal();
            int totalDist = Math.max(dist[start], path.getIntensity());      
            
            //봉우리 한번만 포함가능
            if (summits.contains(end)) {
                continue;
            }
            
            // 기존 답보다 느림
            if (totalDist > answerIntensity) {
                continue;
            }      
            
            // 이미 왔고 값이 더 큼
            if (dist[end] != 0 && dist[end] <= totalDist) {
                continue;
            }
            
            
            dist[end] = totalDist;
            
            // System.out.println("summit:" + summit);
            // System.out.println("start:" + start);
            // System.out.println("end:" + end);
            // System.out.println("totalDist:" + totalDist);
            // System.out.println();
                        
            // 출입구 도착
            if (gates.contains(end)) {
                if (dist[end] < answerIntensity) {
                    answerIntensity = dist[end];
                    answerSummit = summit;
                }
                
                continue;
                
            } else {
                dfs(end, summit);
            }
            
        }
    }
}