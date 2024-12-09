import java.io.*;
import java.util.*;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1부터 시작해서 dp 채우기
        // 시간복잡도를 줄이기 위해 (수열 대체 위치 검색을 이진탐색으로),
        // 원소를 추가하면서 원소의 위치를 나타낼 수 있는 LIS 수열을 만듬
        // (하나씩 추가하며 가장 작은 값이 해당 인덱스를 대체한 수열)
        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];
        List<Integer> list = new ArrayList<>();

        list.add(seq[0]);

        for (int i = 1; i < N; i++) {

            if (seq[i] > list.get(list.size()-1)) {
                list.add(seq[i]);
                dp[i] = list.size()-1;
                continue;
            }

            // 이진탐색
            // 원소보다 큰 배열의 첫번째 위치
            int l = 0;
            int r = list.size()-1;
            while (l < r) {
                int mid = (l+r)/2;

                if (list.get(mid) < seq[i]) l = mid+1;
                else r = mid;
            }

            list.set(r, seq[i]);
            dp[i] = r;

        }

        int arrIndex = list.size()-1;
        Stack<Integer> stack = new Stack<>();
        for (int i = N-1; i >= 0; i--) {
            if (dp[i] == arrIndex) {
                stack.add(seq[i]);
                arrIndex--;
                continue;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(list.size());
        System.out.println(sb.toString().trim());
    }


}