import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(cards);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int left = 0;
            int right = cards.length-1;

            while (left < right) {
                int mid = (left + right)/2;

                if (cards[mid] == arr[i]) {
                    left = mid;
                    right = mid;
                } else if (cards[mid] < arr[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (cards[right] == arr[i]) sb.append(1 + " ");
            else sb.append(0 + " ");

        }

        System.out.println(sb.toString());
    }
}