import java.util.*;

class Solution {
    public String[] solution(String[] s) {
		int l = s.length, len, i, j, idx, c; 
        boolean f; 
        char x, y, z;
		String str;
		StringBuilder sb;
		Stack<Character> stack;
		for (i = 0; i < l; i++) {
			str = s[i];
			len = str.length();
			c = 0;
			stack = new Stack<>();
			for (j = 0; j < len; j++) {
				z = str.charAt(j);
				if (stack.size() >= 2 && z == '0') {
					y = stack.pop();
					x = stack.pop();
					if (x == '1' && y == '1' && z == '0') {
						c++;
						continue;
					} else {
						stack.push(x);
						stack.push(y);
						stack.push(z);
					}
				} else
					stack.push(z);
			}
			if (c > 0) {
				idx = stack.size();
				f = false;
				sb = new StringBuilder();
				while (!stack.isEmpty()) {
					if (!f && stack.peek() == '1')
						idx--;
					if (!f && stack.peek() == '0')
						f = true;
					sb.insert(0, stack.pop());
				}
				while (c-- > 0)
					sb.insert(idx, "110");
				s[i] = sb.toString();
			}
		}
		return s;
    }
}