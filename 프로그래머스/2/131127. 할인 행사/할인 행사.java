class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        // 원하는 품목 중 열흘간 할인받는 갯수
        int[] discountNumber = new int[number.length];
        
        // 첫날째부터 열흘간 채워넣기
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < want.length; j++) {
                if (want[j].equals(discount[i])) {
                    discountNumber[j]++;
                }
            }
        }
        
        for (int i = 0; i < discount.length; i++) {
            boolean isPossible = true;
            for (int j = 0; j < number.length; j++) {
                if (number[j] > discountNumber[j]) {
                    isPossible = false;
                    break;
                }
            }
            
            if (isPossible) {
                answer++;
            }
            
            
            //할인 추가
            if (discount.length - 10 > i) {
                for (int j = 0; j < want.length; j++) {
                    if (want[j].equals(discount[i+10])) {
                        discountNumber[j]++;
                    }
                }
            }
            
            //할인 삭제
            for (int j = 0; j < want.length; j++) {
                if (want[j].equals(discount[i])) {
                    discountNumber[j]--;
                }
            }
            
        }
        
        return answer;
    }
}