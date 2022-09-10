package DataStructure.Tree.csabst108;

public class CSABST108 {
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return makeTree(nums,0, nums.length-1);
    }
    public TreeNode makeTree(int[] nums, int left,int right){
        if(left > right) return null;
        int mid = left + right >>> 1;
        TreeNode treeNode = new TreeNode(nums[mid], null, null);
        treeNode.left = makeTree(nums,left,mid-1);
        treeNode.right = makeTree(nums,mid + 1,right);
        return treeNode;
    }
}
