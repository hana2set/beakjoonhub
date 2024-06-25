import java.util.*;

class Solution {
    
    public int[][] cost = { //번호간 가중치
        { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
        { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
        { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
        { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
        { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
        { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
        { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
        { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
        { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
        { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };
    
    public int[][][] dp; //dp[ind][left][right]
    public char[] numberArr;
    public int len;
    
    public int solution(String numbers) {
        
        numberArr = numbers.toCharArray();
        len = numberArr.length;
        dp = new int[len+1][10][10];
        
        for (int i = 0; i < len + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
        
        return solve(0, 4, 6);
    }
    
    public int solve(int index, int l, int r) {
        // 사이즈 초과 시
        if (index == len) {
            return 0;
        }
        
        if (dp[index][l][r] != -1) return dp[index][l][r];
        
        int number = numberArr[index] - '0';
        int result = Integer.MAX_VALUE;
        
        // 왼손 움직이기
        if (number != r) result = Math.min(solve(index+1, number, r) + cost[l][number], result);
        // 오른손
        if (number != l) result = Math.min(solve(index+1, l, number) + cost[r][number], result);
        
        // 값이 같으면 해당손만 가능
        // 두 값 중 최소값으로 이동
        
        return dp[index][l][r] = result;
        
    }
    
}