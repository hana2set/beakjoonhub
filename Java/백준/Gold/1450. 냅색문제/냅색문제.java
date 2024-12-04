import java.io.*;
import java.util.*;

class Main {

    static int N, C;
    static int[] seq;
    static List<Integer>
            left = new ArrayList<>(),
            right = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 완전탐색시 시간 초과 O(2^N)
        // 두 그룹으로 나누어서 계산 시 가능 - O(2^N/2)
        // 각 두 그룹에서 "합의 배열"를 미리 계산해, "양쪽의 합이 C보다 낮은 경우의 수"를 계산함
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        int count = 0;
        seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 두 그룹으로 완전탐색
        combi(left, 0, N/2, 0);
        combi(right, N/2, N, 0);

        // 정렬해서, 합이 최대인 인덱스 계산
        Collections.sort(left);
        Collections.sort(right);

        for (int i = 0; i < left.size(); i++) {
            // 합이 C보다 작거나 같으면 갯수 추가
            // -> 상한 인덱스를 합계에 더함
            int sameValueIndex = upperBound(left, left.get(i));

            count += (upperBound(right, C - left.get(i))+1) * (sameValueIndex-i+1); // 합이 C 이하인 최대 인덱스 계산

            i = sameValueIndex;
        }


        System.out.println(count);
    }

    private static int upperBound(List<Integer> list, int value) {
        int l = 0, r = list.size()-1;

        while (l <= r) {
            int mid = (l + r)/2;

            if (list.get(mid) > value) r = mid-1;
            else l = mid+1;
        }

        return r;
    }

    private static void combi(List<Integer> list, int s, int e, int sum) {
        if (sum > C) return;
        if (s == e) {
            list.add(sum);
            return;
        }

        // s를 추가하거나, 빼는 경우 탐색
        combi(list, s+1, e, sum);
        combi(list, s+1, e, sum + seq[s]);
    }



}
