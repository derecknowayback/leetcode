package DataStructure.Stack.rsbepp;

import java.util.ArrayDeque;

public class RSBEPP1190 {

}

/**
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 解释：先反转子字符串 "love" ，然后反转整个字符串。
 *
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 */
class Solution {
    public String reverseParentheses(String s) {
        char[] chars = s.toCharArray();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if(c == '('){
                stack.push(i);
            }else if(c == ')'){
                int  left = stack.pop() + 1,right = i - 1; // 交换 head ~ i-1
                while (left < right){
                    while (left < len &&(chars[left] == '(' || chars[left] == ')')) left++;
                    while (right >= 0 && (chars[right] == '(' || chars[right] == ')')) right--;
                    if(left >=  right) break;
                    char k =  chars[left];
                    chars[left] =  chars[right];
                    chars[right] =  k;
                    left++;
                    right--;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if(chars[i] != '(' && chars[i] != ')')
                stringBuilder.append(chars[i]);
        }
        return new String(stringBuilder);
    }
}
