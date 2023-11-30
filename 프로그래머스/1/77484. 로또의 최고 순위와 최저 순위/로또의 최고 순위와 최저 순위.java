class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int min_count = 0;
        int zero_count = 0;
        for (int num : lottos) {
            if (num == 0) {
                zero_count++;
                continue;
            }
            
            for (int win_num : win_nums) {
                if (win_num == num) {
                    min_count++;
                    continue;
                }
            }
        }
        
        int[] answer = new int[2];
        
        switch(min_count) {
                case 6: answer[1] = 1; break;
                case 5: answer[1] = 2; break;
                case 4: answer[1] = 3; break;
                case 3: answer[1] = 4; break;
                case 2: answer[1] = 5; break;
                default: answer[1] = 6; break;
        }
        
        switch(min_count + zero_count) {
                case 6: answer[0] = 1; break;
                case 5: answer[0] = 2; break;
                case 4: answer[0] = 3; break;
                case 3: answer[0] = 4; break;
                case 2: answer[0] = 5; break;
                case 1: answer[0] = 6; break;
                case 0: answer[0] = 6; break;
                default: answer[0] = 1; break;
        }
        
        
        
        return answer;
    }
    
}