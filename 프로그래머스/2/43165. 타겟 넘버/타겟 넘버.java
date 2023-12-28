import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();           
        list.add(numbers[0]);
        list.add(-numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            List<Integer> temp_list = new ArrayList<>();
            for (Integer value : list) {
                temp_list.add(value+numbers[i]);
                temp_list.add(value-numbers[i]);
            }
            list = temp_list;
        }
        
        for (Integer value : list) {
            if (value.equals(target)) {
                answer++;
            }
        }
        
        return answer;
    }
}