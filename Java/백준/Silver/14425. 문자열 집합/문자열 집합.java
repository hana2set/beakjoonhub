import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);
        int result = 0;

        // trie로 풀이
        Node trie = new Node();

        while (N-- > 0) {
            trie.insert(br.readLine());
        }

        while (M-- > 0) {
            if (trie.isExist(br.readLine())) result++;
        }

        System.out.println(result);
    }

}

class Node {
    Node[] child = new Node[26];
    boolean isEnd;

    public void insert(String str) {
        char[] data = str.toCharArray();
        Node cur = this;
        for (char c : data) {
            int index = c-'a';

            if (cur.child[index] == null) {
                cur.child[index] = new Node();
            }
            cur = cur.child[index];
        }

        // 문자열 마지막 표시
        cur.isEnd = true;
    }

    public boolean isExist(String str) {
        char[] data = str.toCharArray();
        Node cur = this;
        for (char c : data) {
            int index = c-'a';
            if (cur.child[index] == null) return false;

            cur = cur.child[c-'a'];
        }

        return cur.isEnd;
    }
}
