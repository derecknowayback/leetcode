package DFS.fds652;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FDS652 {
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

    static HashMap<String,TreeNode> memory = new HashMap<>();
    static HashSet<String> record = new HashSet<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        memory.clear();
        record.clear();
        dfs(root);
        ArrayList<TreeNode> res = new ArrayList<>();
        for (String s : record)
            res.add(memory.get(s));
        return res;
    }

    public String dfs(TreeNode root){
        if(root == null) return "";
        String now = root.val + "";
        String left = dfs(root.left), right = dfs(root.right);
        if(!left.equals("")){
            if(memory.containsKey(left)) record.add(left);
            memory.put(left,root.left);
        }
        if(!right.equals("")){
            if(memory.containsKey(right)) record.add(right);
            memory.put(right,root.right);
        }
        now += "(" + left + ")" + "(" + right + ")";
        return now;
    }
}