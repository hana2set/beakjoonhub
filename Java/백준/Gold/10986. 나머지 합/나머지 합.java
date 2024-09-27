import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        long[] seq = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        // i,j별 구간합을 구해야 함으로, 모든 누적합을 미리 구해야함
        // 그런데 i,j를 전체를 구하게 되면 시간복잡도가 O(N^2) -> 1초안에 풀 수 없음
        // 나머지가 0이 되면 됨으로, 각 합의 나머지 갯수룰 구해서, 나머지 갯수가 같은 i, j를 선택하는 경우의 수를 구하면 됨.
        // (나머지가 같으면, 누적합 빼기 계산에서 나머지가 0이 됨으로)
        long[] remain = new long[M];
        long sum = 0; // 1에서 i까지의 합 (을 M으로 나눈 나머지)
        long count = 0;

        for (int i = 0; i < N; i++) {
            sum += seq[i] % M;
            int index = (int) (sum % M);

            // sum이 0일 경우, 조합과 관계없이 혼자서 count+1
            if (index == 0) count++;

            // 그런데 굳이 조합으로 계산하지 않아도 하나씩 더하면 선택하는 총 경우의 수임.
            // 왜냐면 (현재 나온 j와 조합이 가능한 수) = (현재 나온 j) * (기존에 있던 i)의 갯수
            //      -> (기존에 있던 i)의 갯수 ==> remain[sum]
            count += remain[index];

            remain[index]++;
        }

        System.out.println(count);
    }

}
