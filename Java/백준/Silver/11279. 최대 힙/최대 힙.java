import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        PriorityQueue<Long> q = new PriorityQueue(Collections.reverseOrder());

        while (N-- > 0) {
            long num = sc.nextLong();

            if (num == 0) {
                sb.append(q.isEmpty() ? 0 : q.poll());
                sb.append("\n");
            } else {
                q.add(num);
            }
        }

        System.out.println(sb.toString());
    }
}