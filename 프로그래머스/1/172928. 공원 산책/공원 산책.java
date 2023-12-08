import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        int[] position = {};
        for (int i = 0; i < park.length; i++) {
            if (park[i].indexOf("S") >= 0) {
                position = new int[]{i, park[i].indexOf("S")};
            };
        }
        for (String route : routes) {
            String[] od = route.split(" ");
            String op = od[0];
            int n = Integer.parseInt(od[1]);
            boolean isBreaked = false;
            
            if (op.equals("E")) {
                if (position[1]+n+1 > park[position[0]].length()) {
                    isBreaked = true;
                } else {
                    for (int i = position[1]+1; i < position[1]+n+1; i++) {
                        if (park[position[0]].charAt(i) == 'X') {
                            isBreaked = true;
                            break;
                        }
                    }   
                }
                
                if (isBreaked == false)  {
                    position[1] += n;
                }
                
            } else if (op.equals("W")) {
                if (position[1]-n < 0) {
                    isBreaked = true;
                } else {
                    for (int i = position[1]-1; i >= position[1]-n; i--) {
                        if (park[position[0]].charAt(i) == 'X') {
                            isBreaked = true;
                            break;
                        }
                    }
                }
                
                if (isBreaked == false)  {
                    position[1] -= n;
                }
            } else if (op.equals("S")) {
                if (position[0]+n+1 > park.length) {
                    isBreaked = true;
                } else {
                    for (int i = position[0]+1; i < position[0]+n+1; i++) {
                        if (park[i].charAt(position[1]) == 'X') {
                            isBreaked = true;
                            break;
                        }
                    }
                }
                
                if (isBreaked == false)  {
                    position[0] += n;
                }
            } else if (op.equals("N")) {
                if (position[0]-n < 0) {
                    isBreaked = true;
                } else {
                    for (int i = position[0]-1; i >= position[0]-n; i--) {
                        if (park[i].charAt(position[1]) == 'X') {
                            isBreaked = true;
                            break;
                        }
                    }
                }
                
                if (isBreaked == false)  {
                    position[0] -= n;
                }
            }
                
        }
        
        answer = position;
        
        return answer;
    }
}