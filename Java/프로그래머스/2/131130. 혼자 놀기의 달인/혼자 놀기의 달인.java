import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        
        boolean[] visit = new boolean[cards.length];
        
        int cardsIndex = 0;
        int loopIndex = 0;
        int loopLength = 1;
        
        List<Integer> loopSize = new ArrayList<>();
        
        // cards 의 루프 길이가 최대, 두번째 인 값을 구해서 곱하면됨
        while(cardsIndex < cards.length) {
            if (visit[loopIndex] == true) {
                loopIndex = ++cardsIndex;
                continue;
            }
            
            visit[loopIndex] = true;
            
            if (visit[cards[loopIndex]-1] == false) {
                loopIndex = cards[loopIndex]-1;
                loopLength++;
            } else {
                loopSize.add(loopLength);
                loopLength = 1;
                loopIndex = ++cardsIndex;
            }
        }
        
        if (loopSize.size() == 1) {
            return 0;
        }
        
        Collections.sort(loopSize);
        
        answer = loopSize.get(loopSize.size() - 1) * (loopSize.get(loopSize.size() - 2));
        
        return answer;
    }
    
    public boolean isNotVisit() {
        return false;
    }
}