class Solution {
    public int solution(String[] board) {
        // 선공 O 후공 X
        // O가 이기면 하나더 많아야함
        // X가 이기면 같은 갯수
        // 승패 안나면 +1 +0
        
        char[][] boardArr = new char[3][];
        boardArr[0] = board[0].toCharArray();
        boardArr[1] = board[1].toCharArray();
        boardArr[2] = board[2].toCharArray();
        
        //가로세로 승패
        char win = '.';
        for (int i = 0; i < boardArr.length; i++) {
            if (boardArr[i][0] == boardArr[i][1]
                && boardArr[i][0] == boardArr[i][2]
                ) {
                
                if (win != '.' && win != boardArr[i][0]) {
                    return 0;
                }
                
                win = boardArr[i][0];
            }
            
            if (boardArr[0][i] == boardArr[1][i]
                && boardArr[1][i] == boardArr[2][i]
                ) {
                
                if (win != '.' && win != boardArr[0][i]) {
                    return 0;
                }
                
                win = boardArr[0][i];
            }
        }
        
        //대각선 승패
        if (win == '.') {
            if (boardArr[0][0] == boardArr[1][1]
                    && boardArr[1][1] == boardArr[2][2]
               ) {
                
                if (win != '.' && win != boardArr[1][1]) {
                    return 0;
                }
                
                win = boardArr[1][1];
            }
            
            if (boardArr[2][0] == boardArr[1][1]
                    && boardArr[1][1] == boardArr[0][2]
               ) {
                
                if (win != '.' && win != boardArr[1][1]) {
                    return 0;
                }
                
                win = boardArr[1][1];
            }
        }
        
        //총 갯수
        int oCount = 0;
        int xCount = 0;
        for (int i = 0; i < boardArr.length; i++) {
            for (int j = 0; j < boardArr[i].length; j++) {
                if (boardArr[i][j] == 'O') oCount++;
                if (boardArr[i][j] == 'X') xCount++;
            }
        }
        
        // 갯수
        if (win == 'O') {
            if (oCount != xCount+1) {
                return 0;
            }
        }
        
        if (win == 'X') {
            if (oCount != xCount) {
                return 0;
            }
        }
        
        if (win == '.') {
            if (oCount != xCount
               && oCount != xCount+1) {
                return 0;
            }
        }
        
        return 1;
    }
}