import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String T = br.readLine();
        String P = br.readLine();

        int[] table = makeTable(P);

        int begin = 0;
        int matched = 0;
        int cnt = 0;
        while (begin <= T.length() - P.length()) {
            // 문자열 일치
            if (matched < P.length() && T.charAt(begin+matched) == P.charAt(matched)) {
                matched++;

                // 문자열 번호가 마지막일 경우우
               if (matched == P.length()) {
                    sb.append(begin+1).append(" ");
                    cnt++;
                }

            // 문자열이 일치하지 않는 경우
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    // 일치한 문자열이 있을 경우
                    // 미리 만들어둔 번호로 건너뛰기
                    begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }

            }
        }

        System.out.println(cnt);
        System.out.println(sb);

    }

    private static int[] makeTable(String P) {
        int[] table = new int[P.length()];
        int idx = 0;

        for (int i = 1; i < P.length(); i++) {
            while (idx > 0 && P.charAt(i) != P.charAt(idx)) {
                idx = table[idx-1];
            }

            if (P.charAt(i) == P.charAt(idx)) {
                table[i] = ++idx;
            }

        }


        return table;
    }

}