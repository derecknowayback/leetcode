package DFS.ubst95;
import java.util.ArrayList;
import java.util.List;


public class UBST95 {
    public static void main(String[] args) {
        System.out.println(new Solution().generateTrees(3).size());
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

    static List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> generateTrees(int n) {
        res.clear();
        boolean[] visited = new boolean[n+1];
        dfs(visited,null);
        return res;
    }

    public void dfs(boolean[]visited,TreeNode root){
        boolean isFull = true;
        for (int i = 1; i <= visited.length-1 ; i++) {
            if(!visited[i]){
                isFull = false;
                visited[i] = true;
                if(root == null){
                    dfs(visited, new TreeNode(i));
                }else{
                    insert(root,i);
                    dfs(visited, root);
                }
                visited[i] = false;
            }
        }
        if(isFull)
            res.add(root);
    }


    public void insert(TreeNode root, int x){
        TreeNode ptr = root;
        while (ptr != null){
            if(ptr.val > x){
                if(ptr.left == null){
                    ptr.left = new TreeNode(x);
                    return;
                }
                ptr = ptr.left;
            }
            else if(ptr.val < x){
                if(ptr.right == null){
                    ptr.right = new TreeNode(x);
                    return;
                }
                ptr = ptr.right;
            }
            else return;
        }
    }


}