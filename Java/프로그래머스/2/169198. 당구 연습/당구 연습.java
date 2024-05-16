import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        // 축 기준으로 반전후 거리 계산 -> 최소값구하기
        // 수직인경우, 대각선인경우 제외
        int[] x = new int[]{ startX, startX, -startX, 2*m - startX };
        int[] y = new int[]{ -startY, 2*n - startY, startY, startY };
        
        
        for (int i = 0; i < balls.length; i++) {
            for (int j = 0; j < x.length; j++){
                
                // 겹치는 경우
                int dx = balls[i][0] - x[j];
                int dy = balls[i][1] - y[j];
                if (dx == 0) {
                    if (balls[i][1] < y[j] && balls[i][1] > startY) continue;
                    if (balls[i][1] > y[j] && balls[i][1] < startY) continue;
                }
                if (dy == 0) {
                    if (balls[i][0] < x[j] && balls[i][0] > startX) continue;
                    if (balls[i][0] > x[j] && balls[i][0] < startX) continue;
                }
                
                //이동 거리 제곱
                int dir = (int) Math.pow(balls[i][0] - x[j], 2) + (int) Math.pow(balls[i][1] - y[j], 2);
                // if (dir <= 0) continue;
                if (answer[i] == 0 || answer[i] > dir) {
                    answer[i] = dir;
                }
            }
            
        }
        
        return answer;
    }
}