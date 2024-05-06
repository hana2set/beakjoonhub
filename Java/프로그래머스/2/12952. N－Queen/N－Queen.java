import java.util.*;

class Solution {
    
    private int[] board;
    private int total;
    
    public int solution(int n) {
        int answer = 0;
        //백트래킹 문제
        //board[i] = j -> i행 j열에 체스 두기
        //이렇게하면 i행은 겹치지 않음으로 가로 체크할 필요 없음.
        //j 열과 대각선 겹치지 않는 총 갯수 구하면 됨
        
        board = new int[n];
        
        backtracking(n, 0);
        
        answer = total;
        
        return answer;
    }
    
    public void backtracking(int n, int row) {
        if (row == n) {
            total++;
            return;
        }
        
        
        for (int i = 0; i < n; i++) {
            board[row] = i;
            
            if (isBoardValid(row, i)) {
                backtracking(n, row+1);
            }
        }
    }
    
    public boolean isBoardValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 열 값이 겹치는가?
            if (board[i] == col) return false;
            // 대각선으로 겹치는가?
            if (Math.abs(i - row) == Math.abs(board[i] - col)) return false;
        }
        
        return true;
    }
    
    
}