class Solution {
    
    
    public int solution(int[] a) {
        
        if (a.length <= 2) {
            return a.length;
        }
        
        // 한쪽에 작은숫자 한개이상 -> 작은 교환 기회 최소 1회 사용
        // 한쪽에 자기보다 작은 숫자가 몰려있으면 가능 
        // -> 양쪽에서 i 기준 한쪽이 큰 숫자만 있는 경우 모두 체크 (전체 최소값까지)
        
        int minIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[minIndex] > a[i]) {
                minIndex = i;
            }
        }
        
        int leftMin = a[0];
        int rightMin = a[a.length-1];
        int answer = 3; // 좌우 끝, 최소값은 무조건 가능
        
        //중복제거
        if (minIndex == 0 || minIndex == a.length-1) {
            answer--; 
        }
        
        // 좌측에 자신보다 작은 수가 없음
        for (int i = 1; i < minIndex; i++) {
            if (a[i] < leftMin) {
                leftMin = a[i];
                answer++;
                
            }
        }
        
        // 우측에 자신보다 작은 수가 없음
        for (int i = a.length-1; i > minIndex; i--) {
            if (a[i] < rightMin) {
                rightMin = a[i];
                answer++;
            }
        }
        
        return answer;
    }
    
}