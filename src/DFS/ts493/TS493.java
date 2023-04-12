package DFS.ts493;

import java.util.HashSet;

public class TS493 {
}
class Solution {
    static int res = 0;

    public int findTargetSumWays(int[] nums, int target) {
        res = 0;
        dfs(nums,target,0,0);
        return res;
    }

    public void dfs(int[]nums,int target,int index, int now){
        if(index == nums.length){
            if(now == target) res++;
            return;
        }
        // 加
        dfs(nums,target,index+1,now + nums[index]);
        // 减
        dfs(nums,target,index+1,now - nums[index]);
    }

}
