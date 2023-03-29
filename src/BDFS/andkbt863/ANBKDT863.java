package BDFS.andkbt863;


import java.util.*;

public class ANBKDT863 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        new Solution().distanceK(root,root.left,1);
    }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        if(k == 0) {
            res.add(target.val);
            return res;
        }
        // 从根节点构造 "溯源"队列
        Queue<TreeNode> reverse = new ArrayDeque<>();
        makeReverseQueue(reverse,root,target);
        // 搜索非子树
        searchNonSub(res,reverse,k,target.val);
        // 搜索子树
        searchSub(res,target,k);
        return res;
    }

    public boolean makeReverseQueue(Queue<TreeNode> queue,TreeNode root,TreeNode target){
        if(root == null) return false;
        if(root == target) return true;
        boolean left = makeReverseQueue(queue, root.left, target), right = makeReverseQueue(queue,root.right,target);
        if(left || right)
            queue.offer(root);
        return left || right;
    }

    public void searchSub(List<Integer> res,TreeNode target,int dis){
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> temp = new LinkedList<>();
        queue.offer(target);
        int step = 0;
        while (!queue.isEmpty() && step < dis){
            step++;
            while (!queue.isEmpty()) temp.add(queue.poll());
            while (!temp.isEmpty()){
                TreeNode poll = temp.poll();
                if(step == dis){
                    if(poll.left != null) res.add(poll.left.val);
                    if(poll.right != null) res.add(poll.right.val);
                }
                if(poll.left != null) queue.offer(poll.left);
                if(poll.right != null)queue.offer(poll.right);
            }
        }
    }

    public void searchNonSub(List<Integer> res,Queue<TreeNode> reverse,int k,int target){
        if(reverse.isEmpty()) return;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> temp = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        visited.add(target); // 添加target自身
        TreeNode firstParent = reverse.poll();
        queue.offer(firstParent);
        visited.add(firstParent.val);
        int step = 1;
        while (!queue.isEmpty() && step <= k){
            while (!queue.isEmpty())
                temp.offer(queue.poll());
            while (!temp.isEmpty()){
                TreeNode poll = temp.poll();
                if(step == k)
                    res.add(poll.val);
                if(poll.left != null && !visited.contains(poll.left.val)){
                    queue.offer(poll.left);
                    visited.add(poll.left.val);
                }
                if(poll.right != null && !visited.contains(poll.right.val)){
                    queue.offer(poll.right);
                    visited.add(poll.right.val);
                }
                if(!reverse.isEmpty()){
                    TreeNode parentCandidate = reverse.peek();
                    if(parentCandidate.left == poll || parentCandidate.right == poll){
                        reverse.poll(); // pop first
                        queue.offer(parentCandidate);
                        visited.add(parentCandidate.val);
                    }
                }
            }
            step++;
        }
    }
}
