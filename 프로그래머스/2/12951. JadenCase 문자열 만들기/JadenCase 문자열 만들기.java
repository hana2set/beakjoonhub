class Solution {
    public String solution(String s) {
        String answer = "";
        
        s = s.toLowerCase();
        char[] sArray = s.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        
        boolean existSpaceBefore = true;
        for (char word : sArray) {
            if (word == ' ') {
                existSpaceBefore = true;
                sb.append(word);
                continue;
            }
            
            if (existSpaceBefore == true && word >= 'a' && word <= 'z') {
                sb.append((char)(word - ('a' - 'A')));
            } else {
                sb.append(word);
            }
                existSpaceBefore = false;
        }
        
        answer = sb.toString();
        
        return answer;
    }
}