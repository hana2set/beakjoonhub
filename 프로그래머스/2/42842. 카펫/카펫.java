class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        // 총 갯수 = brown + yellow = a * b
        // 테투리는 한줄 = brown = (a-1) * 2 + (b-1)*2 = 2a + 2b - 4
        
        int sum = brown+yellow;
        // i = 가로, k = 세로
        for (int i = 1; i < sum; i++) {
            if (sum%i == 0) {
                int k = sum/i;
                if (brown == 2*(i + k - 2)) {
                    answer = new int[]{i, k};
                }
            }
        }
        
        
        return answer;
    }
}