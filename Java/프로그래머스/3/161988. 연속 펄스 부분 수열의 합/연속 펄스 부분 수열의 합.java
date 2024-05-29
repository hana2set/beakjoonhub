import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long max = 0;
        long min = 0;
        long sum = 0;

        // 1. i~j의 합 = (0~j의 합) - (0~i의 합)
        //            = 최대값 - 최소값
        // 2. 두 연속 펄스 합의 절대값은 똑같음 -> 구해서 절대값
        for (int i = 0; i < sequence.length; i++) {
            sum += (long) (sequence[i] * (i % 2 == 0 ? 1 : -1));
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }

        return Math.abs(max - min);
    }
}