package DFS.gp22;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class GP22 {
    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3).toString());
    }
}
class Solution {

    static List<String> res = new ArrayList<>();

    int target;

    public List<String> generateParenthesis(int n) {
        res.clear();
        target = n;
        dfs(0,0,new StringBuilder());
        return res;
    }

    public void dfs(int now,int k,StringBuilder builder){
        if(now == target){
            res.add(builder.toString());
            return;
        }
        int len = builder.length();
        // 可以加 "("
        if(k + now < target){
            builder.append('(');
            dfs(now, k + 1, builder);
            builder.setLength(len);
        }
        // 可以加 ")"
        if (k > 0){
            builder.append(')');
            dfs(now+1, k-1, builder);
            builder.setLength(len);
        }
    }
}
