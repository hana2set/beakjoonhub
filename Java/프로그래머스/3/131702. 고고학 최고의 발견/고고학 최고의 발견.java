import java.util.*;

class Solution {
    int N;
    int[][] clockHands;
    int[] rotate;
    int[] dx = {1,-1,0,0,0};
    int[] dy = {0,0,1,-1,0};
    int answer = Integer.MAX_VALUE;
    
    // 돌린 순서는 영향 안받음
    // 첫째줄 완성 -> 다음줄부터 확정 (윗줄을 0으로 해야함으로)
    // 첫번째 줄 0-3 dfs, 나머지는 계산
    public int solution(int[][] clockHands) {        
        this.clockHands = clockHands;
        this.N = clockHands.length;
        this.rotate = new int[N];
        
        // 가중치로 3,2,1,0 으로 변경
        for (int i = 0; i < clockHands.length; i++) {
            for (int j = 0; j < clockHands[i].length; j++) {
                clockHands[i][j] = (4 - clockHands[i][j]) % 4;
            }
        }
        
        // caluate weight
        for (int i = 0; i < clockHands.length; i++) {
            for (int j = 0; j < clockHands[i].length; j++) {
                int weight = clockHands[i][j];
                if (i-1 >= 0) weight += clockHands[i-1][j];
                if (j-1 >= 0) weight += clockHands[i][j-1];
                if (i+1 < clockHands.length) weight += clockHands[i+1][j];
                if (j+1 < clockHands[i].length) weight += clockHands[i][j+1];
            }
        }
        
        dfs();
        
        return answer;
    }
    
    
    public void dfs() {
        int count = 0;
        int index = 0;
        while (rotate[index] > 3) {
            rotate[index] = 0;
            if (++index >= N) return;
            
            rotate[index]++;
        }
        
        
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.copyOf(clockHands[i], N);
        }
        
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if ((x == 0 && rotate[y] != 0)
                   || (x != 0 && map[x-1][y] != 0)) {
                    
                    int rotationCount = x == 0 ? rotate[y] : map[x-1][y];
                    
                    for (int i = 0; i < dx.length; i++) {
                        if (x+dx[i] >= 0 && y+dy[i] >= 0
                           && x+dx[i] < N && y+dy[i] < N)  {
                            map[x+dx[i]][y+dy[i]] = (map[x+dx[i]][y+dy[i]] + 3 * rotationCount) % 4;
                        }

                    }

                    count += rotationCount;
                    
                }
            }
        }
        
        //마지막줄이 다 0인지 확인
        boolean isLastLineZero = true;
        for (int x = N-1, y = 0; y < N && x >= 0; y++) {
            if (map[x][y] != 0) {
                isLastLineZero = false;
                break;
            }
        }
        
        if (isLastLineZero) {
            answer = Math.min(answer, count);
        }
        
        rotate[0]++;
        dfs();
    }
}