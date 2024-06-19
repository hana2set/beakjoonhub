import java.util.*;

class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;
        // 한 점에 대해 변경 가능 수 = 행,열
        // 순서 차이만 있음. -> 반대 경우 = (행+열) - 결과값 
        
        // 열뒤집기
        for (int i = 0; i < beginning.length; i++) {
            if (beginning[i][0] != target[i][0]) {
                answer++;
                for (int j = 0; j < beginning[i].length; j++) {
                    beginning[i][j] = beginning[i][j] == 0 ? 1 : 0;
                }
            }
            
        }
        
        // 행뒤집기
        for (int i = 0; i < beginning[0].length; i++) {
            if (beginning[0][i] != target[0][i]) {
                answer++;
                for (int j = 0; j < beginning.length; j++) {
                    beginning[j][i] = beginning[j][i] == 0 ? 1 : 0;
                }
            }
        }
        
        // 일치 비교
        for (int i = 0; i < beginning.length; i++) {
            for (int j = 0; j < beginning[i].length; j++) {
                if (beginning[i][j] != target[i][j]) return -1;
            }
        }
        
        return Math.min(answer, beginning.length + beginning[0].length - answer);
    }
}