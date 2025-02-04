import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //CCW를 통한 교차 계산

        String[] l1 = br.readLine().split(" ");
        long x1 = Long.parseLong(l1[0]);
        long y1 = Long.parseLong(l1[1]);
        long x2 = Long.parseLong(l1[2]);
        long y2 = Long.parseLong(l1[3]);

        String[] l2 = br.readLine().split(" ");
        long x3 = Long.parseLong(l2[0]);
        long y3 = Long.parseLong(l2[1]);
        long x4 = Long.parseLong(l2[2]);
        long y4 = Long.parseLong(l2[3]);

        // 0 = 세점이 일직선인 ccw가 존재
        // 1 = p3, p4가 p1-p2 선분 기준으로 같은 방향
        // -1 = p3, p4가 p1-p2 선분 기준으로 다른 방향

        long ccwp1p2 = ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4);
        long ccwp3p4 = ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2);

        // 일직선인경우
        if (ccwp1p2 == 0 && ccwp3p4 == 0) {
            // x,y값이 p1 <= (p3 or p4) <= p2 여야함
            if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2)
                    && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2))
                System.out.println(1);
            else
                System.out.println(0);

            return;
        }

        if ( ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) <= 0
                && ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2) <= 0) System.out.println(1);
        else System.out.println(0);

    }

    private static long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long ccw = (x1*y2+x2*y3+x3*y1) - (x2*y1+x3*y2+x1*y3);

        // ccw 끼리 곱하면 큰숫자가 될 수 있음으로 절삭
        if (ccw < 0) return -1;
        else if (ccw > 0) return 1;
        else return 0;
    }

}