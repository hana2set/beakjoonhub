class Solution {
    public int[] solution(int e, int[] starts) {
        int[] res = new int[starts.length];

        int[] count = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                count[i * j]++;
            }
        }

        int[] max = new int[e + 1];
        max[e] = e;
        for (int i = e - 1; i >= 1; i--) {
            if (count[i] >= count[max[i + 1]]) {
                max[i] = i;
            } else {
                max[i] = max[i + 1];
            }
        }

        for (int i = 0; i < res.length; i++) {
            res[i] = max[starts[i]];
        }
        return res;
    }
}