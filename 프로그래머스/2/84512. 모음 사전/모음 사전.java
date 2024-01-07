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


// 다른사람 풀이1
// class Solution {
//     public int solution(String word) {
//         int answer = 0, per = 3905;
//         for(String s : word.split("")) answer += "AEIOU".indexOf(s) * (per /= 5) + 1;
//         return answer;
//     }
// }
// per : 모든 낱말의 갯수 => 시그마 5^n
// 각 자리 사이에 시그마 5^n개의 문자열이 있음.
// 나누기(/)연산으로 시그마 5^(n-1) 찾음
// +1로 자기자신 계산


// 다른사람 풀이2
// import java.util.*;
// class Solution {
//     List<String> list = new ArrayList<>();
//     void dfs(String str, int len) {
//         if(len > 5) return;
//         list.add(str);
//         for(int i = 0; i < 5; i++) dfs(str + "AEIOU".charAt(i), len + 1);
//     }
//     public int solution(String word) {
//         dfs("", 0);
//         return list.indexOf(word);
//     }
// }
// 문자열 하나씩 더하면서 사전 완성
// 인덱스 계산


