import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Main {
    // 최단경로 -> BFS
    public static Queue<Integer> q = new LinkedList<>();
    public static int[] dp;
    public static int[] before; // 이전 명령


    // 연산 순서 찾기용
    public static Stack<String> s = new Stack<>();
    public static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringBuilder sb = new StringBuilder();
            String[] input = br.readLine().split(" ");

            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            dp = new int[10001];
            before = new int[10001]; // 이전 명령
            q = new LinkedList<>();
            s = new Stack<>();

            q.add(A);

            while (!q.isEmpty()) {
                int num = q.poll();
                if (num == B) {
                    break;
                }

                addQueue(num, D(num));
                addQueue(num, S(num));
                addQueue(num, L(num));
                addQueue(num, R(num));
            }

            index = B;
            while (index != A) {
                int bef = before[index];

                if (addStack(bef, D(bef), "D")) continue;
                if (addStack(bef, S(bef), "S")) continue;
                if (addStack(bef, L(bef), "L")) continue;
                if (addStack(bef, R(bef), "R")) continue;

            }

            // 출력
            while (!s.isEmpty()) {
                sb.append(s.pop());
            }

            System.out.println(sb);

        }
    }

    public static void addQueue(int bef, int aft) {
        if (dp[aft] == 0) {
            dp[aft] = dp[bef]+1;
            before[aft] = bef;
            q.add(aft);
        }
    }

    public static boolean addStack(int bef, int aft, String method) {
        if (aft == index) {
            index = bef;
            s.push(method);
            return true;
        }

        return false;
    }


    public static int D(int val) {
        return (val*2) % 10000;
    }

    public static int S (int val) {
        return (val+9999) % 10000;
    }

    public static int L (int val) {
        return (val*10) % 10000 + (val/1000);
    }

    public static int R (int val) {
        return (val/10) + (val%10) * 1000;
    }

}
