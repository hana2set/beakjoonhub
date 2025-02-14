import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Set set = new HashSet<Integer>();

        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            String[] input = br.readLine().split(" ");
            String order = input[0];
            int num = input.length == 1 ? 0 : Integer.parseInt(input[1]);

            switch (order) {
                case "add":
                    set.add(num);
                    break;
                case "remove":
                    set.remove(num);
                    break;
                case "check":
                    if (set.contains(num)) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "toggle":
                    if (set.contains(num)) set.remove(num);
                    else set.add(num);
                    break;
                case "all":
                    set = new HashSet(
                            Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20})
                    );
                    break;
                case "empty":
                    set = new HashSet<Integer>();
                    break;
            }

        }

        System.out.println(sb);

    }

}