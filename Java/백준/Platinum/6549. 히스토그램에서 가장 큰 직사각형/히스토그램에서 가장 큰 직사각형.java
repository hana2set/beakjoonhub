import java.io.*;
import java.util.Arrays;

class Main {
    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // 초기화
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = input[0];

            if (N == 0) break;

            // 스택방식이 더 효율적이다고 함
            // 분할정복 방식
            // 재귀적으로 좌,우 분할해서 각각의 최대 크기, 합쳤을때 최대 크기를 구함
            System.out.println(getMaxArea(1, N));
        }
    }

    private static long getMaxArea(int left, int right) {
        if (left == right) return input[left]; // 높이 반환

        int mid = (left+right) / 2;

        long leftArea = getMaxArea(left, mid);
        long rightArea = getMaxArea(mid+1, right);
        long sumArea = getSumArea(left, right, mid);

        return Math.max(Math.max(leftArea, rightArea), sumArea);
    }

    private static long getSumArea(int leftBound, int rightBound, int mid) {
        // mid 인덱스부터 확장하면서 최대크기 계산
        // 확장방식 -> 높이가 높은 순으로 확장 -> mid가 포함한 가장 큰 직사각형 계산가능
        int left = mid;
        int right = mid;
        long minHeight = input[mid];
        long max = minHeight; // 높이 * 너비 1

        while (left >= leftBound && right <= rightBound) {
            if (left == leftBound && right == rightBound) break;

            if (right < rightBound
                    && (left == leftBound || input[left-1] <= input[right+1])) {
                right++;
                minHeight = Math.min(minHeight, input[right]);
                max = Math.max(max, minHeight*(right-left+1));
            } else { //right == rightBound || input[left-1] > input[input+1]
                left--;
                minHeight = Math.min(minHeight, input[left]);
                max = Math.max(max, minHeight*(right-left+1));
            }

        }

        return max;
    }


}
