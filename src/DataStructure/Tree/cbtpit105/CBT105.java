package DataStructure.Tree.cbtpit105;

import java.util.HashMap;

public class CBT105 {
    public static void main(String[] args) {
        int[] arr1 =  new int[]{3,9,20,15,7};
        int[] arr2 =  new int[]{9,3,15,20,7};

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

    HashMap<Integer,Integer> preMap;
    HashMap<Integer,Integer> inMap;

    int[] preorder;
    int[] inorder;






}