import java.util.*;

class Solution {
    
    int[][] proc;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        proc = new int[rows][columns];
        
        for (int i = 0; i < proc.length; i++) {
            for (int j = 0; j < proc[i].length; j++) {
                proc[i][j] = columns*i + j + 1;
            }
        }
        
        for(int i = 0; i < queries.length; i++) {
            answer[i] = spinAndGetMin(queries[i]);
        }
        
        
        return answer;
    }
    
    public int spinAndGetMin(int[] query) {
        
        List<Integer> spinList = new ArrayList<>();
        
        int x1 = query[0];
        int y1 = query[1];
        int x2 = query[2];
        int y2 = query[3];
        
        int firstValue = proc[x1-1][y1-1];
        spinList.add(firstValue);
        
        // x1,y1 -> x2,y1
        for (int i = x1-1; i < x2-1; i++) {
            proc[i][y1-1] = proc[i+1][y1-1];
            spinList.add(proc[i][y1-1]);
        }
        
        // x2,y1 -> x2,y2
        for (int i = y1-1; i < y2-1; i++) {
            proc[x2-1][i] = proc[x2-1][i+1];
            spinList.add(proc[x2-1][i]);
        }
        
        // x2,y2 -> x1,y2
        for (int i = x2-1; i > x1-1; i--) {
            proc[i][y2-1] = proc[i-1][y2-1];
            spinList.add(proc[i][y2-1]);
        }        
        
        // x1,y2 -> x1,y1
        for (int i = y2-1; i > y1; i--) {
            proc[x1-1][i] = proc[x1-1][i-1];
            spinList.add(proc[x1-1][i]);
        }
        
        proc[x1-1][y1] = firstValue;
        
        
        Collections.sort(spinList);
        
        return spinList.get(0);
    }
}