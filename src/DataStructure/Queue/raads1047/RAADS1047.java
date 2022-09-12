package DataStructure.Queue.raads1047;

import java.util.ArrayDeque;

public class RAADS1047 {
}

/**
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * "abbaca"
 * "azxxzy"
 */
class Solution {
    public String removeDuplicates(String s) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (deque.isEmpty() || c != deque.peekLast()) {
                deque.addLast(c);
            } else{
                while (! deque.isEmpty() && deque.peekLast() == c)
                    deque.pollLast();
            }
        }
        while (!deque.isEmpty()) {
            builder.append(deque.pollFirst());
        }
        return new String(builder);
    }
}