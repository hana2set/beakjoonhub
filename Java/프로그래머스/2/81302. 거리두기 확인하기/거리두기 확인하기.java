import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        
        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            // System.out.println("place start");
            answer[i] = checkRule(place);
            // System.out.println("place end");
        }
        
        return answer;
    }
    
    public int checkRule(String[] place) {
        char[][] placeChar = new char[5][5];
        
            for (int i = 0; i < place.length; i++) {
                placeChar[i] = place[i].toCharArray();
            }
            
            for (int i = 0; i < placeChar.length; i++) {
                for (int j = 0; j < placeChar[i].length; j++) {

                    if (placeChar[i][j] == 'P') {
                        
                        for (int x = i > 2 ? i-2 : 0; x < 5 && x < i+3; x++) {
                            for (int y = j > 2 ? j-2 : 0; y < 5 && y < j+3; y++) {
                                if (Math.abs(x-i) + Math.abs(y-j) > 2) continue;
                                if (x == i && y == j) continue;
                                
                                // System.out.println("i = " + i + ", j = " + j + ", x = " + x + ", y = " + y);
                                
                                //멘헤튼 거리에 P가 있으면
                                //좌우 꼭지점 -> 시작P 중심점 인접한곳은(방향에서) 무조건 X여야함
                                //도착P가 +-1 -> 시작P 중심점 인접한곳은(방향에서) 무조건 X여야함
                                if (placeChar[x][y] == 'P') {
                                    //좌우 꼭지점
                                    if (x-i == -2 || x-i == -1) {
                                        if (placeChar[i-1][j] != 'X') {
                                            return 0;
                                        }
                                    }
                                    
                                    if (x-i == 2 || x-i == 1) {
                                        if (placeChar[i+1][j] != 'X') {
                                            return 0;
                                        }
                                    }
                                    
                                    if (y-j == -2 || y-j == -1) {
                                        if (placeChar[i][j-1] != 'X') {
                                            return 0;
                                        }
                                    }
                                    
                                    if (y-j == 2 || y-j == 1) {
                                        if (placeChar[i][j+1] != 'X') {
                                            return 0;
                                        }
                                    }
                                }
                            }   
                        }
                    }
                    
                }                
            }
        return 1;
    }
}