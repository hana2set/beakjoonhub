import java.io.*;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String exStr = br.readLine();
        char[] arr = input.toCharArray();
        char[] exArr = exStr.toCharArray();

        // replace는 할때마다 String 할당 -> 100만자리 스트링 메모리 초과
        // stack으로 추가하는 방식으로 해결
        Stack<Character> st = new Stack<>();

        for (char c : arr) {

            st.add(c);

            if (st.size() < exArr.length) continue;

            boolean isMatched = true;
            for (int i = 0; i < exArr.length; i++) {
                if (st.get(st.size() - i - 1) == exArr[exArr.length - i - 1]) continue;

                isMatched = false;
                break;
            }

            if (isMatched == true) {
                for (char t : exArr) st.pop();
                continue;
            }


        }

        StringBuilder sb = new StringBuilder();

        if (st.size() == 0) sb.append("FRULA");
        else for (char c : st) sb.append(c);

        System.out.println(sb);

    }

}
