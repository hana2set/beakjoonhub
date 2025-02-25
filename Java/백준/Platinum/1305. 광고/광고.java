import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();

        // kmp 테이블을 만들고 가장 뒤 접미사를 빼버리면됨. ( 반복이 시작되어 짤리는 부분 )

        int[] table = new int[L];

        int idx = 0;
        for (int i = 1; i < L; i++) {
            // 일치하다가(idx>0) 불일치 부분이 생기면
            while (idx > 0 && str.charAt(i) != str.charAt(idx)) {
                idx = table[idx-1]; //접두사에서 일치하는 부분까지 인덱스 회귀
            }

            // 일치하기 시작하면 번호++
            if (str.charAt(i) == str.charAt(idx)) {
                table[i] = ++idx;
            }
        }

        // table 마지막 인덱스 -> 최후미 반복 시작 접미사
        System.out.println(L - table[L-1]);

    }
}