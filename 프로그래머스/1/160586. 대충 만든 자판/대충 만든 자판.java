class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            int sum = 0;
            for (char value : target.toCharArray()) {
                int min_index = -1;
                for (String key : keymap) {
                    int value_index = key.indexOf(value);
                    if (min_index == -1) {
                        min_index = value_index;
                    } else if (value_index != -1 && value_index < min_index) {
                        min_index = value_index;
                    }
                }
                if (min_index == -1) {
                    sum = -1;
                    break;
                } else {
                    sum += min_index+1;
                }
            }
            
            answer[i] = sum;
        }
        return answer;
    }
}