class Solution {
    public int solution(int storey) {
        int answer = 0;
        int val = storey;
        
        while (val > 0) {
            
            // 5층 -> -1*5, 6층 -> +1*4 -10*1
            if (val % 10 > 5) {
                answer += 10 - val%10;
                val += 10 - val%10;
                
            // 45층 -> -5가 효율적(9), 55층 -> +5가 효율적(10)
            } else if (val % 10 == 5 && val % 100 > 50) {
                answer += 10 - val%10;
                val += 10 - val%10;
            } 
            
            answer += val%10;
            val /= 10;
        }
        
        
        return answer;
    }
}