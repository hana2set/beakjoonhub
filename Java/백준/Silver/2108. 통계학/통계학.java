import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long sum = 0;
        // 최빈값 계산
        int modeCount = 0;
        int modeValue = arr[0];
        boolean isSecondMode = false;
        int refeat = 1;
        for (int i = 0; i < N; i++) {
            sum += arr[i];

            // 값이 같으면 최빈값 횟수 추가
            if (i!=N-1 && arr[i] == arr[i+1]) {
                refeat++;
                continue;
            }

            //값이 다르면 최빈값 초기화
            if (modeCount < refeat) {
                isSecondMode = false;
                modeValue = arr[i];
                modeCount = refeat;
            } else if (isSecondMode == false && modeCount == refeat) {
                isSecondMode = true;
                modeValue = arr[i];
                modeCount = refeat;
            }

            refeat = 1;

        }

//        산술평균 : N개의 수들의 합을 N으로 나눈 값. 소수점 이하 첫째 자리에서 반올림한 값
        System.out.println(Math.round((double)sum/N));

//        중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값. N은 홀수라고 가정됨
        System.out.println(arr[N/2]);

//        최빈값 : N개의 수들 중 가장 많이 나타나는 값
        System.out.println(modeValue);

//        범위 : N개의 수들 중 최댓값과 최솟값의 차이
        System.out.println(arr[N-1] - arr[0]);

    }

}
