import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        // 갹 i 갯수 구함
        int[] cnt = new int[a.length];
        for (int i = 0; i < a.length; i++){
            cnt[a[i]]++;
        }
        
        for (int i = 0; i < cnt.length; i++){
            // 특정숫자가 적어도 답보다 많아야 수열이 길어짐 -> cnt[i] 비교
            if (cnt[i] <= answer) continue;
            
            int ans = 0;
            for(int j = 0; j < a.length-1; j++){
                // 스타 수열 조건
                if(a[j] != a[j+1] && (i == a[j] || i == a[j+1])){
                    ans++;
                    j++;
                }
            }
            
            answer = Math.max(answer, ans);
        }
        
        return answer*2;
    }
}
