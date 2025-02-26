import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 트리구조 생성
        Map<String, Map> tree = new HashMap<>();
        tree.put("root", new HashMap<>());

        while (N-- > 0) {
            String[] food = br.readLine().split(" ");
            int cnt = Integer.parseInt(food[0]);

            Map<String, Map> curMap = tree.get("root");
            for (int i = 1; i <= cnt; i++) {
                curMap.computeIfAbsent(food[i], k -> new HashMap<>());
                curMap = curMap.get(food[i]);
            }
        }

        makeSb(tree.get("root"), 0);

        // 트리 출력력
        System.out.println(sb);



    }

    private static void makeSb(Map<String, Map> tree, int index) {
        String[] list = tree.keySet().toArray(String[]::new);
        Arrays.sort(list);

        for (int i = 0; i < list.length; i++) {
            sb.append("--".repeat(index)).append(list[i]).append("\n");
            if (tree.get(list[i]) != null) {
                makeSb(tree.get(list[i]), index+1);
            }
        }
    }
}