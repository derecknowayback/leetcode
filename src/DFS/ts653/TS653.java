package DFS.ts653;

import java.util.ArrayList;
import java.util.List;

public class TS653 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5 );
        TreeNode n1 = new TreeNode(3,new TreeNode(2),new TreeNode(4));
        TreeNode n2 = new TreeNode(6,null,new TreeNode(7));
        root.left = n1; root.right = n2;
        new Solution().findTarget(root,9);
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
    public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        preFetch(list,root);
        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i);
            int search = binarySearch(list, k - a);
            if(search != -1 && search != i) return true;
        }
        return false;
    }

    public void preFetch(List<Integer> res,TreeNode root){
        if(root == null) return;
        preFetch(res,root.left);
        res.add(root.val);
        preFetch(res,root.right);
    }

    public int binarySearch(List<Integer> list,int target){
        int left = 0, right = list.size() - 1;
        while (left < right){
            int mid = left + right >> 1;
            if(list.get(mid) < target)
                left = mid + 1;
            else
                right = mid;
        }
        return list.get(left) == target ? left : -1;
    }

}