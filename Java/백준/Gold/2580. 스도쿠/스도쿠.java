import java.io.*;
import java.util.*;

class Main {

    static int[][] sudoku = new int[9][9];
    static List<int[]> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            sudoku[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 빈 곳 체크
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    points.add(new int[]{i, j});
                }
            }
        }

        dfs(0, points.get(0));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]);
                if (j == 9) break;
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static boolean dfs(int index, int[] point) {
        for (int i = 1; i < 10; i++) {
            if (isPossible(point, i)) {
                sudoku[point[0]][point[1]] = i;
                if (index+1 == points.size() // 다음 빈 칸 없음 (모두 적절하게 입력)
                        || dfs(index+1, points.get(index+1)) == true) // 최종 결과값이 조건을 만족
                    return true;

                sudoku[point[0]][point[1]] = 0;
            }
        }

        return false;
    }

    private static boolean isPossible(int[] point, int number) {
        for (int i = 0; i < 9; i++) {
            // 가로 세로 일치여부
            if (sudoku[point[0]][i] == number
                    || sudoku[i][point[1]] == number) return false;
        }

        // 직사각형 일치여부
        int xl = point[0] / 3;
        int yl = point[1] / 3;
        for (int i = xl*3; i < xl*3+3; i++) {
            for (int j = yl*3; j < yl*3+3; j++) {
                if (sudoku[i][j] == number) return false;
            }
        }

        return true;
    }
}