class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        // 초기위치 0, 양의값만 있음 -> 최대값까지 왔다가면됨
        int delIndex = n;
        int pickIndex = n;
        
        for (int i = n-1; i >= 0; i--) {
            if (delIndex == n && deliveries[i] > 0) {
                delIndex = i;
            }
            
            if (pickIndex == n && pickups[i] > 0) {
                pickIndex = i;
            }
            
            if (delIndex < n && pickIndex < n) {
                break;
            }
        }
        
        if (delIndex == n) {
            delIndex = -1;
        }
        if (pickIndex == n) {
            pickIndex = -1;
        }
        
        int test = 0;
        while (pickIndex >= 0 || delIndex >= 0) {
            // 최대사거리 이동
            answer += delIndex > pickIndex ? (delIndex+1)*2 : (pickIndex+1)*2;
            
            // 트럭 초기화
            int remainCap = cap;
            
            for (int i = delIndex; i >= 0; i--) {
                if (deliveries[i] > 0) {
                    if (remainCap >= deliveries[i]) {
                        remainCap -= deliveries[i];
                    } else {
                        deliveries[i] -= remainCap;
                        remainCap = 0;
                        break;
                    }
                }
                
                delIndex--;
                continue;
            }

            // 정확한 갯수 가져왔다고 가정
            remainCap = 0;
            
            for (int i = pickIndex; i >= 0; i--) {
                if (pickups[i] > 0) {
                    if (remainCap + pickups[i] > cap) {
                        pickups[i] -= cap - remainCap;
                        break;
                    } else {
                        remainCap += pickups[i];
                    }
                }
                
                pickIndex--;
                continue;
            }
            
            
        }
        
        return answer;
    }
}