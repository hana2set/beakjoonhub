import java.io.*;
import java.util.*;

class Main {

    static int k, n;
    static List<Integer>[] graph;

    // 타잔 알고리즘
    static int[] id;
    static int[] sccNum;
    static boolean[] finished;
    static int index, sccIndex;
    static Stack<Integer> s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 두개 이상이 정답이어야함 -> 하나가 틀릴경우 나머지 정답
        // graph의 1~k는 빨간색, k+1~2k 는 파란색이어야 하는 경우를 담을 예정
        // scc로 묶고 모순이 없으면 가능.
        String[] data = br.readLine().split(" ");
        k = Integer.parseInt(data[0]);
        n = Integer.parseInt(data[1]);
        graph = new List[2*k + 1];


        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        while (n-- > 0) {
            String[] vote = br.readLine().split(" ");
            int a = Integer.parseInt(vote[0]) + (vote[1].equals("R") ? 0 : k);
            int b = Integer.parseInt(vote[2]) + (vote[3].equals("R") ? 0 : k);
            int c = Integer.parseInt(vote[4]) + (vote[5].equals("R") ? 0 : k);

            graph[getNeg(a)].add(b);
            graph[getNeg(a)].add(c);
            graph[getNeg(b)].add(a);
            graph[getNeg(b)].add(c);
            graph[getNeg(c)].add(a);
            graph[getNeg(c)].add(b);
        }

        makeScc();

        System.out.println(getColorList());
    }

    private static int getNeg(int val) {
        if (val > k) return val-k;
        return val+k;
    }

    private static void makeScc() {
        // scc 변수 초기화
        index = 0;
        sccIndex = 1;
        id = new int[2*k+1];
        finished = new boolean[2*k+1];
        sccNum = new int[2*k+1];
        s = new Stack<>();

        for (int i = 1; i <= 2*k; i++) {
            if (!finished[i]) {
                scc(i);
            }
        }
    }

    private static int scc(int cur) {
        id[cur] = ++index;
        s.add(cur);

        int parent = id[cur];
        for (int next : graph[cur]) {
            if (id[next] == 0)
                parent = Math.min(scc(next), parent);
            else if (!finished[next])
                parent = Math.min(id[next], parent);
        }

        if (parent == id[cur]) {
            while (!s.isEmpty()) {
                int p = s.pop();
                finished[p] = true;
                sccNum[p] = sccIndex;

                if (p == cur)
                    break;
            }

            sccIndex++;
        }

        return parent;
    }


    private static String getColorList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= k; i++) {
            // scc가 모순이 있는지 체크. (R,B가 같은 SCC에 존재)
            if (sccNum[i] == sccNum[i+k]) return "-1";
            else if (sccNum[i] < sccNum[i+k]) sb.append("R");
            else sb.append("B");
        }

        return sb.toString();
    }

}