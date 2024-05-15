class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        // 대칭이고 반복된 형태로 구성됨
        // 2번 (2번 11011, 00000, 2번 11011), 5(00000), 2번 (2번 11011, 00000, 2번 11011)...
        // 5로 나눠서, 나머지로 값 판단 (나머지가 3이면 0, 아니면 나누기 n-1번 반복)
        // 5진법 -> 3있으면 제외
        
        for (long i = l; i <= r; i++) {
            if (isValueOne(i, n)) answer++;
        }
        
        return answer;
    }
    
    private boolean isValueOne(long value, int n) {
        long remain = 0;
        String rad5 = Long.toString(value-1, 5);
        
        // 몫이 3 자리 
        if (rad5.indexOf("2") >= 0) {
            return false;
        }
        
        return true;
        
    }
}