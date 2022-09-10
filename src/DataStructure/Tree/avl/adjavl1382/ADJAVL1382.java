package DataStructure.Tree.avl.adjavl1382;


import java.util.ArrayList;
import java.util.HashMap;

public class ADJAVL1382 {
    public static void main(String[] args) {

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
    HashMap <TreeNode,Integer> heightMap = new HashMap<>();
    ArrayList<TreeNode> nodes = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        addMap_List(root);
        root = treefy();
        return root;
    }



    public void addMap_List(TreeNode root){
        if(root == null) return;
        else{
            TreeNode newNode = new TreeNode(root.val, null, null);
            heightMap.put(newNode,1);
            nodes.add(newNode);
            addMap_List(root.left);
            addMap_List(root.right);
        }
    }



    public TreeNode deleteNode(TreeNode root,int key){
        if(root == null) return null;
        if(root.val > key){
            root.left = deleteNode(root.left,key);
        }else if(root.val < key){
            root.right = deleteNode(root.right, key);
        }else{
            if(root.left == null && root.right == null) return null;
            else if(root.right == null) return root.left;
            else if (root.left == null) return root.right;
            else {
                TreeNode p = root.right,bef = root;
                while (p.left != null){
                    p = p.left;
                }
                root.right = deleteNode(root.right, p.val);
                p.left  = root.left;
                p.right = root.right;
                root = p;
            }
        }
        return root;
    }

    public TreeNode treefy(){
        TreeNode root = null;
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode temp = nodes.get(i);
            root = add(root,temp);
        }
        return root;
    }

    public TreeNode add(TreeNode root,TreeNode toAdd){
        if(root == null) return toAdd; // 这个很妙
        int a = root.val , b = toAdd.val;
        if(a == b) return root;
        else if (a > b) {
            if(root.left != null)
                root.left = add(root.left,toAdd); //这里也要设置root.left
            else{
                root.left = toAdd;
                int leftHeight = heightMap.get(root) + 1;
                heightMap.put(root,leftHeight);
            }
        }else{
            if(root.right != null)
                root.right = add(root.right,toAdd); // 这里也要设置 root.right
            else {
                root.right = toAdd;
                int rightHeight = heightMap.get(root) + 1 ;
                heightMap.put(root,rightHeight);
            }
        }
        root = adjust(root);
        int height = getHeight(root);
        heightMap.put(root,height); // 这里要更新高度，很重要
        return root;
    }

    public TreeNode adjust(TreeNode root){
        if(root == null) return null;
        int leftHeight = heightMap.getOrDefault(root.left,0);
        int rightHeight = heightMap.getOrDefault(root.right,0);
        int dif = Math.abs(leftHeight - rightHeight);
        if(dif > 1){
            if(leftHeight > rightHeight){
                TreeNode leftNode = root.left;
                if(leftNode.right != null && getHeight(leftNode.right) > getHeight(leftNode.left)){
                    root.left = rotateLeft(leftNode);
                }
                root = rotateRight(root);
            }else{
                TreeNode rightNode = root.right;
                if(rightNode.left != null && getHeight(rightNode.left) > getHeight(rightNode.right)){
                    root.right = rotateRight(rightNode);
                }
                root = rotateLeft(root);
            }
        }
        return root;
    }

    public int getHeight(TreeNode node){
        //return heightMap.getOrDefault(node,0);
        if(node == null) return 0;
        return Math.max(heightMap.getOrDefault(node.left,0),heightMap.getOrDefault(node.right,0)) + 1;
    }

    public TreeNode rotateLeft(TreeNode root){
        TreeNode right = root.right;
        heightMap.put(root,Math.max(getHeight(right.left),getHeight(root.left)) + 1);
        heightMap.put(right,Math.max(getHeight(root),getHeight(root.right)) + 1);
        root.right = right.left;
        right.left = root;
        return right;
    }

    public TreeNode rotateRight(TreeNode root){
        TreeNode left = root.left;
        heightMap.put(root,Math.max(getHeight(left.right),getHeight(root.right)) + 1);
        heightMap.put(left,Math.max(getHeight(root),getHeight(root.left)) + 1);
        root.left = left.right;
        left.right = root;
        return left;
    }
}
