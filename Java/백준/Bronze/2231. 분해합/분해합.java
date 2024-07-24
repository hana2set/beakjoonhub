import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int index = 1;
        while (true) {
            index++;

            int sum = index;
            sum += Arrays.stream(String.valueOf(index)
                    .split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();

            if (sum == N) {
                break;
            }
            if (index > N) {
                index = 0;
                break;
            }
        }

        System.out.println(index);

    }
}