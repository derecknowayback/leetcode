package DataStructure.Stack.etf636;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ETF636 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("0:start:0");
        strings.add("1:start:2");
        strings.add("1:start:5");
        strings.add("0:start:6");
        new Solution().exclusiveTime(2,strings);
    }
}

/**
 * 用一个res数组记录时间
 * time = end - start + 1
 * 为什么会用栈呢？
 * 因为有递归这个东西，比较麻烦。而且你一个函数只能是嵌套或者顺序执行，
 * 比如 a开始 -> b开始 -> b结束 -> a结束
 * 不可能 a开始 -> b开始 -> a结束 -> b结束
 * (确实是这样的，所以放心用栈）
 */
class Solution {

    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<int[]> stack = new Stack<>();
        int[] res = new int[n];
        for (String log : logs) {
            String[] split = log.split(":");
            int id = Integer.parseInt(split[0]);
            int time = Integer.parseInt(split[2]);
            if ("start".equals(split[1])) {
                stack.push(new int[]{id, time});
            } else {
                int[] pop = stack.pop();
                int interval = time - pop[1] + 1;
                res[pop[0]] += interval;
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] -= interval;
                }
            }
        }
        return res;
    }
}