
import java.util.*;

public class test {
    public static void main(String[] args) {
               int[][] arr= {{441459,446342},{801308,840640},
                        {871890,963447},{228525,336985},
                        {807945,946787},{479815,507766},
                        {693292,944029},{751962,821744}};
               new Solution().minGroups(arr);
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 输入：intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 输出：3
 解释：我们可以将区间划分为如下的区间组：
 - 第 1 组：[1, 5] ，[6, 8] 。
 - 第 2 组：[2, 3] ，[5, 10] 。
 - 第 3 组：[1, 10] 。
   1,5  1,10    2,3   5,10 ,6,8
 可以证明无法将区间划分为少于 3 个组。
 */
class Solution {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        int res = 0 , len = intervals.length;
        boolean[] isVisited = new boolean[len];
        for (int i = 0; i < len; i++) {
            if(isVisited[i]) continue;
            res ++;
            isVisited[i] = true;
            int j = i;
            int end = intervals[j][1] , start = intervals[j][0];
            while (j < len){
                int mark = j ,earlyst = 1000000000;
                for (int k = j; k < len; k++) {
                    int nextS = intervals[k][0] , nextE = intervals[k][1];
                    if(isCover(start,end,nextS,nextE) || isVisited[k]) continue;
                    if(earlyst > nextE){
                        mark = k;
                        earlyst = nextE;
                    }
                }
                if(mark == j) break;
                isVisited[mark] = true;
                j = mark;
                end = intervals[j][1] ;
                start = intervals[j][0];
            }
        }
        return res;
    }

    public boolean isCover(int s ,int e, int ns, int ne){
        return ((s >= ns && s <= ne && ns != ne) || (e >= ns && e<= ne && ns != ne) || (s >= ns && e <= ne) || (ns >= s && ne <= e)) ;
    }
}
//[[441459,446342],[801308,840640],[871890,963447],[228525,336985],[807945,946787],[479815,507766],[693292,944029],[751962,821744]]