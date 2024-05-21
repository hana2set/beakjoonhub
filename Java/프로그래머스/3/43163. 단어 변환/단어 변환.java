class Solution {
    String[] words;
    boolean[] visit;
    int answer;
    
    public int solution(String begin, String target, String[] words) {
        this.words = words;
        this.visit = new boolean[words.length];
        
        answer = 0;
        
        dfs(begin, target, 0);
        
        return answer;
    }
    
    private void dfs(String begin, String target, int n) {
        if (target.equals(begin)) {
            if (answer == 0 || answer > n) {
                answer = n;
            }
            return;
        }
        
        loop1:
        for (int i = 0; i < words.length; i++) {
            if (visit[i] == true) {
                continue;
            }
            String word =  words[i];
            
            char[] wordArr = word.toCharArray();
            char[] beginArr = begin.toCharArray();
            
            int diffCount = 0;
            
            loop2:
            for (int j = 0; j < wordArr.length; j++) {
                if (wordArr[j] != beginArr[j]) diffCount++;
                
                if (diffCount > 1) {
                    continue loop1;   
                }
            }
            
            if (diffCount == 1) {
                
                visit[i] = true;
                dfs(word, target, n+1);
                visit[i] = false;
            }
        }
        
    }
}