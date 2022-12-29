package DataStructure.Heap.votbt987;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class VOTBT987 {
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

    PriorityQueue<MyNode> heap;

    class MyNode{
        TreeNode treeNode;
        int row;
        int col;
        MyNode(TreeNode t,int row,int col){
            this.treeNode = t;
            this.row = row;
            this.col = col;
        }
    }

    void travel(TreeNode root,int row,int col){
        if(root == null) return;
        heap.add(new MyNode(root,row,col));
        travel(root.left,row+1,col-1);
        travel(root.right,row+1,col+1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // 确定行列，行通过层数确定，列通过左右确定
        heap = new PriorityQueue<>((a,b)->{
            if(a.col == b.col && a.row == b.row) return a.treeNode.val - b.treeNode.val;
            if(a.col == b.col) return a.row - b.row;
            return a.col - b.col;
        });
        travel(root,0,0);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> embed = new ArrayList<>();
        Integer bef = null;
        while (! heap.isEmpty()){
            MyNode temp = heap.poll();
            if(bef == null || temp.col != bef){
                if (bef != null) res.add(embed);
                embed = new ArrayList<>();
                bef = temp.col;
            }
            embed.add(temp.treeNode.val);
        }
        if (!embed.isEmpty()) res.add(embed);
        return res;
    }
}
