import java.util.*;

class Solution {
    boolean[] visit;
    int[] light;
    Map<Integer, List<Integer>> roadMap;
    int answer = 0;
    
    public int solution(int n, int[][] lighthouse) {
        // 뱃길이 n-1개 임으로, 트리 구조를 띄게됨 (사이클이 생기려면 최소 n개 여야함)
        // 최하위 노드 (leaf node)가 상위 노드에 영향을 주려면 모든 등대가 켜져있어야 함으로
        // 최하위 노드가 꺼진 상태로 가정하는 것이 최소값에 유리
        
        // 한 지점에서 시작해, dfs 검색
        visit = new boolean[n+1];
        light = new int[n+1];
        roadMap = new HashMap<>();
        
        for (int i = 0; i < lighthouse.length; i++) {
            roadMap.computeIfAbsent(lighthouse[i][0], k -> new ArrayList<>());
            roadMap.computeIfAbsent(lighthouse[i][1], k -> new ArrayList<>());
            roadMap.get(lighthouse[i][0]).add(lighthouse[i][1]);
            roadMap.get(lighthouse[i][1]).add(lighthouse[i][0]);
        }
        
        dfs(1);
        
        for (int onOff : light) {
            if (onOff == 1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dfs (int start) {
        visit[start] = true;
        
        List<Integer> target = roadMap.get(start);
        
        // 리프 노드일 경우
        if (target.size() == 1) {
            if (visit[target.get(0)] == true) return;
        }
        
        for (int i = 0; i < target.size(); i++) {
            if (visit[target.get(i)] == true) continue;
            
            dfs(target.get(i));
            
            // 불이 꺼져있으면 켜야함
            if (light[target.get(i)] == 0) light[start] = 1;
        }
    }
}