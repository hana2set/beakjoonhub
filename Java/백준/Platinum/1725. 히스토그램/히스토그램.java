import java.io.*;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] seq = new long[N+2]; //양옆에 0 채워서 너비 계산하기
        long max = 0;

        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(br.readLine());
        }

        // 직사각형 넓이는 높이*너비
        // 1. 높이가 같거나 계속 증가하면: 앞쪽 높이로 넓이가 결정됨
        // 2. 작아지면 : 작아진 높이로 넓이가 결정
        // 즉, 올라가면 값 저장,
        // 내려가면 해당 높이에서 최대값을 구하면 됨 (현재높이보다 큰 최소값까지 길이)

        Stack<Integer> s = new Stack<>();
        s.push(0);

        for (int i = 1; i < seq.length; i++) {
            // 올라갈때만 저장함으로, 높이 기준은 앞의 값 (스택의 값)이 됨.
            while (!s.isEmpty() && seq[s.peek()] > seq[i]) {
                int val = s.pop();
                max = Math.max(seq[val] * (i-s.peek()-1), max);
            } 

            s.push(i);
        }

        System.out.println(max);

    }

}
