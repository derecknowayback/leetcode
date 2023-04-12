package DFS.adrt623;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ADRT623 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        node.left = node1;
        node.right = node2;
        node2.left = node3;
        node3.right = node4;
        List<TreeNode> depths = new Solution().getDepths(node, 3);
        System.out.println("cc");
    }
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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth == 1){
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        List<TreeNode> depths = getDepths(root, depth - 1);
        System.out.println(depths.size());
        for (TreeNode node : depths) {
            TreeNode left = new TreeNode(val), right = new TreeNode(val);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
        }
        return root;
    }

    public List<TreeNode> getDepths(TreeNode root,int depth){
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<TreeNode> res = null;
            if(step == depth)
                res = new ArrayList<>();
            while (size -- > 0) {
                TreeNode poll = queue.poll();
                if(res != null) res.add(poll);
                else{
                    if(poll.left != null) queue.offer(poll.left);
                    if(poll.right != null) queue.offer(poll.right);
                }
            }
            if(res != null) return res;
            step++;
        }
        return null;
    }
}