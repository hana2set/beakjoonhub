import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int number = 666;

        while (true) {
            if (Integer.toString(number).contains("666")) N--;
            if (N == 0) break;

            number++;
        }

        System.out.println(number);

    }
}