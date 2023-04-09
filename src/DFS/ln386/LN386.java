package DFS.ln386;

import java.util.ArrayList;
import java.util.List;

public class LN386 {
}
class Solution {

    static List<Integer> res = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        res.clear();
        // 从1开始枚举
        for (int i = 1; i <= 9; i++)
            dfs(i,n);
        return res;
    }

    public void dfs(int n,int limit){
        if(n <= limit) res.add(n);
        for (int i = 0; i < 10; i++) {
            int nxt = addDigit(n, i);
            if(nxt <= limit)
                dfs(nxt,limit);
        }
    }

    public int addDigit(int origin,int k){
        return origin * 10 + k;
    }
}