import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static Queue<Integer> q = new LinkedList<>();
    static int[] inorder, postorder, rootInorderMapping;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rootInorderMapping = new int[N+1]; // arr[root값] = inorder 배열의 인덱스

        // 프리오더 root L R
        // 인오더 L root R
        // 포스트오더 L R root

        // 1. 포스트오더는 항상 root가 마지막에 옴으로, root를 구할 수 있다.
        // 2. root와 인오더를 통해 root를 중심으로 트리를 좌우로 분할한다.
        // 3. 좌우로 분할한 트리를 기준으로, 1-2를 반복.
        // -> 인오더 인덱스, 포스트오더 인덱스를 반복하여 내리면서 계산. //두 인덱스가 별도임으로, 두 범위 같이 보내기
        // -> root 구하는 법: L R root 임으로, root를 제외하고 좌우로 나눴을때 (inorder로 갯수 계산) L,R 가장 오른쪽에 있는 값


        for (int i = 0; i < N; i++) {
            rootInorderMapping[inorder[i]] = i;
        }

        findPreOrder(0, N-1, 0, N-1);

        for (int i = 0; i < N; i++) {
            sb.append(q.poll()).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    /**
     * fi: inorder 시작인덱스
     * li: inorder 종료인덱스
     * fp: post 시작인덱스
     * lp: post 종료인덱스
    * */
    private static void findPreOrder(int fi, int li, int fp, int lp) {
        if (fp > lp) return;

        // root 더하기 (preorder)
        int root = postorder[lp];
        q.add(root);

        if (fi == li) return; // 리프노드일 경우

        int rootIndex = rootInorderMapping[root];

        // 좌우 갯수 구하기 (postindex 계산용)
        int count = li - fi + 1;
        int rc = count - (rootIndex - fi + 1);
        int lc = count - rc - 1;

        // L
        if (lc > 0) findPreOrder(fi, rootIndex-1, fp, fp + lc -1);

        // R
        if (rc > 0) findPreOrder(rootIndex+1, li, fp + lc, lp - 1);

    }


}
