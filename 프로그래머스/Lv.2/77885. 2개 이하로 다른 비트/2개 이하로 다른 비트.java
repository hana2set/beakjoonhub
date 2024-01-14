class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        //0이 있으면 가장 뒤 0을 1로바꾸면 큰수들 중 가장작음
        //없으면 01을 10으로 바꿈
        
        for (int i = 0; i < numbers.length; i++) {
            String twoIndexNumber = Long.toString(numbers[i], 2);
            char[] numCharArray = twoIndexNumber.toCharArray();
            int lastZeroIndex = twoIndexNumber.lastIndexOf('0');
            if (lastZeroIndex > -1) {
                numCharArray[lastZeroIndex] = '1';
                int oneIndex = twoIndexNumber.indexOf('1', lastZeroIndex);
                if (oneIndex > -1) {
                    numCharArray[oneIndex] = '0';
                }
                answer[i] = Long.parseLong(String.valueOf(numCharArray), 2);
            } else {
                numCharArray[0] = '0';
                answer[i] = Long.parseLong("1" + String.valueOf(numCharArray), 2);
            };
            
        }
        
        
        return answer;
    }
}