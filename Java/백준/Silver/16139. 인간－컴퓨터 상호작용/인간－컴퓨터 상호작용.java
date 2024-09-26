import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        char[] chars = str.toCharArray();
        int N = Integer.parseInt(br.readLine());

        // 문제수가 많아지면 직접 계산하는데에 절대적 시간이 길어짐
        // 인덱스 별 알파벳 등장 횟수의 합을 미리 계산해두면 문제수에 따른 답을 빨리 도출 가능
        int[][] count = new int[26][chars.length]; // [알파벳수][문자열길이]

        count[chars[0] - 'a'][0]++;

        for (int i = 1; i < chars.length; i++) {
//            int addNum = chars[i] - 'a';
//            for (int j = i; j < chars.length; j++) {
//                count[addNum][j]++; // 나온 숫자 뒤 전체 +1
//            }

            for (int j = 0; j < 26; j++) {
                count[j][i] = count[j][i-1];
            }

            count[chars[i] - 'a'][i]++;
        }

        while (N-- > 0) {
            String[] input = br.readLine().split(" ");
            int target = input[0].charAt(0) - 'a';
            int start = Integer.parseInt(input[1]);
            int end = Integer.parseInt(input[2]);

            int result = count[target][end];
            if (start != 0) result -= count[target][start-1];

            bw.append(result+"\n");
        }

        bw.close();
        br.close();
    }

}
