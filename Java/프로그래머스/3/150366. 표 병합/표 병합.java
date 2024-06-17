import java.util.*;

class Solution {
    static Node[][] table = new Node[51][51];
    static int uniqueKey = 0;
    
    public ArrayList solution(String[] commands) {
        ArrayList answer = new ArrayList();
        
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                Node node = new Node("", uniqueKey);
                table[i][j] = node;
                uniqueKey += 1;
            }
        }
        
        for (String com : commands) {
            String[] arrCom = com.split(" ");
            if (arrCom[0].equals("UPDATE")) {
                if (arrCom.length == 4) {
                    int r = Integer.parseInt(arrCom[1]);
                    int c = Integer.parseInt(arrCom[2]);
                    updateA(r, c, arrCom[3]);
                } else {
                    updateB(arrCom[1], arrCom[2]);
                }
            } else if (arrCom[0].equals("MERGE")) {
                int r1 = Integer.parseInt(arrCom[1]);
                int c1 = Integer.parseInt(arrCom[2]);
                int r2 = Integer.parseInt(arrCom[3]);
                int c2 = Integer.parseInt(arrCom[4]);
                merge(r1, c1, r2, c2);
            } else if (arrCom[0].equals("UNMERGE")) {
                int r = Integer.parseInt(arrCom[1]);
                int c = Integer.parseInt(arrCom[2]);
                unmerge(r, c);
            } else {
                int r = Integer.parseInt(arrCom[1]);
                int c = Integer.parseInt(arrCom[2]);
                String word = table[r][c].word;
                if (word.equals("")) {
                    word = "EMPTY";
                }
                answer.add(word);
            }
            
        }
        
        return answer;
    }
    
    public void updateA(int si, int sj, String word) {
        int key = table[si][sj].key;
        
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (table[i][j].key == key) {
                    table[i][j].word = word;
                }
            }
        }
        
    }
    
    public void updateB(String a, String b) {
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (table[i][j].word.equals(a)) {
                    table[i][j].word = b;
                }
            }
        }
    }
    
    public void merge(int r1, int c1, int r2, int c2) {
        int keyA = table[r1][c1].key;
        int keyB = table[r2][c2].key;
        
        String word = table[r1][c1].word;
        if (word.equals("") && !table[r2][c2].word.equals("")) {
            word = table[r2][c2].word;
        }
        
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if ((table[i][j].key == keyA) || (table[i][j].key == keyB)) {
                    table[i][j].word = word;
                    table[i][j].key = uniqueKey;
                }
            }
        }
        
        uniqueKey += 1;
    }
    
    public void unmerge(int r, int c) {
        int key = table[r][c].key;
        String word = table[r][c].word;
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (table[i][j].key == key) {
                    table[i][j].key = uniqueKey;
                    table[i][j].word = "";
                    uniqueKey += 1;
                }
            }
        }
        table[r][c].word = word;
    }
    

}

class Node {
    int key;
    String word;
    public Node(String word, int key) {
        this.key = key;
        this.word = word;
    }
}