import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[] answer = {};
        
        List<List<Integer>> frmd = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            frmd.add(new ArrayList<>());
        }
        
        int k = n;
        int count = 1;
        int arrIndex = 0;
        
        while (k > 0) {
            int start = count;
            if (n%3 == k%3) {
                for (int i = start; i < start+k; i++) {
                    frmd.get(arrIndex++).add(n/3 - k/3, count++);
                }
                
                arrIndex--;
            }
            
            if (n%3 == (k+1)%3) {
                for (int i = start; i < start+k; i++) {
                    frmd.get(arrIndex).add(n/3 - (k+1)/3 + 1 + i - start, count++);
                }
            }
            
            if (n%3 == (k+2)%3) {
                for (int i = start; i < start+k; i++) {
                    frmd.get(--arrIndex).add(frmd.get(arrIndex).size() - (n/3 - (k+2)/3),count++);
                }
                
                arrIndex++;
            }
            
            k--;
        }
        
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            resultList.addAll(frmd.get(i));
        }
        
        
        return resultList.stream().mapToInt(i -> i).toArray();
        
//       다른사람풀이
//         -> n by n matrix에서 좌표로 하나씩 직접 입력
//         -> 순서대로 출력
        
//         int[] answer = new int[(n*(n+1))/2];
//         int[][] matrix = new int[n][n];

//         int x = -1, y = 0;
//         int num = 1;

//         for (int i = 0; i < n; ++i) {
//             for (int j = i; j < n; ++j) {
//                 if (i % 3 == 0) {
//                     ++x;
//                 } else if (i % 3 == 1) {
//                     ++y;
//                 } else if (i % 3 == 2) {
//                     --x;
//                     --y;
//                 }
//                 matrix[x][y] = num++;
//             }
//         }

//         int k = 0;
//         for(int i = 0; i < n; ++i) {
//             for(int j = 0; j < n; ++j) {
//                 if(matrix[i][j] == 0) break;
//                 answer[k++] = matrix[i][j];
//             }
//         }

    }
}