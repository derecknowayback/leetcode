
import java.util.*;

public class test {
    public static void main(String[] args) {
        String s = "1:end:10";
        String[] split = s.split(":");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
        //System.out.println(new Solution().getNum(num2, 2));
      // System.out.println(new Solution().shipWithinDays(num1,4));
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
 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 */
class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        long[] preSum = new long[len + 1];
        preSum[0] = 0;
        for (int i = 1; i <= len; i++) {
            preSum[i] = preSum[i-1] + chalk[i-1];
        }
        int floor = (int)Math.floor(1.0 * k / preSum[len]);
        k -= floor * preSum[len];
        int left = 0 , right = len;
        while (left < right){
            int mid = left + right >>> 1;
            if (preSum[mid] <= k)
                left = mid + 1;
            else
                right = mid;
        }
        return left==0?len-1:left-1;
    }
}