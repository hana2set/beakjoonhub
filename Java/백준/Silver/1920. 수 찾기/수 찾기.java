import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        int N = Integer.parseInt(br.readLine());
        Integer[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

        int M = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // set
        Set<Integer> set = new HashSet<>(Arrays.asList(seq));


        for (int i = 0; i < M; i++) {
            System.out.println(set.contains(input[i]) ? 1 : 0);
        }

    }

}
