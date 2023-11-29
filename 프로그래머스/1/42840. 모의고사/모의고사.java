import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        
        int[] per1 = {1,2,3,4,5};
        int[] per2 = {2,1,2,3,2,4,2,5};
        int[] per3 = {3,3,1,1,2,2,4,4,5,5};
        
        int[] answer_cnt = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
            if (per1[i%per1.length] == answers[i]) { answer_cnt[0]++; }
            if (per2[i%per2.length] == answers[i]) { answer_cnt[1]++; }
            if (per3[i%per3.length] == answers[i]) { answer_cnt[2]++; }
        }
        
        int per1_cnt = answer_cnt[0];
        int per2_cnt = answer_cnt[1];
        int per3_cnt = answer_cnt[2];
        
        Arrays.sort(answer_cnt);
        List<Integer> a = new ArrayList();
        if (per1_cnt == answer_cnt[2]) { a.add(1); }
        if (per2_cnt == answer_cnt[2]) { a.add(2); }
        if (per3_cnt == answer_cnt[2]) { a.add(3); }
        
        
        int[] answer = a.stream().mapToInt(i->i).toArray();
        return answer;
    }
}