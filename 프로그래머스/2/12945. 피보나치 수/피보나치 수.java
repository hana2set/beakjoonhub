class Solution {
    public long solution(int n) {
        long answer = 0;
        long first = 0;
        long second = 1;
        for (int i = 0; i < n-1; i++) {
            answer = (first + second) % 1234567;
            first = second % 1234567;
            second = answer % 1234567;
        }
        
        return answer;
    }
}