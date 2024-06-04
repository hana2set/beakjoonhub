import java.util.*;


class Solution {
    private class Person {
        
        public int index;
        public int p1;
        public int p2;
        public int sumScore;
        
        public Person (int index, int p1, int p2) {
            this.index = index;
            this.p1 = p1;
            this.p2 = p2;
            this.sumScore = p1+p2;
        }
        
        public String toString() {
            return this.index + " : " + this.sumScore;
        }
        
    }
    
    public int solution(int[][] scores) {
//         int answer = 0;
        
//         List<Person> list = new ArrayList<>();
        
//         for (int i = 0; i < scores.length; i++) {
//             list.add(new Person(i, scores[i][0], scores[i][1]));
//         }
        
//         list.sort((e1, e2) -> {
//             if (e2.sumScore == e1.sumScore) {
//                 return e1.index - e2.index;
//             }
//             return e2.sumScore - e1.sumScore;
//         });
        
//         int skipCount = 0;
//         for (int i = 0; i < list.size(); i++) {
//             boolean isSkip = false;
//             for (int j = 0; j < i; j++) {
//                 if (list.get(j).p1 > list.get(i).p1
//                    && list.get(j).p2 > list.get(i).p2) {
//                     skipCount++;
//                     isSkip = true;
//                     break;
//                 }
//             }
            
        
//             if (list.get(i).index == 0) {
//                 if (isSkip == true) {
//                     return -1;
//                 }
                
//                 return i-skipCount+1;
//             }
//         }
        
//         return -1;
        
        
        
        
        // 다른사람 답안
        int[] s = scores[0];
        // 앞숫자 내림차순, 뒷숫자 오름차순
        Arrays.sort(scores, (s1, s2) -> s1[0] == s2[0] ? s1[1] - s2[1] : s2[0] - s1[0]);
        ArrayList<Integer> arr = new ArrayList<>();
        int max = 0, ans = 1;
        // 내림차순임으로 앞숫자는 항상 큼으로, 앞에서부터 뒷숫자의 max를 구하면
        // max만 비교하면 됨(max 보다 작을 경우 해당 score보다 두점수가 큰 경우가 무조건 있음.)
        for(int[] score : scores) {
            if(score[1] < max) {
                if(score.equals(s))
                    return -1;
            }
            else {
                max = Math.max(max, score[1]);
                if(s[0] + s[1] < score[0] + score[1])
                    ans++;
            }
        }
        return ans;
        
    }
}