class Solution {
    
    public int solution(String word) {
        int answer = 0;
        // 각 자리 변경시 변경시 경우의 수 = 
        //      남은자리수 보다 작은 자리수로 만들 수 있는 경우의 수 + 자릿수
        // (해당 자릿수 A 배열에서 해당 문자열로 변경하는 경우의 수 + 자릿수까지 가는 방법 )
        // ex) AAAAX -> AAABX = 남은자리수 1개 = 5 + 1 (0자리 수)
        // ex) AAAXX -> AABXX = 남은자리수 2개 = 25 + 5 + 1
        
        char[] dic = new char[]{'A', 'E', 'I', 'O', 'U'};
        int[] digitCount = new int[]{1+5+25+125+625, 1+5+25+125, 1+5+25, 1+5, 1};
        char[] words = word.toCharArray();
        
        for (int i = 0; i < words.length; i++) {
            answer++;
            int index = 0;
            for (int j = 0; j < dic.length; j++) {
                if (dic[j] == words[i]) {
                    index = j;
                }
            }
            answer += index * digitCount[i];
            
        }
                
        return answer;
    }
}