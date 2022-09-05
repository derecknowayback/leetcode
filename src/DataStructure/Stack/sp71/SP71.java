package DataStructure.Stack.sp71;

import java.util.ArrayDeque;

public class SP71 {
    public static void main(String[] args) {
        new Solution().simplifyPath("/home//foo/");
    }
}
class Solution {
    public String simplifyPath(String path) {
        if(path == null) return null;
        String[] strings = path.split("/");
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < strings.length; i++) {
            String temp = strings[i];
            if(temp == null || temp.length() == 0) continue;
            if(".".equals(temp)) continue;
            else if("..".equals(temp)){
                if(!stack.isEmpty())
                    stack.pop();
            }
            else stack.push(temp);
        }
        StringBuffer res = new StringBuffer("/");
       while (!stack.isEmpty()){ // 不能使用for循环
            res.append(stack.pollLast());
            if(!stack.isEmpty())
                res.append("/");
        }
        return res.toString();
    }
}