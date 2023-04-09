package DFS.rip301;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RIP301 {
    public static void main(String[] args) {
       new Solution().removeInvalidParentheses(")(").forEach(System.out::println);
    }
}
class Solution {

    static int max = 0;

    static List<Set<Integer>> unused = new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {
        // 先把字母删掉
        max = 0;
        unused.clear();
        StringBuilder withoutLetter = new StringBuilder();
        boolean hasLeft = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c >= 'a' && c <= 'z') continue;
            withoutLetter.append(c);
            if(c == '(') hasLeft = true;
        }
        // 处理特殊情况: 没有左括号 或者 没有括号
        if(!hasLeft){
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c >= 'a' && c <= 'z') res.append(c);
            }
            return Collections.singletonList(res.toString());
        }

        ArrayDeque<Integer> left = new ArrayDeque<>(), right = new ArrayDeque<>();
        dfs(withoutLetter.toString(),0,new StringBuilder(),left,right,false);

        HashSet<String> res = new HashSet<>();
        for (int i = 0; i < unused.size(); i++) {
            Set<Integer> skip = unused.get(i);
            StringBuilder temp = new StringBuilder();
            int indexOfQ = 0;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if((c == '(' || c == ')') && skip.contains(indexOfQ++)) continue;
                temp.append(c);
            }
            res.add(temp.toString());
        }
        return new ArrayList<>(res);
    }

    public void dfs(String s,int index,StringBuilder now,ArrayDeque<Integer> left,ArrayDeque<Integer> right,boolean isTopLeft){
        if(s.length() == index){
            // match
            if(left.size() == right.size() && left.size() >= max){
                if(left.size() > max) unused.clear();
                boolean[] used = new boolean[s.length()];
                for (int k : left) used[k] = true;
                for (int k : right) used[k] = true;
                HashSet<Integer> list = new HashSet<>();
                for (int i = 0; i < used.length; i++)if(!used[i]) list.add(i);
                unused.add(list);
                max = left.size();
            }
            return ;
        }
        int originLen = now.length();
        char c = s.charAt(index);
        // 左括号无条件来
        if(c == '('){
            // 选
            now.append(c);
            left.push(index);
            dfs(s,index+1,now,left,right,true);
            left.pop();
            now.setLength(originLen);
            // 不选
            dfs(s,index + 1,now,left,right,isTopLeft);
        }
        //  右括号只能在有空闲左括号来
        else if (c == ')') {
            if(isTopLeft){
                now.append(c);
                right.push(index);
                boolean newTop = left.size() == right.size();
                dfs(s,index+1,now,left,right,!newTop);
                right.pop();
            }
            // 不选
            dfs(s,index+1,now,left,right,isTopLeft);
        }
    }

}
