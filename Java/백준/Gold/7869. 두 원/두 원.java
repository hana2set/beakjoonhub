import java.io.*;
import java.util.Arrays;

class Main {


    static int[] parent;
    static int[] x1;
    static int[] y1;
    static int[] x2;
    static int[] y2;

    static int[] count; // 선분 갯수
    static int groupCount;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double[] data = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        double x1 = data[0];
        double y1 = data[1];
        double r1 = data[2];
        double x2 = data[3];
        double y2 = data[4];
        double r2 = data[5];

        // 부채꼴에서 삼각형 넓이를 뺀걸 더하면 됨.
        // 부채꼴의 넓이는 r^2 * Θ / 2
        // 삼각형의 넓이는 r^2 * sin(Θ) / 2
        // Θ는 제2 코사인법칙으로 계산 -> 2*Θ = 2*cos^-1( (d^2 + r1^2 - r2^2) / 2dr1 )

        double d = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));

        if (Math.abs(r1-r2) >= d) { // 내부에 포함
            System.out.println( String.format("%.3f", Math.PI * Math.pow(Math.min(r1,r2), 2) ));
        } else if (r1+r2 <= d) { // 접점이 없음
            System.out.println( String.format("%.3f", 0.0 ));
        } else { // 접점 2개
            double theta1 = 2 * Math.acos((d*d + r1*r1 - r2*r2) / (2*d*r1) );
            double theta2 = 2 * Math.acos((d*d + r2*r2 - r1*r1) / (2*d*r2) );

            double w1 = (r1*r1*theta1)/2 - (r1*r1*Math.sin(theta1)/2);
            double w2 = (r2*r2*theta2)/2 - (r2*r2*Math.sin(theta2)/2);

            System.out.println( String.format("%.3f", w1+w2 ));
        }
    }

}