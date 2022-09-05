package DataStructure.Stack.vp20;

import java.util.ArrayDeque;

public class VP20 {

}
class Solution {
    public boolean isValid(String s) {
        int len;
        if( s == null || (len = s.length()) == 0) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()) return false;
                char top = stack.pop();
                if((c == ')' && top == '(') || (c == ']' && top == '[') || (c == '}' && top == '{'))
                    continue;
                else
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
