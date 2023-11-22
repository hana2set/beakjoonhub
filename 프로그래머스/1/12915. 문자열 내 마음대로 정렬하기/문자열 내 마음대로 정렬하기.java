import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = (String[]) Arrays.stream(strings).sorted().sorted(
            (e1,e2) -> e1.toCharArray()[n] - e2.toCharArray()[n]
        ).toArray(String[]::new);
        
        return answer;
    }
}