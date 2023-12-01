import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        char[] X_chars = X.toCharArray();
        char[] Y_chars = Y.toCharArray();
        Arrays.sort(X_chars); 
        Arrays.sort(Y_chars);
        
        List<Character> dupl_chars = new ArrayList<>();
        
        int x_index = 0;
        int y_index = 0;
        while(x_index < X_chars.length && y_index < Y_chars.length) {
            if (X_chars[x_index] < Y_chars[y_index]) {
                x_index++;
            } else if (X_chars[x_index] > Y_chars[y_index]) {
                y_index++;
            } else if (X_chars[x_index] == Y_chars[y_index]) {
                dupl_chars.add(X_chars[x_index]);
                x_index++;
                y_index++;
                continue;
            }
        }
        
        if (dupl_chars.size() == 0) {
            answer = "-1";
        } else if (dupl_chars.get(dupl_chars.size()-1) == '0') {            
            answer = "0";
        } else {
            answer = dupl_chars.stream().sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining());
        }
        
        
        return answer;
    }
}