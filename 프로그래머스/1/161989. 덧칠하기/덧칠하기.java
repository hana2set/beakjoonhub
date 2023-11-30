class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int last_number = 0;
        for (int num : section) {
            if (last_number > num) {
                continue;
            }
            
            
            last_number = num + m;
            answer++;
            
        }
        
        return answer;
    }
}