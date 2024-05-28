class Solution
{
    
    char[] charArray;
    int answer = 1;
    
    public int solution(String s) {

        charArray = s.toCharArray();

        
        for (int i = 0; i < charArray.length; i++) {
            dfs(i);
        }        

        return answer;
    }
    
    private void dfs(int index) {
        // 뒤부터 일치하는값 있는지 확인
        mainloop:
        for (int i = charArray.length-1; i > index; i--) {
            if (charArray[i] == charArray[index]) {
                
                // 좌우가 같은지
                subloop:
                for (int j = 0; 2*j <= i-index; j++) {
                    if (charArray[index+j] != charArray[i-j]) {
                        continue mainloop;
                    }
                }
                
                // System.out.println("index: " + index);
                // System.out.println("i:" + i);
                
                // index에서 일치하는 가장 긴 문자열
                if (i - index + 1 > answer) answer = i - index + 1;
                return;
            }   
        }
        
        
    }
}