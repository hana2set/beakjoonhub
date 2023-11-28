class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        int cards1_index = 0;
        int cards2_index = 0;
        int goal_index = 0;
        for (int i = 0; i < goal.length; i++) {
            String value = goal[i];
            boolean passed = true;
            
            //card1
            if (cards1.length > cards1_index && cards1[cards1_index].equals(value)) {
                cards1_index++;
                passed = false;
                continue;
            }
            
            if (!passed) {
                continue;
            }
            
            //card2
            if (cards2.length > cards2_index && cards2[cards2_index].equals(value)) {
                cards2_index++;
                passed = false;
                continue;
            }
            
            if (!passed) {
                continue;
            }
            
            answer = "No";
            break;
        }
        
        return answer;
    }
}