import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        String[] exp = input.split("-");

        // 마이너스 뒤 플러스를 모두 묶으면 최소값
        int sum = 0;
        for (int i = 0; i < exp.length; i++) {
            if (exp[i].equals("")) continue;

            String[] sumExp = exp[i].split("\\+");

            int tempSum = 0;
            for (int j = 0; j < sumExp.length; j++) {
                tempSum += Integer.parseInt(sumExp[j]);
            }

            if (i == 0) sum += tempSum;
            else sum -= tempSum;

        }

        System.out.println(sum);

    }
}