import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        // 연결이 목표 - 외부로 연결 하나이상만 되어있으면됨
        // 각 포인트의 최소값을 선택하면서 확장
        
        int answer = 0;
        boolean[] visit = new boolean[n];
        
        Arrays.sort(costs, (o1, o2) -> o1[2]-o2[2]);
        
        // 첫번째 선택
        visit[costs[0][0]] = true;
        visit[costs[0][1]] = true;
        answer += costs[0][2];
        
        for (int i = 0; i < costs.length; i++) {
            // 한곳 방문, 다른곳 미방문일 경우
            if (visit[costs[i][0]] != visit[costs[i][1]]) {
                visit[costs[i][0]] = true;
                visit[costs[i][1]] = true;
                answer += costs[i][2];
                i = 0;
            }
        }
        
        
        return answer;
    }
}