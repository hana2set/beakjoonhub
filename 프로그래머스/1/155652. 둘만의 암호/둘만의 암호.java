import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        List<Character> map = new ArrayList<>();
        for (char c = 'a'; c < 'z'+1; c++) {
            if (skip.indexOf(c) == -1) {
                map.add(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        char[] s_arr = s.toCharArray();
        
        for (char c : s_arr) {
            for (int i = 0; i < map.size(); i++) {
                if ( c <= map.get(i)) {
                    sb.append(map.get( (i + index) % map.size() ));
                    break;
                }
            }
        }
        
        answer = sb.toString();
        return answer;
    }
}