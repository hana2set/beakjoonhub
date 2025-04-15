import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 직선이고, 정방향인경우, M까지 가는 경로에서 무조건 내려줄 수 있고,
        // 역방향인 경우 겹치지 않으면 3번 지나야함
        // 따라서 정방향 -> 무시, 역방향 -> 겹친 그룹끼리 묶고 (최대-최소)*2를 더함
        // 위 방법을 위해, 역방향은 모아서 순서대로 정렬해, 수직선을 감싸는 영역만 구해서 *2를 더하면 된다.

        String[] info = br.readLine().split(" ");
        long N = Long.parseLong(info[0]);
        long M = Long.parseLong(info[1]);

        List<long[]> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] data = br.readLine().split(" ");
            long a = Long.parseLong(data[0]);
            long b = Long.parseLong(data[1]);

            if (a > b) arr.add(new long[]{b, a});
        }

        arr.sort((a, b) -> {
            if (a[0] == b[0]) return Long.compare(b[1], a[1]);
            else return Long.compare(a[0], b[0]);
        });

        long result = M;
        long max = 0;
        if (!arr.isEmpty()) {
            max = arr.get(0)[0];
        }
        for (long[] cur : arr) {
            long start = cur[0];
            long end = cur[1];

            if (max < start) result += (end - start) * 2;
            else if (max < end) result += (end - max) * 2;
            else continue;

            max = end;
        }

        System.out.println(result);
    }

}