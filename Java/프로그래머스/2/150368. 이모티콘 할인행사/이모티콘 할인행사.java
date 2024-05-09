import java.util.*;

class Solution {
    
    int[][] users;
    int[] emoticons;
    int[] choiceEmoticon;
    int[] answer;
    
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.answer = new int[2];
        this.users = users;
        this.emoticons = emoticons;
        this.choiceEmoticon = new int[emoticons.length];
        
        dfs(0);
        
        return answer;
        
    }
    
    public void dfs(int index) {
        if (index >= emoticons.length) {
            getResult();
            return;
        }
        
        for (int i = 10; i <= 40; i = i+10) {
            choiceEmoticon[index] = i;
            dfs(index+1);
        }
    }
    
    public void getResult() {
        int sum = 0;
        int count = 0;
        
        for (int i = 0; i < users.length; i++) {

            int userSum = 0;
            for (int j = 0; j < choiceEmoticon.length; j++) {
                if ( choiceEmoticon[j] >= users[i][0] ) {
                    userSum += emoticons[j] * (100 - choiceEmoticon[j]) / 100;
                }

                // 기준치 초과 시 서비스 가입
                if (userSum >= users[i][1]) {
                    userSum = 0;
                    count++;
                    break;
                }
            }


            // 기준치 미초과 시 이모티콘 구입
            if (userSum != 0) {
                sum += userSum;
            }
        }
        
        if (count == answer[0]) {
            answer[1] = Math.max(answer[1], sum);
        } else if (count > answer[0]) {
            answer[0] = count;
            answer[1] = sum;
        }
        
        
        // return null;
    }
}