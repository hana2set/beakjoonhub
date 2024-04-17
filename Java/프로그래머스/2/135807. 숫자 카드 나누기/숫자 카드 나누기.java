import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int limit = (int) Math.sqrt(100_000_000);
        
        for (int i = limit; i > 0; i--) {
            boolean isADevided = arrayA[0] % i == 0;
            boolean isBDevided = arrayB[0] % i == 0;
            
            if (isADevided == isBDevided) {
                continue;
            }
            
            System.out.println("i = " + i);
            
            boolean isAOk = true;
            boolean isBOk = true;
            
            for (int j = 1; j < arrayA.length; j++) {
                if ( (arrayA[j] % i == 0) != isADevided) {
                    // System.out.println("A fail :" + arrayA[j]);
                    isAOk = false;
                    break;
                }
            }
            
            if (isAOk == false) {
                continue;
            }
            
            for (int j = 1; j < arrayB.length; j++) {
                if ( (arrayB[j] % i == 0) != isBDevided) {
                    // System.out.println("B fail : " + arrayB[j]);
                    isBOk = false;
                    break;
                }
            }
            
            if (isBOk == false) {
                continue;
            }
            
            // System.out.println("i : " + i);
            return i;
        }
        
        return 0;
    }
}