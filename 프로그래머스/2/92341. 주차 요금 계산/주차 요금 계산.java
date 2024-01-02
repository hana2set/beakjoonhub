import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        Map<String, Integer> carMap = new HashMap<>();
        Map<String, Integer> totalTimeMap = new HashMap<>();
        Map<String, Integer> inTimeMap = new HashMap<>();
        
        for (String record : records) {
            String[] recordArr = record.split(" ");
            String carNum = recordArr[1];
            if (carMap.get(carNum) == null) {
                carMap.put(carNum, 0);
                totalTimeMap.put(carNum, 0);
            }
            
            String[] timeArr = recordArr[0].split(":");
            int intime = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
            
            if (recordArr[2].equals("IN")) {
                carMap.put(carNum, 1);
                inTimeMap.put(carNum, intime);
            } else {
                carMap.put(carNum, 0);
                int overtime = intime - inTimeMap.get(carNum);
                totalTimeMap.put(carNum, totalTimeMap.get(carNum) + overtime);
            }
        }
        
        
        
        List<String> keyset = new ArrayList<>(carMap.keySet());
        Collections.sort(keyset);
        
        int[] answer = new int[keyset.size()];
        
        int outtime = 23 * 60 + 59;
        for (int i = 0; i < keyset.size(); i++) {
            String carNum = keyset.get(i);
            if (carMap.get(carNum) == 1) {
                int overtime = outtime - inTimeMap.get(carNum);
                totalTimeMap.put(carNum, totalTimeMap.get(carNum) + overtime);
            }
        }
        
        
        for (int i = 0; i < keyset.size(); i++) {
            String carNum = keyset.get(i);
            int overtime = totalTimeMap.get(carNum);
            if (overtime < fees[0]) {
                answer[i] = fees[1];
            } else {
                int price = fees[1] + ((overtime-fees[0]) / fees[2] + ((overtime-fees[0]) % fees[2] > 0 ? 1 : 0 )) * fees[3];
                answer[i] = price;
            }
        }
        
        return answer;
    }
}