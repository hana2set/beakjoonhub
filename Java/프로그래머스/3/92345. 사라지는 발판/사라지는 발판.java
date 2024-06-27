import java.util.*;

class Solution {
    
    int[] dx = new int[]{1,-1,0,0};
    int[] dy = new int[]{0,0,1,-1};
    int[][] board;
    int[] aloc;
    int[] bloc;
    
    class Result {
        boolean win = false;
        int moveCount = 0;
        
        Result(boolean win, int cnt) {
            this.win = win;
            this.moveCount = cnt;
        }
    }
    
    // 번갈아가며 움직이기 때문에, 한명이 반드시 이김.
    // a,b 둘중 한명이 무조건 이기는 수가 존재함.
    // dfs로 한명이 무조건 이기는 경우의 수 계산 후
    // b 최대값, a 최소값 순으로 계산
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        this.aloc = aloc;
        this.bloc = bloc;
        
        Result r = move(0, 0);
        
        return r.moveCount;
    }
    
    // backtracking
    // 깊게 들어가다가 움직일 수 없으면 Result.win = false 반환
    public Result move(int aMove, int bMove) {
        boolean isWin = false;
        int winMove = Integer.MAX_VALUE;
        int loseMove = aMove + bMove;
        
        if (aMove == bMove && board[aloc[0]][aloc[1]] == 1) {
            for (int i = 0; i < dx.length; i++) {
                int ax = aloc[0] + dx[i];
                int ay = aloc[1] + dy[i];
                
                // 이동할 수 없으면 패배
                if (isLose(new int[]{ax, ay})) continue;
                
                // 위치 변경
                board[aloc[0]][aloc[1]] = 0;
                aloc[0] = ax;
                aloc[1] = ay;
                
                // a가 움직이고 결과 반환
                Result r = move(aMove+1, bMove);
                
                // move(a) 이후 b의 모든 경우의 수가 패배 -> a의 승리
                // a의 필승 move가 있음으로, 최적의 수를 계산 (min)
                if (r.win == false) {
                    isWin = true;
                    winMove = Math.min(winMove, r.moveCount);
                } else {
                // r.win(b의 모든 경우의 수)이 모두 승리라면, 패배 -> 최대값 반환
                    loseMove = Math.max(loseMove, r.moveCount);
                }
                
                //위치 초기화
                aloc[0] = ax - dx[i];
                aloc[1] = ay - dy[i];
                board[aloc[0]][aloc[1]] = 1;
            }
        }
        
        if (aMove > bMove && board[bloc[0]][bloc[1]] == 1) {
            for (int i = 0; i < dx.length; i++) {
                int bx = bloc[0] + dx[i];
                int by = bloc[1] + dy[i];
                
                // 이동할 수 없으면 패배
                if (isLose(new int[]{bx, by})) continue;
                
                // 위치 변경
                board[bloc[0]][bloc[1]] = 0;
                bloc[0] = bx;
                bloc[1] = by;
                
                // 움직인 곳에서 상태 반환
                Result r = move(aMove, bMove+1);
                
                // move(b) 이후 a의 모든 경우의 수가 패배 -> a의 승리
                // b의 필승 move가 있음으로, 최적의 수를 계산 (min)
                if (r.win == false) {
                    isWin = true;
                    winMove = Math.min(winMove, r.moveCount);
                } else {
                // r.win(a의 모든 경우의 수)이 모두 승리라면, 패배 -> 최대값 반환
                    loseMove = Math.max(loseMove, r.moveCount);
                }
                
                //위치 초기화
                bloc[0] = bx - dx[i];
                bloc[1] = by - dy[i];
                board[bloc[0]][bloc[1]] = 1;
            }
        }
        
        //
        return new Result(isWin, isWin ? winMove : loseMove);
    }
    
    public boolean isLose(int[] pos) {
        if (pos[0] < 0 || pos[0] >= board.length
            || pos[1] < 0 || pos[1] >= board[pos[0]].length
            || board[pos[0]][pos[1]] == 0) return true;
        return false;
            
    }
}