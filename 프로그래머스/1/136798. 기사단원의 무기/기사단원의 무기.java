class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for (int i = 0; i < number; i++) {
            answer += getWeaponDamage(i+1, limit, power);
        }
        
        return answer;
    }
    
    public int getWeaponDamage(int num, int limit, int power) {
        int count = 0;
        for (int i = 1; i * i <= num; i++) {
            if (i * i == num) count++;
            else if (num % i == 0) count = count+2;
        }
        
        return count <= limit ? count : power;
    }
    
}