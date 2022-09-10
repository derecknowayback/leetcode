package DataStructure.Stack.bbt654;

public class BBT654 {
    public static void main(String[] args) {
        new Solution().constructMaximumBinaryTree(new int[]{1,2,3,4,5,6});
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int len = nums.length;
        return recur(nums,0,len-1);
    }
    public TreeNode recur(int[] nums , int start , int end){
        if(start == -1 || start == nums.length || end == -1 || end == nums.length || start > end) return null;
        if(start == end) return new TreeNode(nums[start]);
        else{
            int maxIndex = start,max = -1;
            for (int i = start; i <= end; i++) {
                if(nums[i] > max){
                    max = nums[i];
                    maxIndex = i;
                }
            }
            TreeNode root = new TreeNode(nums[maxIndex]);
            root.left = recur(nums,start,maxIndex - 1);
            root.right = recur(nums,maxIndex+1,end);
            return root;
        }
    }
}
