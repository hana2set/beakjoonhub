class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        int remain = 0;
        int total = n;
        int cnt= 0;
        while (total >= a) {
            int receive = (total / a) * b;
            remain = total % a;
            answer += receive;
            total = receive + remain;
            
        }
        
        
        return answer;
    }
}