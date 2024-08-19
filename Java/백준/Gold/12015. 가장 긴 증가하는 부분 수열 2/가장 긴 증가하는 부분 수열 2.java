import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {

        // LIS 알고리즘
        // DP 방식은 시간초과임으로
        // 이진 탐색 방식으로 해결
        // - 최장 부분수열 길이만 구할 때 유효한 방식
        // - 가장 뒤 숫자가 작을수록 최장길이수열을 만드는게 유리함을 이용함
        // 방법 : 다음과 같은 방법으로 부분수열 생성
        // 1. 가장 뒷 자리 숫자보다 크면 맨뒤 수열 추가
        // 2. 가장 뒷자리 숫자보다 작으면, 앞쪽에서 해당 숫자보다 큰 인덱스 값을 대체 (Lower_Bound)
        // - 이런 식으로 구성할 경우, 해당 수열의 길이가 최장 길이 수열이 됨.

        // 예를들어서, 특정 인덱스까지 만들 수 있는 부분수열이
        // [1, 2, 5, 7, 8]
        // [1, 2, 3, 4]
        // 두 가지와 같다면, 위 방식을 통해 두 수열을 합쳐져서
        // [1, 2, 3, 4, 8]로 만듬 (인덱스별 최소값)
        // 이런식으로 만들면, 다음값이 8 이상일 경우,
        // [1, 2, 5, 7, 8, 9] 와 같은 부분 수열,
        // 8 이하 일경우
        // [1, 2, 3, 4, 6] 같은 부분 수열을 만들 가능성이 생김.
        // 즉, 이 방식이 "모든 수열을 합친 증가 배열"이 만들어짐으로, 최장길이수열과 길이가 같음.

        // 대체할 값을 찾는 알고리즘이 이진탐색 (logN) 임으로, (NlogN의 시간복잡도를 가짐)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> list = new ArrayList<>();
        list.add(seq[0]);
        
        for (int i = 0; i < N; i++) {
            int lastVal = list.get(list.size()-1);
            if (lastVal < seq[i]) {
                list.add(seq[i]);
            } else if (seq[i] < lastVal) {
                list.set(getReplaceIndex(list, seq[i]), seq[i]);
            }
        }

        System.out.println(list.size());

    }

    private static int getReplaceIndex(List<Integer> list, int value) {
        int start = 0;
        int end = list.size()-1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (list.get(mid) < value) {
                start = mid + 1;
            } else if (value < list.get(mid)) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return start;
    }


}