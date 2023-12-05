class Solution {
    public int solution(String s) {
        int answer = 0;
        int first_cnt = 0;
        int other_cnt = 0;
        char[] arr = s.toCharArray();
        
        char first = '0';
        for (char word : arr) {
            if (first == '0') {
                first = word;
                first_cnt++;
                continue;
            }
            
            if (first == word) {
                first_cnt++;
            } else {
                other_cnt++;
            }
            
            if (first_cnt == other_cnt) {
                first = '0';
                first_cnt = 0;
                other_cnt = 0;
                answer++;
            }
            
        }
        
        if (first != '0') {
            answer++;
        }
        
        return answer;
    }
}