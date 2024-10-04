import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        long A = Long.parseLong(input[0]);
        long B = Long.parseLong(input[1]);
        long C = Long.parseLong(input[2]);

        System.out.println(pow(A, B, C));
    }

    // B를 분할함으로 제곱형태로 계산 -> 연산최소화
    private static long pow(long a, long b, long c) {
        if (b == 0) return 1; //A^0 == 1

        long n = pow(a, b/2, c) % c; //제곱의 제곱형태 먼저 최대한 계산

        if (b%2 == 0) return n*n % c;
        else return (n*n % c) * a % c; //제곱이 아님 -> * a
    }

}
