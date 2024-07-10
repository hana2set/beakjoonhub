import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> s = new Stack<>();

        int index = 1;
        while (N-- > 0) {
            int data = Integer.parseInt(br.readLine());

            while (index <= data) {
                s.push(index);
                index++;
                sb.append("+\n");
            }

            int stackValue = -1;
            while (!s.isEmpty()) {
                stackValue = s.pop();
                sb.append("-\n");

                if (stackValue == data) break;
            }

            if (stackValue < data) {
                sb = new StringBuilder();
                sb.append("NO");
                N = 0;
                break;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}