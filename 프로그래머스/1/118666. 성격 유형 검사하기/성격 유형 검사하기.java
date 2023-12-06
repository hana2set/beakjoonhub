import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        
        map.put("R", 0);
        map.put("T", 0);
        map.put("F", 0);
        map.put("C", 0);
        map.put("M", 0);
        map.put("J", 0);
        map.put("A", 0);
        map.put("N", 0);
        
        for (int i = 0; i < survey.length; i++) {
            if (choices[i] == 4) {
                continue;
            }
            
            String[] words = survey[i].split("");
            
            if (choices[i] < 4) {
                map.put(words[0], map.get(words[0]) + 4 - choices[i]);
            } else {
                map.put(words[1], map.get(words[1]) + choices[i] - 4);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        if (map.get("R") < map.get("T")) {
            sb.append("T");
        } else {
             sb.append("R");
        };
        
        if (map.get("C") < map.get("F")) {
            sb.append("F");
        } else {
             sb.append("C");
        };
        
        if (map.get("J") < map.get("M")) {
            sb.append("M");
        } else {
             sb.append("J");
        };
        
        if (map.get("A") < map.get("N")) {
            sb.append("N");
        } else {
             sb.append("A");
        };
        
        
        answer = sb.toString();
        return answer;
    }
}