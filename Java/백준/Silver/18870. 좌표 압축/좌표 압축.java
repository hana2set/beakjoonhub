import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sortArr = Arrays.copyOf(arr, arr.length);

        Arrays.sort(sortArr);

        Map<Integer, Integer> map = new HashMap<>();

        int index = 0;
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(sortArr[i])) map.put(sortArr[i], index++);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map.get(arr[i]) + " ");
        }

        System.out.println(sb.toString());
    }
}