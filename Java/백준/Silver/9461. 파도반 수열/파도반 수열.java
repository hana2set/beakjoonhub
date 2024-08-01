import java.util.*;

class Main {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        long[] p = new long[101];

        p[1] = 1;
        p[2] = 1;
        p[3] = 1;
        p[4] = 2;
        p[5] = 2;

        int index = 5;
        while (++index <= 100) {
            p[index] = p[index-1] + p[index-5];
        }


        int T = sc.nextInt();

        while(T-- > 0) {
            int N = sc.nextInt();
            System.out.println(p[N]);
        }
    }

}