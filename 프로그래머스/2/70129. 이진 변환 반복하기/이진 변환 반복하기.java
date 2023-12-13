class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int delCount = 0;
        while (s != "1" && s.length() > 1) {
            int zeroCount = 0;

            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeroCount++;
                }
            }

            delCount++;
            answer[1] += zeroCount;
            s = Integer.toString(s.length() - zeroCount, 2);
        }
        
        answer[0] = delCount;
        
        return answer;
    }
}