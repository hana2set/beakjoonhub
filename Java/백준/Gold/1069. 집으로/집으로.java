import java.io.*;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = data[0];
        int y = data[1];
        int d = data[2];
        int t = data[3];

        double dis = Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
        double jump = (double)d/t;
        double time = Double.MAX_VALUE;

        if ( jump > 1 ) {
            int count = (int) (dis/d);

            if (dis == count*d) {
                time = count*t;
            } else {
                if (count == 0) {
                    double doubleJump = 2*t;
                    double walk = dis;
                    double jumpAndBack = t + d - dis;

                    time = Math.min(time, doubleJump);
                    time = Math.min(time, walk);
                    time = Math.min(time, jumpAndBack);
                } else {
                    double rest = dis - count*d;

                    double addJump = t;
                    double walk = rest;

                    time = Math.min(time, addJump);
                    time = Math.min(time, walk);

                    time += count*t;
                }
            }

        } else {
            time = dis;
        }

        System.out.println(time);

    }

}