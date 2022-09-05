package DataStructure.Stack.lvp32;

import java.util.ArrayDeque;

public class LVP32 {
    public static void main(String[] args) {
        new Solution().longestValidParentheses("(()");
    }
}
class Solution {
    public int longestValidParentheses(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int length = s.length();
        boolean[] booleans = new boolean[length];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if(c == '(') stack.push(i);
            else{
                if(!stack.isEmpty()){
                    int index = stack.pop();
                    booleans[index] = true;
                    booleans[i] = true;
                }
            }
        }
        int res = 0, temp = 0;
        for (int i = 0; i < length; i++) {
            if(booleans[i]) temp++;
            else{
                res =Math.max(temp,res);
                temp = 0;
            }
        }
        res =Math.max(temp,res);
        return res;
    }
}