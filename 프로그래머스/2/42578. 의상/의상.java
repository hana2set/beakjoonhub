import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        // 같은거 없음 -> 의상의 종류 계산 -> 곱연산
        Map<String, Integer> cates = new HashMap<>();
        
        for (String[] cloth : clothes) {
            if (cates.get(cloth[1]) == null) {
                cates.put(cloth[1], 1);
            } else {
                cates.put(cloth[1], cates.get(cloth[1])+1);
            }
        }
        
        // (0개 또는 한개 선택 조합)
        for (String cate : cates.keySet()) {
            answer *= (cates.get(cate) + 1);
        }
        
        // - 모두 벗은경우(1)
        answer--;
        
        return answer;
    }
}