import java.io.*;

class Main {

    static long mod = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        String input = br.readLine();
        long N = Long.parseLong(input);


        // 재귀 -> 시간초과
        // dp -> 공간초과 (hashMap은 가능함)

        // 따라서 -> 행렬로 계산산 (https//st-lab.tistory.com/252)
        // | F(n)    | = | 1 1 | * | F(n-1) F(n-2) |
        // | F(n-1)  |   | 1 0 |   | F(n)   F(n-1) |
        //
        // 여기서 {1 1}, {1 0) 을 A라고하면,
        // A = | 1 1 | = | F(2) F(1) |
        //     | 1 0 |   | F(1) F(0) |
        // 임으로, F(n)을 행렬 A의 제곱으로 표현하는 것이 가능.
        // -> A^n = | F(n+1) F(n) |
        //          | F(n) F(n-1) |

        System.out.println(fibonacci(N));
    }

    private static long fibonacci(long n) {
        // A^n-1 까지만 계산하면 됨
        n--;

        long[][] A = {{1, 1}, {1, 0}};
        long[][] result = {{1, 0}, {0, 1}};

        // 행렬의 제곱 지수로 계산
        while (n > 0) {
            if (n % 2 == 1) {
                result = matrixMod(A, result);
            }
            A = matrixMod(A, A);
            n /= 2;
        }

        return result[0][0];
    }

    private static long[][] matrixMod(long[][] A, long[][] B) {
        long[][] result = {{0, 0}, {0, 0}};
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[i].length; j++) {

                for (int k = 0; k < A[i].length; k++) {
                    result[i][j] += A[i][k] * B[k][j] % mod;
                    result[i][j] %= mod;
                }
            }
        }

        return result;
    }

}
