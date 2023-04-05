package DFS.cs39;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class CS39 {
    public static void main(String[] args) {
        int []arr = {2,3,6,7};
        System.out.println(new Solution().combinationSum(arr, 7).size());
    }
}
class Solution {

    static List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res.clear();
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
        // 选 n 个 （n >= 1）
        int copy = now;
        int k = 0;
        while (copy < target){
            // 先添加
            arr.addLast(candidates[index]);
            copy += candidates[index];
            k++;
            dfs(candidates, target,copy, index+1, arr);
        }
        while (k-- > 0) arr.pollLast();
    }
}
