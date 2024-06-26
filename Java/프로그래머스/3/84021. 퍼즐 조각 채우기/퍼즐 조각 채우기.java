import java.util.*;

class Solution {

    int[][] game_board, table;
    boolean[][] visit;
    int findValue;
    List<Piece> boardPieces = new ArrayList<>();
    List<Piece> tablePieces = new ArrayList<>();

    int[] dx = new int[]{0, 0, 1, -1};
    int[] dy = new int[]{1, -1, 0, 0};

    class Piece {
        int[][] shape;
        List<int[]> points = new ArrayList<>();
        int[] rectX = new int[]{Integer.MAX_VALUE, 0};
        int[] rectY = new int[]{Integer.MAX_VALUE, 0};
        boolean use = false;

        public void addPoint(int x, int y) {
            points.add(new int[]{x, y});
            rectX[0] = Math.min(rectX[0], x);
            rectX[1] = Math.max(rectX[1], x);
            rectY[0] = Math.min(rectY[0], y);
            rectY[1] = Math.max(rectY[1], y);
        }

        public void buildShape() {
            Collections.sort(points, (o1, o2) -> {
                if (o1[0] < o2[0]) return 1;
                else if (o1[0] > o2[0]) return -1;
                else return o1[1] < o2[0] ? 1 : -1;
            });

            shape = new int[rectX[1]-rectX[0]+1][rectY[1]-rectY[0]+1];
            for (int[] point : points) {
                shape[point[0] - rectX[0]][point[1] - rectY[0]] = 1;
            }
        }

        public int getSize() {
            return points.size();
        }

        public boolean equals(Piece piece) {
            if (shape == null) {
                buildShape();
            }

            int[][] pShape = piece.shape;

            if (pShape == null) {
                piece.buildShape();
            }

            boolean pass = true;
            if (shape.length == pShape.length && shape[0].length == pShape[0].length) {
                for (int i = 0; i < shape.length; i++) {
                    if (pass == false) break;
                    for (int j = 0; j < shape[i].length; j++) {
                        if (pass == false) break;
                        if (shape[i][j] != pShape[i][j]) pass = false;
                    }
                }

                if (pass == true) return true;
                pass = true;
                // 180도
                for (int i = 0; i < shape.length; i++) {
                    if (pass == false) break;
                    for (int j = 0; j < shape[i].length; j++) {
                        if (pass == false) break;
                        if (shape[i][j] != pShape[shape.length - i - 1][shape[i].length - j - 1]) pass = false;
                    }
                }

                if (pass == true) return true;
            }

            pass = true;

            if (shape.length == pShape[0].length && shape[0].length == pShape.length) {
                // 90도
                for (int i = 0; i < shape.length; i++) {
                    if (pass == false) break;
                    for (int j = 0; j < shape[i].length; j++) {
                        if (pass == false) break;
                        if (shape[i][j] != pShape[j][shape.length - i - 1]) pass = false;
                    }
                }

                if (pass == true) return true;
                pass = true;

                // 270도
                for (int i = 0; i < shape.length; i++) {
                    if (pass == false) break;
                    for (int j = 0; j < shape[i].length; j++) {
                        if (pass == false) break;
                        if (shape[i][j] != pShape[shape[i].length - j - 1][i]) pass = false;
                    }
                }

                if (pass == true) return true;
            }

            return false;
        }

    }

    // 빈칸이 없으려면 딱맞아야함
    // 양쪽 piece 클래스를 만들어 크기가 일치하는 경우를 셈
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        this.game_board = game_board;
        this.table = table;

        getPieces(game_board, boardPieces, 0);
        getPieces(table, tablePieces, 1);

        // 비어있으면 안됨으로, 일치하는 조각만 삽입가능 -> 그냥 일치하는 갯수 세면됨
        for (int i = 0; i < boardPieces.size(); i++) {
            for (int j = 0; j < tablePieces.size(); j++) {
                if (tablePieces.get(j).use == true) continue;
                if (boardPieces.get(i).use == true) break;

                if (boardPieces.get(i).equals(tablePieces.get(j))) {
                    answer += tablePieces.get(j).getSize();
                    tablePieces.get(j).use = true;
                    boardPieces.get(i).use = true;
                }
            }
        }

        return answer;
    }

    // board와 table 조각들 구하기
    public void getPieces(int[][] board, List<Piece> pieces, int value) {

        this.visit = new boolean[game_board.length][game_board[0].length];
        this.findValue = value;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (visit[i][j] == false && board[i][j] == findValue) {
                    pieces.add(getPiece(board, i, j));
                }
            }
        }
    }

    // 특정 점 기준으로 조각 가져오기
    public Piece getPiece(int[][] board, int x, int y) {
        Piece piece = new Piece();
        getPoints(board, x, y, piece);
        piece.buildShape();
        return piece;
    }

    // 특정 점에대한 포인트 취합
    public void getPoints(int[][] board, int x, int y, Piece piece) {
        if (visit[x][y] == true) return;

        visit[x][y] = true;
        piece.addPoint(x, y);

        for (int i = 0; i < dx.length; i++) {
            if (x+dx[i] < 0 || x+dx[i] >= board.length
                    || y+dy[i] < 0 || y+dy[i] >= board[x].length) continue;

            if (board[x+dx[i]][y+dy[i]] == findValue) {
                getPoints(board, x+dx[i], y+dy[i], piece);
            }
        }
    }

    
}