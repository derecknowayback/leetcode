package DFS.msbt662;

import java.util.ArrayDeque;
import java.util.List;
import javafx.util.Pair;

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
        if(root.left == null && root.right == null) return 1;
        ArrayDeque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root,1));
        while (!queue.isEmpty()){
            int size = queue.size();
            int left = -1, right = -1;
            while (size -- > 0){
                Pair<TreeNode, Integer> poll = queue.poll();
                TreeNode node = poll.getKey();
                int pos = poll.getValue();
                if(node.left != null){
                    int nxtPos = pos * 2 - 1;
                    if(left == -1) left = nxtPos;
                    right = nxtPos;
                    queue.offer(new Pair<>(node.left,nxtPos));
                }
                if(node.right != null){
                    int nxtPos = pos * 2;
                    if(left == -1) left = nxtPos;
                    right = nxtPos;
                    queue.offer(new Pair<>(node.right,nxtPos));
                }
            }
            int width = left == right ? 0 : right - left  + 1;
            max = Math.max(width,max);
        }
        return max;
    }
}