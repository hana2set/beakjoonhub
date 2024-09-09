import java.io.*;

class Main {

    static int fibCount = 0;
    static int fibonacciCount = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        fib(N);
        fibonacci(N);

        // 첫 값은 1을 반환할 때까지 루프를 탐 -> 피보나치 수와 같음
        System.out.print(fibCount);
        System.out.print(" ");
        System.out.print(fibonacciCount);
    }

    private static int fib(int n) {
        if (n == 1 || n == 2) {
            fibCount++;
            return 1;
        }
        else return fib(n - 1) + fib(n - 2);
    }

    private static int fibonacci(int n) {
        int[] f = new int[n+1];
        f[1] = f[2] = 1;

        for (int i = 3; i <= n; i++) {
            fibonacciCount++;
            f[i] = f[i-1] + f[i-2];
        }

        return f[n];
    }
}
