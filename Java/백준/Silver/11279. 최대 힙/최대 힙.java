import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue(Collections.reverseOrder());

        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                bw.write(String.valueOf(q.isEmpty() ? 0 : q.poll()));
                bw.write("\n");
            } else {
                q.add(num);
            }
        }

        bw.flush();
        bw.close();
    }
}