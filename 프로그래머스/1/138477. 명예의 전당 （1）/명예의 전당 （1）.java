import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        List<Integer> sortList = new ArrayList<>();
        
        int maxValue = 0;
        for (int i = 0; i < score.length; i++) {
            sortList.add(score[i]);
            Collections.sort(sortList, Comparator.reverseOrder());
            if (i < k) {
                answer[i] = sortList.get(i);
            } else {
                answer[i] = sortList.get(k-1);
            }
        }
        return answer;
    }
}