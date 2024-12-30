import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {

    static Map<Integer, List<Integer>> roads;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = 1;

        while (true) {
            String[] info = br.readLine().split(" ");

            int n = Integer.parseInt(info[0]);
            int m = Integer.parseInt(info[1]);

            if (n == 0 && m == 0) break;


            // 경로 초기화
            roads = new HashMap<>();
            visit = new boolean[n+1];
            int count = 0;

            for (int i = 1; i <= n; i++) {
                roads.put(i, new ArrayList<>());
            }

            while (m-- > 0) {
                String[] road = br.readLine().split(" ");

                int s = Integer.parseInt(road[0]);
                int e = Integer.parseInt(road[1]);

                roads.get(s).add(e);
                roads.get(e).add(s);
            }

            for (int i = 1; i <= n; i++) {
                if (visit[i] == false && checkTree(i, 0) == true) count++;
            }

            String resultText = "Case " + caseNum++;
            if (count == 0) resultText += ": No trees.";
            else if (count == 1) resultText += ": There is one tree.";
            else resultText += ": A forest of "+count+" trees.";


            System.out.println(resultText);

        }
    }

    private static boolean checkTree(int s, int before) {
        boolean isGraphTree = true;
        visit[s] = true;

        for (int t : roads.get(s)) {
            if (t != before) {
                if (visit[t] == true) return false; // 사이클 존재

                isGraphTree = isGraphTree && checkTree(t, s);
            }


        }


        return isGraphTree;
    }

}
