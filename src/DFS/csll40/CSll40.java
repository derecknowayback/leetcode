package DFS.csll40;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CSll40 {
}
class Solution {

    static List<List<Integer>> res = new ArrayList<>();
    HashMap<Integer,Integer> cnt;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res.clear();
        cnt = new HashMap<>();
        Arrays.sort(candidates);
        for (int candidate : candidates) {
            int val = cnt.getOrDefault(candidate, 0);
            cnt.put(candidate, val + 1);
        }
        candidates = new int[cnt.size()];
        int index = 0;
        for (int val : cnt.keySet()){
            candidates[index++] = val;
        }
        dfs(candidates,target,0,0,new ArrayDeque<>());
        return res;
    }

    public void dfs(int[] candidates,int target,int now, int index,ArrayDeque<Integer> arr){
        if(now == target){
            ArrayList<Integer> answer = new ArrayList<>(arr);
            res.add(answer);
            return;
        }
        if(now > target || index >= candidates.length) return;
        // 不选
        dfs(candidates, target, now, index+1, arr);
        // 选 n 个 （n >= 1 && n <= cnt[candidate[index]]）
        int copy = now;
        int k = 0;
        while (copy < target && k < cnt.get(candidates[index])){
            // 先添加
            arr.addLast(candidates[index]);
            copy += candidates[index];
            k++;
            dfs(candidates, target,copy, index+1, arr);
        }
        while (k-- > 0) arr.pollLast();
    }
}
