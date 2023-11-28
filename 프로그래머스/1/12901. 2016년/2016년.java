class Solution {
    public String solution(int a, int b) {
        String answer = "";
        int[] dayCount = {31,29,31,30,31,30,31,31,30,31,30,31};
        String[] yo1 = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
        int totalday = 0;
        for (int i = 0; i < a-1; i++) {
            totalday += dayCount[i];
        }
        totalday += b+3;
        int remainday = totalday % 7;
        
        answer = yo1[remainday];
        
        return answer;
    }
}