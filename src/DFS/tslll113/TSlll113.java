package DFS.tslll113;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class TSlll113 {
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

        static List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            res.clear();
            dfs(new ArrayDeque<>(),root,targetSum);
            return res;
        }

        public void dfs(ArrayDeque<TreeNode> queue,TreeNode root,int target){
            if(root == null) return;
            if(root.val == target && root.left == null && root.right == null){
                ArrayList<Integer> answer = new ArrayList<>();
                for (TreeNode node: queue)
                    answer.add(node.val);
                answer.add(root.val);
                res.add(answer);
                return;
            }
            queue.addLast(root);
            dfs(queue,root.left,target-root.val);
            dfs(queue,root.right,target-root.val);
            queue.pollLast();
        }
}


