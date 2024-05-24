import java.util.*;

class Solution {
    String[] answer = {};
    String[][] tickets;
    Stack<String> path;
    boolean[] use;
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.use = new boolean[tickets.length];
        this.path = new Stack<>();
        
        path.add("ICN");
        dfs("ICN", 0);
        
        return answer;
    }
    
    private void dfs(String start, int n) {
        
        if (isTicketsUsedAll() == true) {
            answer = getEarlyOrder(answer, path.toArray(new String[0]));
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(start) && use[i] == false) {
                use[i] = true;
                path.add(tickets[i][1]);
                dfs(tickets[i][1], n+1);
                path.pop();
                use[i] = false;
            }
        }
        
    }
    
    private boolean isTicketsUsedAll() {        
        for (int i = 0; i < use.length; i++) {
            if (use[i] == true) {
                continue;
            }
            return false;
        }
        
        return true;
    }
    
    private String[] getEarlyOrder(String[] ans1, String[] ans2) {
        
        if (ans1.length == 0) {
            return ans2;
        }
        
        for (int i = 0; i < ans1.length; i++) {
            if (ans1[i].equals(ans2[i])) {
                continue;
            }
            
            return ans1[i].compareTo(ans2[i]) > 0 ? ans2 : ans1;
        }
        
        return ans1;
    }
    
    
}