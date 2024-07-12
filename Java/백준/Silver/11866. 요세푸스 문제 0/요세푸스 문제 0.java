import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int K = sc.nextInt();

        sb.append("<");

        boolean[] visit = new boolean[N];
        int index = 0;
        int count = N;
        while (count-- > 0) {
            // 인덱스 계산
            int loopCount = K;
            while (loopCount > 0) {
                index = (index + 1) % N;
                if (visit[index] != true) loopCount--;
            }

            visit[index] = true;
            sb.append(index == 0 ? N : index);

            if (count != 0) sb.append(", ");
        }

        sb.append(">");

        System.out.println(sb.toString());
    }
}