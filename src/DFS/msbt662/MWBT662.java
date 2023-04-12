package DFS.msbt662;

import java.util.ArrayDeque;
import java.util.List;

public class MWBT662 {
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        // 根据上一层算出下一层
        int max = 1;

        return max;
    }
}