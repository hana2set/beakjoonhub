class Solution {
    public String solution(String s) {
        String answer = "";
        String[] nums = s.split(" ");
        int min = Integer.parseInt(nums[0]), max = Integer.parseInt(nums[0]);
        
        for (String numStr : nums) {
            int num = Integer.parseInt(numStr);
            if (min > num) {
                min = num;
            }
            
            if (max < num) {
                max = num;
            }
        }
        answer = min + " " + max;
        return answer;
    }
}