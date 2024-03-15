import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        char[] numArr = number.toCharArray();
        Stack<Character> stack = new Stack();
        
        // 큰수 만들기
        for (int i = 0; i < numArr.length; i++) {
            if (stack.size() == 0) {
                stack.push(numArr[i]);
            } else if (k > 0 && stack.peek() < numArr[i]) {
                stack.pop();
                k--;
                i--;
            } else {
                stack.push(numArr[i]);
            }
        }
        
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuffer sb = new StringBuffer();
        for (Character ch : stack) 
            sb.append(ch);
        
        return sb.toString();
    }
    
    
}