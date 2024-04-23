import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        // col 번째 컬럼값 기준 오름차순 정렬
        // 같으면 첫번째 컬럼값으로 내림차순 정렬
        Arrays.sort(data, (o1, o2) -> o1[col-1] == o2[col-1] ? o2[0] - o1[0] : o1[col-1] - o2[col-1]);
        
        // S_i = (i 번째 행의 튜플)에 대해 (각 컬럼의 값)을 (i) 로 나눈 (나머지들의 합)
        int[] S_i = new int[data.length];
        for (int i = row_begin-1; i < row_end; i++) {
            for (int j = 0; j < data[i].length; j++) {
                S_i[i] += data[i][j] % (i+1);
            }
            
            // row_begin ≤ i ≤ row_end 인 모든 S_i를 누적하여 bitwise XOR 한 값을 해시 값으로서 반환 (^)
            answer ^= S_i[i];
        }
        
        
        // System.out.println(Arrays.deepToString(data));
        // System.out.println(Arrays.toString(S_i));
        
        
        return answer;
    }
}