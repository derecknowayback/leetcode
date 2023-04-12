package DFS.csbt606;

public class CSBT606 {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

//        node1.left = node2;
//        node1

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
    public String tree2str(TreeNode root) {
        return dfs(root);
    }

    public String dfs(TreeNode root){
        if(root == null) return "";
        String res = Integer.toString(root.val);
        if(root.left != null){
            String left = dfs(root.left);
            res += "(" + left + ")";
        }else {
            if (root.right != null){
                res += "(" + ")";
            }
        }
        if (root.right != null) {
            String right = dfs(root.right);
            res += "(" + right + ")";
        }
        return res;
    }
}