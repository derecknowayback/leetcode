package DFS.ms473;

import java.util.Arrays;

public class MS473 {
    public static void main(String[] args) {
        int[] arr = {5,5,5,5,4,4,4,4,3,3,3,3};
        System.out.println(new Solution().makesquare(arr));
//        if(arr[index] + now[i] <= target){
//            now[i] += arr[index];
//
//        }
    }
}
class Solution {
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int k : matchsticks) sum += k;
        if(sum % 4 != 0) return false;
        int []now = new int[4];
        Arrays.sort(matchsticks);
        return dfs(matchsticks,matchsticks.length - 1, now,sum / 4);
    }
    public boolean dfs(int[] arr, int index,int[] now,int target){
        boolean done = true;
        for (int i = 0; i < 4; i++) if(now[i] != target) done = false;
        if(done) return true;
        if(index < 0) return false;
        for (int i = 0; i < 4; i++) {
            if(arr[index] + now[i] <= target){
                now[i] += arr[index];
                boolean dfs = dfs(arr, index - 1, now, target);
                if(dfs) return true;
                now[i] -= arr[index];
            }
        }
        return false;
    }
}