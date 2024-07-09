import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> s = new Stack();

        while (true) {
            char[] charArr = br.readLine().toCharArray();

            if (charArr.length == 1 && charArr[0] == '.') {
                break;
            }

            String answer = "yes";
            s.clear();

            for (char ch : charArr) {
                if (ch == '(' || ch == '[') {
                    s.push(ch);
                    continue;
                }

                if (ch == ')') {
                    if (s.isEmpty() || s.pop() != '(') {
                        answer = "no";
                        break;
                    }
                }

                if (ch == ']') {
                    if (s.isEmpty() || s.pop() != '[') {
                        answer = "no";
                        break;
                    }
                }
            }

            if (!s.isEmpty()) {
                answer = "no";
            }


            bw.write(answer + "\n");
            bw.flush();
        }

    }
}