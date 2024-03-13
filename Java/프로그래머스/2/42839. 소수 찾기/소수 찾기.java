import java.util.*;

class Solution {
    int answer = 0;
    Set<String> numbersSet = new HashSet<>();
    
    public int solution(String numbers) {
        // int[] numbersArr = Arrays
        //     .stream(st.split(""))
        //     .mapToInt((num) -> Integer.parseInt(num))
        //     .sorted()
        //     .toArray();
        
        numbersSet.add("");
        numbersSet.add("0");
        numbersSet.add("1");
        
        dfs("", 0, numbers.split(""));
        
        return answer;
    }
    
    public void dfs(String value, int count, String[] numbersArr) {
        if (value.equals("0")) {
            return;
        }
        
        int size = numbersSet.size();
        numbersSet.add(value);
        boolean isChecked = true;
        if (!(size == numbersSet.size())) {
            for (int i = 2; i <= Math.sqrt(Integer.parseInt(value)); i++) {
                if (Integer.parseInt(value) % i == 0) {
                    isChecked = false;
                    break;
                }
            }
                        
            if (isChecked == true) {
                answer++;
            }
        }
        
        for (int i = 0; i < numbersArr.length; i++) {
            String addValue = numbersArr[i];
            List<String> numbersList = new ArrayList<>(Arrays.asList(numbersArr));
            numbersList.remove(i);
            dfs(value + addValue, count+1, numbersList.toArray(String[]::new));
        }
        
    }
}