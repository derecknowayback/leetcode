package BFS.fj403;

import java.util.*;

public class FJ403 {
    public static void main(String[] args) {
        int []arr = {0,1,3,5,6,8,12,17};
        new Solution().canCross(arr);
    }
}
class Solution {

    Boolean [][]visited;
    public boolean canCross(int[] stones) {
       int n = stones.length;
       visited = new Boolean[n][n];
       return dfs(stones,0,0);
    }

    public boolean dfs(int[]stones,int index,int step){
        if(index == stones.length - 1) return true;
        if(visited[index][step] != null) return false;
        else visited[index][step] = true;
        int curPos = stones[index];
        for (int i = -1; i < 2; i++) {
            if(step + i <= 0) continue;
            int nextPos = curPos + step + i;
            int k = Arrays.binarySearch(stones, nextPos);
            if(k > 0){
                if(dfs(stones,k,step+i))
                    return true;
            }
        }
        return false;
    }
}
