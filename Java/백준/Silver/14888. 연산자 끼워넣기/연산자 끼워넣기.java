import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

class Main {

    static int N;
    static int[] numbers;
    static int[] opers;
    static BiFunction<Integer, Integer, Integer>[] operators = new BiFunction[4];

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        opers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        operators[0] = (p1, p2) -> p1 + p2;
        operators[1] = (p1, p2) -> p1 - p2;
        operators[2] = (p1, p2) -> p1 * p2;
        operators[3] = (p1, p2) -> p1 / p2;

        dfs(0, numbers[0]);
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int index, int value) {
        if (index == N-1) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        for (int i = 0; i < opers.length; i++) {
            if (opers[i] <= 0) continue;

            opers[i]--;
            dfs(index+1, calc(value, numbers[index+1], operators[i] ));
            opers[i]++;

        }
    }

    private static int calc(int num1, int num2, BiFunction<Integer, Integer, Integer> fuc) {
        return fuc.apply(num1, num2);
    }
}
