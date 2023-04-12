package DFS.btt563;

import java.util.HashMap;

public class BTT563 {
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

    static HashMap<TreeNode,Integer> map = new HashMap<>();

    public int findTilt(TreeNode root) {
        map.clear();
        forePlay(root);
        int sum = 0;
        for (TreeNode t : map.keySet()) {
            int left = map.getOrDefault(t.left,0), right = map.getOrDefault(t.right,0);
            sum += Math.abs(left - right);
        }
        return sum;
    }

    public int forePlay(TreeNode root){
        if(root == null) return 0;
        int left = forePlay(root.left), right = forePlay(root.right);
        int self = left + right + root.val;
        // 不存储叶子
        if(self != root.val)
            map.put(root,self);
        return self;
    }
}