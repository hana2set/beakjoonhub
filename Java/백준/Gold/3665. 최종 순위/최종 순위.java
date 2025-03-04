import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int testcase = Integer.parseInt(br.readLine());

        // 위상정렬 알고리즘으로 해결
        // 기본 순서가 정렬되어 있음으로, inDegree, edge를 미리 입력함(1등 -> 2,3,4..등)
        // 간선이 주어지면,
        //  1. 진출노드 indegree--, 진입노드 indegree++  (순서가 바뀜으로)
        //  2. 두 노드의 edge값 변경
        // 이후 기본적인 위상정렬 방식으로 진행
        // 1. 큐 안에 두개 이상 -> 우선순위를 가릴 수 없음 -> "?" 출력
        // 2. 큐 안에 값이 없음 -> 정렬 불가능 -> "IMPOSSIBLE"
        // 3. 그 외: 정렬가능

        while (testcase-- > 0) {

            int n = Integer.parseInt(br.readLine());
            String[] rankStr = br.readLine().split(" ");
            int[] inDegree = new int[n+1];
            boolean[][] edges = new boolean[n+1][n+1];
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(rankStr[i]);
                inDegree[num] = i;

                // edges값 변경
                for (int j = 1; j <= n; j++) {
                    // 앞에서 자신과 연결된 노드가 없음 -> 최우선 노드
                    if (j != num && edges[j][num] == false)
                        edges[num][j] = true;
                }
            }

            //순서가 바뀐 경우 입력
            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                String[] edgeStr = br.readLine().split(" ");
                int a = Integer.parseInt(edgeStr[0]);
                int b = Integer.parseInt(edgeStr[1]);

                if (edges[a][b] == false) {
                    // a 진출 b 진입 으로 변경
                    inDegree[a]--;
                    inDegree[b]++;

                    edges[a][b] = true;
                    edges[b][a] = false;
                } else {
                    // b 진출 a 진입 으로 변경
                    inDegree[b]--;
                    inDegree[a]++;

                    edges[b][a] = true;
                    edges[a][b] = false;
                }

            }


            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) q.add(i);
            }

            StringBuffer qsb = new StringBuffer();
            // n 횟수만큼 반복,
            for (int i = 0; i < n; i++) {

                if (q.isEmpty()) {
                    qsb.setLength(0);
                    qsb.append("IMPOSSIBLE");
                    break;
                }

                if (q.size() > 1) {
                    qsb.setLength(0);
                    qsb.append("?");
                    break;
                }

                int num = q.poll();
                qsb.append(num).append(" ");

                for (int j = 1; j <= n; j++) {
                    if (edges[num][j] == false) continue;

                    edges[num][j] = false; //반복 방지
                    if (--inDegree[j] == 0) q.add(j);
                }

            }
            sb.append(qsb).append("\n");

        }

        System.out.println(sb);

    }

}
