import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        // 포화 이진트리 -> 표현가능하려면 자리수가 고정
        // 1, 1+2, 1+2+4 ... -> 2^n-1 자리수로 표현되어야함
        // 자식이 1이면 부모도 1 
        // -> 1. 자리수가 2^n-1인지 확인 
        // -> 2. 1이 나올경우, 순회하면서 루트까지 1인지 확인
                
        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            
            answer[i] = getAnswerInt(number);
        }        
        
        return answer;
    }
    
    public int getAnswerInt(long number) {
        
        String binary = Long.toString(number, 2);
        int checkValue = binary.length();
        
        // 자리수 확인
        int nodeLevel = 0;
        while (true) {
            if (checkValue == 0) {
                break;
            }
            nodeLevel++;
            checkValue /= 2;
        }
        
        if (nodeLevel < 2) {
            return 1;
        }
        
        // 1이 true인 배열 생성
        boolean[] oneArray = new boolean[(int) Math.pow(2, nodeLevel)];
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') oneArray[oneArray.length - binary.length() + i] = true;
        }
        
        // root부터 유효성 체크
        int root = (int) Math.pow(2, nodeLevel-1);
        if (oneArray[root] == false) {
            return 0;
        }
        
        if (validRoot(oneArray, root, nodeLevel, true)) {
            return 1;
        } else {
            return 0;
        }
        
    }
    
    public boolean validRoot(boolean[] oneArray, int index, int level, boolean isOnePossible) {
        
        if (isOnePossible == false && oneArray[index] == true) {
            return false;
        }
        
        if (level == 1) { // 더 내려갈필요 없음
            return true;
        }
        
        if (oneArray[index] == false) {
            isOnePossible = false;
        }
        
        int step = (int) Math.pow(2, level-2);
        
        return validRoot(oneArray, index - step, level-1, isOnePossible)
            && validRoot(oneArray, index + step, level-1, isOnePossible);
        
    }
}