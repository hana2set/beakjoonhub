import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = -1;
        // 올바른 괄호 문자열 안에 올바른 괄호 문자열만 있어야함.
        // 올바른 괄호 문자열이 된 경우, 최외곽 문자열을 기준으로 회전해야만
        // 올바른 괄호 문자열이 생성됨 (아니면 깨짐)
        // 완성1개에서 최외곽 괄호 문자열 갯수 -> 총 갯수
        int outerBracketCnt = 0;
        
        //하나 완성시키기
        int[] bracketCnt = new int[3];
        for (int i = 0; i < s.length(); i++) {
            String checkString = s.substring(i);
            checkString += s.substring(0, i);
            
            //순서 확인
            Stack<Character> stack = new Stack<>();
            bracketCnt = new int[3];
            for (char c : checkString.toCharArray()) {
                // 생성 불가
                if ( c == '}' || c == ']' || c == ')' ){
                    if (stack.size() == 0) {
                        outerBracketCnt = 0;
                        break;
                    }
                    
                    char lastChar = stack.pop();
                    if ( (c == '}' && lastChar != '{')
                        || (c == ']' && lastChar != '[')
                        || (c == ')' && lastChar != '(') ) {
                        outerBracketCnt = 0;
                        break;
                    }
                } else {
                    stack.add(c);
                }
                
                if (stack.size() == 0) {
                    outerBracketCnt++;
                }
            }
            
            if (stack.size() != 0) {
                outerBracketCnt = 0;
            }
            
            // 하나라도 완성됨
            if (outerBracketCnt > 0) {
                break;
            }
        }
        
        
        answer = outerBracketCnt;
        
        return answer;
    }
}