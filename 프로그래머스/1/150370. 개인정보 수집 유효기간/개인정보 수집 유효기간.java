import java.util.*;

class Solution {
    public List solution(String today, String[] terms, String[] privacies) {
        
        List<Integer> exprired = new ArrayList<>();
        
        Map<String, Integer> map = new HashMap<>();
        int[] todayArr = Arrays.stream(today.split("\\.")).mapToInt(v->Integer.parseInt(v)).toArray();
        
        for (String term : terms) {
            String[] _term = term.split(" ");
            map.put(_term[0], Integer.parseInt(_term[1]));
        }
            
        
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            int[] date = Arrays.stream(privacy[0].split("\\.")).mapToInt(v->Integer.parseInt(v)).toArray();
            Integer privacyTerm = map.get(privacy[1]);
            
            int[] expireDay = new int[3];
            expireDay[0] = date[0];
            expireDay[1] = date[1] + privacyTerm;
            expireDay[2] = date[2];
            
            // 달
            if (expireDay[1] > 12) {
                expireDay[0] += expireDay[1] / 12;
                expireDay[1] = expireDay[1] % 12;
                if (expireDay[1] == 0) {
                    expireDay[0]--;
                    expireDay[1] = 12;
                }
            }
            
            System.out.println(expireDay[0] + ":" + expireDay[1] + ":" + expireDay[2]);
            
            if (expireDay[0] < todayArr[0]) {
                exprired.add(i+1);
            } else if (expireDay[0] == todayArr[0]) {
                if (expireDay[1] < todayArr[1]) {
                    exprired.add(i+1);                    
                } else if (expireDay[1] == todayArr[1]) {
                    if (expireDay[2] <= todayArr[2]) {
                        exprired.add(i+1); 
                    }
                }
            }
        }
        
        return exprired;
    }
}