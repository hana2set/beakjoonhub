import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        List<Integer> line = new ArrayList<>();
        List<Double> sum = new ArrayList<>();
        
        while(true) {
            line.add(k);
            if (k % 2 == 0) {
                k = k/2;
            } else {
                k = k*3 + 1;
            }
            
            if (line.size() > 0) {
                sum.add( (k+line.get(line.size()-1)) / 2.0 );
            }
            
            if (k == 1) {
                break;
            }
        }
        line.add(k); 
        
        int n = line.size();
        
        for (int i = 0; i < ranges.length; i++) {
            ranges[i][1] += n;
            
            if (ranges[i][0] >= ranges[i][1]) {
                answer[i] = -1.0;
                continue;
            }
            
            for (int j = ranges[i][0]; j < ranges[i][1]-1; j++) {
                answer[i] += sum.get(j);
            }
        }
        
        return answer;
    }
}