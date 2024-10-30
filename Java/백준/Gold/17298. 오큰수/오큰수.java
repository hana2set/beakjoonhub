import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 스택에 쌓으면서, 마지막 수보다 큰 수가 나올 경우, 작아질떄까지 반환 -> 반환된 모든 값이 오큰수
        Stack<Integer> st = new Stack<>();
        Stack<Integer> indexSt = new Stack<>();
        int[] result = new int[N];

        for (int i = 0; i < seq.length; i++) {
            int val = seq[i];

            while (!st.isEmpty() && val > st.peek()) {
                st.pop();
                result[indexSt.pop()] = val;
            }

            st.add(val);
            indexSt.add(i);
        }

        while (!st.isEmpty()) {
            st.pop();
            result[indexSt.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();

        for (int val :result) {
            sb.append(val).append(" ");
        }

        System.out.println(sb.toString().trim());

    }

}
