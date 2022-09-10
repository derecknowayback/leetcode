package DataStructure.Tree.lcat236;

public class LCAT236 {
    public static void main(String[] args) {

    }
}
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if(root == null)
          return null;
      if (root == p || root == q)
          return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null)
            return left;
        else if(right != null)
            return right;
        else
            return root;
    }
}