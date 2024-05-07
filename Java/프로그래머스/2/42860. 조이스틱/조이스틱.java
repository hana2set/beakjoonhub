import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        char[] nameArray = name.toCharArray();
        
        // 이동횟수 + 알파벳 조작 횟수
        answer = findMinMoveCount(nameArray) + findChangeCount(nameArray);
        
        return answer;
    }
    
    private int findMinMoveCount(char[] nameArray) {
        // A인경우, 조작하지 않아도 됨으로 이동횟수 아낄 수 있음.
        // 한번 밖에 아낄수없음으로 (해당지점 도착시 반대로 이동) 각 지점에서 최소값 구함
        int minMoveCount = nameArray.length - 1; // 쭉 이동시 기본값
        
        int index = 1; //첫값은 움직일일이 없음
        int aCount = 0;
        
        while(index < nameArray.length) {
            if (nameArray[index] == 'A') {
                aCount++;
                index++;
                if (index == nameArray.length) {
                    minMoveCount = Math.min(minMoveCount, index - aCount - 1); // 앞
                    break;
                } else {
                    continue;
                }
            }
            
            // a가 있을 경우, 연속된 a문자 앞뒤에서 최소갯수 구함
            // index = A문자열 뒷 인덱스
            //앞 = (index-aCount-1) * 2 + (nameArray.length - index)
            //뒤 = (nameArray.length - index)*2 + (index-aCount-1)
            if (aCount > 0) {
                minMoveCount = Math.min(minMoveCount, nameArray.length + index - 2*aCount - 2); // 앞
                minMoveCount = Math.min(minMoveCount, 2 * nameArray.length - index - aCount - 1); // 뒤 
                aCount = 0;
            }
            
            index++;
        }
        
        return minMoveCount;
        
    }
    
    
    private int findChangeCount(char[] nameArray) {
        int totalCount = 0;
        
        //26자 -> 13 보다 작은 수
        for (char name : nameArray) {
            if (name - 'A' > 12) {
                totalCount += 'Z' - name + 1;
            } else {
                totalCount += name - 'A';
            }
        }
        
        return totalCount;
        
    }
}