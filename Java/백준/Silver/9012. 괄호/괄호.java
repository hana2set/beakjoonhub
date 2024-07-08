import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String vps = sc.next();

            char[] charArr = vps.toCharArray();
            String answer = "YES";

            int psCount = 0;
            for (char ch : charArr) {
                if (ch == '(') psCount++;
                if (ch == ')') psCount--;

                if (psCount < 0) {
                    answer = "NO";
                    continue;
                }
            }

            if (answer.equals("YES") && psCount > 0) answer = "NO";

            System.out.println(answer);
        }

    }
}