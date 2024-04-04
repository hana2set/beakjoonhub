import java.util.*;

class Solution {
    
    boolean[][] mapsChecker;
    String[][] mapsValue;
    List<Integer> resultList;
    int sum;
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        mapsChecker = new boolean[maps.length][maps[0].length()];
        mapsValue = new String[maps.length][];
        resultList = new ArrayList<>();
        
        for (int i = 0; i < mapsChecker.length; i++) {
            mapsValue[i] = maps[i].split("");
        }
        
        for (int i = 0; i < mapsValue.length; i++) {
            for (int j = 0; j < mapsValue[i].length; j++) {
                if (mapsChecker[i][j] == false) {
                    sum = 0;
                    findFood(i, j);
                    if (sum != 0) {
                        resultList.add(sum);
                    }
                }
            }   
        
        }
        
        if (resultList.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        
        answer = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    public void findFood(int x, int y) {
        
        if (x < 0 || x >= mapsValue.length
            || y < 0 || y >= mapsValue[x].length
            || mapsChecker[x][y] == true) { 
            return;
        }
        
        if (mapsValue[x][y].equals("X")) {
            mapsChecker[x][y] = true;
            return;
        } else {
            sum += Integer.parseInt(mapsValue[x][y]);
            mapsChecker[x][y] = true;
            
            findFood(x-1, y); 
            findFood(x, y-1); 
            findFood(x+1, y); 
            findFood(x, y+1); 
        }
    }
}