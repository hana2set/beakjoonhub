import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        // long xMax = -1000L;
        // long xMin = 1000L;
        // long yMax = -1000L;
        // long yMin = 1000L;
        long xMin = Long.MAX_VALUE;
        long yMin = Long.MAX_VALUE;
        long xMax = Long.MIN_VALUE;
        long yMax = Long.MIN_VALUE;
        
        // y, List<x>
        Map<Long, List<Long>> pointsMap = new HashMap<>();
        
        for (int i = 0; i < line.length-1; i++) {
            for (int j = i; j < line.length; j++) {
                long a1 = line[i][0];
                long b1 = line[i][1];
                long c1 = line[i][2];
                long a2 = line[j][0];
                long b2 = line[j][1];
                long c2 = line[j][2];
                
                // a1*b2 == a2*b1인 경우 두 직선은 평행 또는 일치 (일치는 조건으로 없음)
                long d = a1*b2 - b1*a2;
                
                if (d == 0L) continue;
                    
                // x = (b1*c2 - b2*c1) / (a1*b2 - a2*b1)
                // y = (a2*c1 - a1*c2) / (a1*b2 - a2*b1)
                long x = b1*c2 - c1*b2;
                long y = c1*a2 - a1*c2;

                //해가 정수가 아니면
                if (x%d != 0L || y%d != 0L) continue;
                
                x /= d;
                y /= d;
                
                xMax = Math.max(xMax, x);
                xMin = Math.min(xMin, x);
                yMax = Math.max(yMax, y);
                yMin = Math.min(yMin, y);
                
                if(!pointsMap.containsKey(y)) {
                    pointsMap.put(y, new ArrayList<>());
                }

                pointsMap.get(y).add(x);
                
            }
        }
        
        List<String> board = new ArrayList<>();
        
        int index = 0;
        for (long i = yMax; i >= yMin; i--) {
            StringBuilder sb = new StringBuilder();
            List<Long> points = pointsMap.getOrDefault(i, new ArrayList<>());
            
            // sb.append(".".repeat((int)(xMax - xMin + 1)));
            // for (Long point : points) {
            //     sb.setCharAt((int)(point - xMin), '*');
            // }
            
            for(long j=xMin; j<=xMax; j++) {
                if(points.contains(j)) {
                    sb.append("*");
                }else {
                    sb.append(".");
                }
            }
            
            board.add(sb.toString());
        }
        
        String[] answer = new String[board.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = board.get(i);
        }
        
        return answer;
    }
}