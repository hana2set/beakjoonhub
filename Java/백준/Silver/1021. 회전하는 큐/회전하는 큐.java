import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        List<Integer> putList = new ArrayList<>();

        int index = 1;
        int move = 0;
        while (M-- > 0) {
            int num = sc.nextInt();

            int left = num > index ? index : num;
            int right = num > index ? num : index;

            int innerMove = right-left;
            int outerMove = N-(right-left);

            for (int i = 0; i < putList.size(); i++) {
                if (putList.get(i) < left || right < putList.get(i)) outerMove--;
                else innerMove--;
            }

            move += Math.min(innerMove, outerMove);

            index = num+1;
            
            while(putList.contains(index)) {
                index++;
                if (index > N) index = 1;
            }

            // 뽑은 숫자 모아두기
            putList.add(num);

        }

        System.out.println(move);
    }
}