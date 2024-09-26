import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        char[] chars = str.toCharArray();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String[] input = br.readLine().split(" ");
            char target = input[0].charAt(0);
            int start = Integer.parseInt(input[1]);
            int end = Integer.parseInt(input[2]);
            int count = 0;

            for (int i = start; i <= end; i++) {
                if (target == chars[i]) count++;
            }

            System.out.println(count);
        }

    }

}
