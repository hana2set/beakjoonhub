class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    int checknum = nums[i] + nums[j] + nums[k];
                    boolean isFound = true;
                    
                    for (int l = 2; l < checknum ; l++) {
                        if (checknum % l == 0) {
                            isFound = false;
                            break;
                        }
                    }
                    
                    if (isFound) {
                        answer++;
                    }
                }    
            }   
        }
        

        return answer;
    }
}