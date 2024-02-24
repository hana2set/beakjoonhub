import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        //큰숫자부터 -> 스트링정렬
        
        String[] numsStringArr = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            numsStringArr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(numsStringArr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < numsStringArr.length; i++) {
            String num = numsStringArr[i];
            if (sb.length() == 0 && num.equals("0")) {
                continue;
            }
            sb.append(num);
        }
        
        
        if (sb.length() == 0) {
            return "0";
        }
        
        
        answer = sb.toString();
        
        return answer;
    }
}