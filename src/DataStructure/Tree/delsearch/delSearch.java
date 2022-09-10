package DataStructure.Tree.delsearch;



public class delSearch {
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        TreeNode p = root,parent = root;
        boolean isLeftChild = true, delRoot = false;
        if(root.val == key) delRoot = true;
        while (p != null && p.val != key){
            parent = p;
            if(p.val < key){
                p = p.right;
                isLeftChild = false;
            }
            else{
                p = p.left;
                isLeftChild = true;
            }
        }
        if(p == null) return root;
        TreeNode leftC = p.left , rightC = p.right;
        if(leftC == null && rightC == null){
            if(parent == p) return null;
            if(isLeftChild) parent.left = null;
            else parent.right = null;
        } else if (leftC != null && rightC != null) {
             TreeNode smallest = p.right,bef = p;
             while (smallest.left != null){
                 bef = smallest;
                 smallest = smallest.left;
             }
             if(smallest == p.right){
                 if(isLeftChild && bef != p) parent.left = smallest;
                 else parent.right = smallest;
             }
             if(bef != p)bef.left = null;

             smallest.left = p.left;
             if(smallest != p.right) smallest.right = p.right;
             if(delRoot) root = smallest;
        }else{
           if(isLeftChild){
               parent.left = leftC == null ? rightC : leftC;
           }else{
               parent.right = leftC == null ? rightC : leftC;
           }
        }
        return root;
    }
}
