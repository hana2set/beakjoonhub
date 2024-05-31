import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, String> referMap = new HashMap<>();
        Map<String, Integer> encomeMap = new HashMap<>();
        
        for (int i = 0; i < enroll.length; i++) {
            referMap.put(enroll[i], referral[i]);
            encomeMap.put(enroll[i], 0);
        }
        
        for (int i = 0; i < seller.length; i++) {
            String sell = seller[i];
            String parent = referMap.get(sell);
            
            int price = amount[i] * 100;
            
            
            while (parent.equals("-") == false && price != 0) {
                int parentPrice = price / 10;
                encomeMap.put(sell, encomeMap.get(sell) + price - parentPrice);
                
                sell = referMap.get(sell);
                parent = referMap.get(sell);
                price = parentPrice;
            }
            
            encomeMap.put(sell, encomeMap.get(sell) + price - price / 10);
        }
        
        // System.out.println(encomeMap);
        
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = encomeMap.get(enroll[i]);
        }
        
        return answer;
    }
}