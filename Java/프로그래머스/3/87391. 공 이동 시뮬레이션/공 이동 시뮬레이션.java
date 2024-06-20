import java.util.*;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        // 역순으로 계산        
        // index
        long xMax = x;
        long xMin = x;
        long yMax = y;
        long yMin = y;
        
        for (int i = queries.length-1; i >= 0; i--) {
            switch(queries[i][0]) {
                case 0:
                    yMax += queries[i][1];
                    if (yMin != 0) {
                        yMin += queries[i][1];
                    }
                    if (yMin >= m) return 0;
                    if (yMax >= m) {
                        yMax = m-1;
                    }
                    break;
                case 1:
                    if (yMax != m-1) {
                        yMax -= queries[i][1];
                    }
                    yMin -= queries[i][1];
                    if (yMax < 0) return 0;
                    if (yMin < 0) {
                        yMin = 0;
                    }
                    break;
                case 2:
                    xMax += queries[i][1];
                    if (xMin != 0) {
                        xMin += queries[i][1];
                    }
                    if (xMin >= n) return 0;
                    if (xMax >= n) {
                        xMax = n-1;
                    }
                    break;
                case 3:
                    if (xMax != n-1) {
                        xMax -= queries[i][1];
                    }
                    xMin -= queries[i][1];
                    if (xMax < 0) return 0;
                    if (xMin < 0) {
                        xMin = 0;
                    }
                    break;
            }
        }
        
        
        return (xMax-xMin+1)*(yMax-yMin+1);
    }
}