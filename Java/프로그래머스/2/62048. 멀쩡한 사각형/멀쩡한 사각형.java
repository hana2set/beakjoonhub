class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        
//         for (long i = 1; i <= w; i++) {
            
//             answer += h - (long)(1.0 * i * h / w) + (long) (1.0 * (i-1) * h / w);
//             if (i * 1.0 * h / w != (long) (i * 1.0 * h / w)) {
//                 answer -= 1;
//             }
//         }
        
        // 정석풀이 : w,h의 최소공배수 마다 패턴이 반복됨
        
        // h가 길다고 가정하고 반복 영역안에서 좌측으로 밀어보면, 튀어나오는 부분은 (너비 - 1)개 만큼 있음. (패턴안에서는 항상튀어나옴)
        // 좌측에는 (높이)만큼 있음으로, 반복패턴안의 값 = w/gcd + h/gcd -1
        // w/gcd가 gcd 만큼 반복중 -> (w/gcd + h/gcd -1) * gcd = w + h - gcd
        
        long gcd = gcd(w, h);
        answer = (long)w * (long)h - ((long)w + (long)h - gcd);

        return answer;
    }
    
    
    private long gcd(int w, int h){

        if(w == 0) return h;
        return gcd(h % w, w);

    }
    
}