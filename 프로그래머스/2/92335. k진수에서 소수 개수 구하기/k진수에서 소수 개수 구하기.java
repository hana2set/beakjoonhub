import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String kIntString = Integer.toString(n, k);
        
        String[] pArray = kIntString.split("0");
        for (String p : pArray) {
            if (p.length() > 0) {                
                
                
                long pValue = Long.parseLong(p);
                
                if (pValue == 0 || pValue == 1) {
                    continue;
                }
                if (pValue == 2) {
                    answer++;
                    continue;
                }
                
                long maxTest = (long) Math.sqrt(pValue);
                
                boolean isPrimary = true;
                for (long i = 2; i <= maxTest; i++) {
                    if (pValue % i == 0) {
                        isPrimary = false;
                        continue;
                    }
                }
                
                if (isPrimary) {
                    answer++;
                }
                
            }
        }
        
        return answer;
    }
}