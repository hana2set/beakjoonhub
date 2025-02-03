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

        if ( ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) < 0
                && ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2) < 0) System.out.println(1);
        else System.out.println(0);

    }

    private static long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        return (x1*y2+x2*y3+x3*y1) - (x2*y1+x3*y2+x1*y3) < 0 ? 1 : -1; //교차는 없다!
    }

}