class Solution {
    public long solution(int n) {
        long answer = 0;
        // g(1) -> 1
        // g(2) -> 2
        // g(3) -> 3
        // g(4) -> 5
        // g(5) -> 8 = 5+3
        // g(6) -> 13 = 8+5
        // g(n) -> g(n-1) + g(n-2);
        
        if (n == 1 || n == 2) {
            return n;
        }
        
        long first = 1;
        long second = 2;
        for (int i = 3; i <= n; i++) {
            answer = (first + second) % 1234567;
            first = second;
            second = answer;
        }
        
        return answer;
    }
}