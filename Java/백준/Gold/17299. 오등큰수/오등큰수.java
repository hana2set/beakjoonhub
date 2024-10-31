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
        int[] count = new int[1_000_001];

        for (int num : seq) count[num]++; // 횟수 추가

        Stack<Integer> st = new Stack<>();
        int[] result = new int[N];

        // 스택에 수를 추가하면서 이전 수에 비해 등장횟수가
        // 1. 늘어나면 : 해당 수가 오등큰수, 해당 수 보다 작아질때 까지 반환하면됨
        // 2. 같거나 작으면 : 오등큰수 등장까지 계속 입력, 없으면 -1 반환
        for (int i = 0; i < seq.length; i++) {
            int val = seq[i];

            while (!st.isEmpty() && count[val] > count[seq[st.peek()]]) {
                result[st.pop()] = val;
            }

            st.add(i);
        }

        while (!st.isEmpty()) {
            result[st.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();

        for (int val :result) {
            sb.append(val).append(" ");
        }

        System.out.println(sb.toString().trim());

    }

}
