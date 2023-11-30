class Solution {
    String[] dir = {"aya", "ye", "woo", "ma"};
    
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (String babble : babbling) {
            if (cutWord(babble, "").length() == 0)
                answer++;
        }
        
        return answer;
    }
    
    public String cutWord(String babble, String beforeWord) {
        String remainText = babble;
        for (String data : dir) {
            if (beforeWord.equals(data)) {
                continue;
            }
            
            if (babble.indexOf(data) == 0) {
                remainText = cutWord(babble.substring(data.length()), data);
            }
        }
        
        return remainText;
    }
}