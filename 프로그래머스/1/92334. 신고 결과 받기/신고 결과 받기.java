import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] reports, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> reportMap = new HashMap<>();
        
        for (String report : reports) {
            String[] target = report.split(" ");
            if (reportMap.get(target[1]) == null) {
                reportMap.put(target[1], new HashSet());
            }
            reportMap.get(target[1]).add(target[0]);
        }
        
        Map<String, Integer> resultCountMap = new HashMap<>();
        for (String key : reportMap.keySet()) {
            if (reportMap.get(key).size() >= k) {
                for (String id : reportMap.get(key)) {
                    resultCountMap.put(id, resultCountMap.get(id) != null ? resultCountMap.get(id)+1 : 1);
                }
            }
        }
        
        for (int i = 0; i < id_list.length; i++) {
            if (resultCountMap.get(id_list[i]) != null) {
                answer[i] = resultCountMap.get(id_list[i]);
            }
        }
        
        return answer;
    }
}