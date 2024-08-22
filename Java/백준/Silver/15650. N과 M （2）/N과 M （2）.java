import java.io.*;

class Main {

    static boolean[] valid;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sc = br.readLine().split(" ");
        N = Integer.parseInt(sc[0]);
        M = Integer.parseInt(sc[1]);

        // N개중 M개를 골라 출력한 수열과 같음
        // dfs로 입력 순환하면서 출력
        valid = new boolean[N+1];

        dfs(1, 0);
    }

    private static void dfs(int index, int count) {
        if (count == M) {
            printSeq();
            return;
        }

        if (index > N) return; //인덱스 초과시 종료

        valid[index] = true;
        dfs(index+1, count+1);
        valid[index] = false;
        dfs(index+1, count);
    }

    private static void printSeq() {
        for (int i = 1; i <= N; i++) {
            if (valid[i] == true) System.out.print(i + " ");
        }

        System.out.println();
    }


}