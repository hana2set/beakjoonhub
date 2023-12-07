class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {0, 0, 0, 0};
        // x축, y축별 최소,최대 구하면됨
        // (1,0) -> y축이 1.. 문제 이상함 정신나감
        for (int i = 0; i < wallpaper.length; i++) {
            // i - y축
            int sharp_first_index = wallpaper[i].indexOf("#");
            int sharp_last_index = wallpaper[i].lastIndexOf("#");
            if (sharp_first_index == -1) {
                continue;
            }
            
            
            if (answer[1] == 0 && answer[3] == 0) {
                answer[0] = i;
                answer[2] = i+1;
                answer[1] = sharp_first_index;
                answer[3] = sharp_last_index + 1;
            } else {
                if (answer[1] > sharp_first_index) {
                    answer[1] = sharp_first_index;
                }
                
                if (answer[3] < sharp_last_index + 1) {
                    answer[3] = sharp_last_index + 1;
                }
                
                answer[2] = i+1;
            }
            
        }
        
        return answer;
    }
}