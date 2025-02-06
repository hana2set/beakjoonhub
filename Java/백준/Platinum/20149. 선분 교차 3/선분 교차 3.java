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

        long ccw123 = ccw(x1, y1, x2, y2, x3, y3);
        long ccw124 = ccw(x1, y1, x2, y2, x4, y4);
        long ccw341 = ccw(x3, y3, x4, y4, x1, y1);
        long ccw342 = ccw(x3, y3, x4, y4, x2, y2);

        // 일직선인경우
        if (ccw123 == 0 && ccw124 == 0 && ccw341 == 0 && ccw341 == 0) {
            // 교차 여부 확인
            if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2)
                    && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2))
                System.out.println(1);
            else
                System.out.println(0);

            // 끝점이 일치하지 않으면 여러점에서 교차 -> 하나일 경우만 따짐
            if (Math.min(x1, x2) == Math.max(x3, x4) && Math.min(y1, y2) == Math.max(y3, y4))
                System.out.println(Math.min(x1, x2) + " " + Math.min(y1, y2));
            else if (Math.min(x1, x2) == Math.max(x3, x4) && Math.max(y1, y2) == Math.min(y3, y4))
                System.out.println(Math.min(x1, x2) + " " + Math.max(y1, y2));
            else if (Math.min(x3, x4) == Math.max(x1, x2) && Math.min(y1, y2) == Math.max(y3, y4))
                System.out.println(Math.min(x3, x4) + " " + Math.min(y1, y2));
            else if (Math.min(x3, x4) == Math.max(x1, x2) && Math.max(y1, y2) == Math.min(y3, y4))
                System.out.println(Math.min(x3, x4) + " " + Math.max(y1, y2));

            return;
        }

        if ( ccw123 * ccw124 <= 0
                && ccw341 * ccw342 <= 0) {
            System.out.println(1);

            // 방정식 y = (y1-y2)/(x1-x2) * (x-x1) + y1과 p3,p4에 대한 연립방정식 풀이.
            double x = 0;
            double y = 0;
            double s12 = Double.valueOf(y1-y2); //p1,2 기울기
            double s34 = Double.valueOf(y3-y4); //p3,4 기울기

            if (x1 == x2) { //p1,p2가 수직
                s34 /= (x3-x4);
                x = x1;
                y = s34 * (x - x3) + y3;
            } else if (x3 == x4) { //p3,p4가 수직
                s12 /= (x1-x2);
                x = x3;
                y = s12 * (x - x1) + y1;
            } else { //일반 교차
                s12 /= (x1-x2);

                x = (x1 * y2 - y1 * x2) * (x3 - x4)  - (x1 - x2) * (x3 * y4 - y3 * x4);
                x /= (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
                y = s12*x - x1*s12 + y1;

            }

            System.out.println(x + " " + y);
        } else {
            System.out.println(0);
        }

    }

    private static long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long ccw = (x1*y2+x2*y3+x3*y1) - (x2*y1+x3*y2+x1*y3);

        // ccw 끼리 곱하면 큰숫자가 될 수 있음으로 절삭
        if (ccw < 0) return -1;
        else if (ccw > 0) return 1;
        else return 0;
    }

}