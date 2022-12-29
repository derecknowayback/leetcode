package DataStructure.Tree.pONT589;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PONT589 {
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
class Solution {

//    public List<Integer> preorder(Node root) {
//        if(root == null) return new ArrayList<>();
//        List<Integer> res =  new ArrayList<>();
//        res.add(root.val);
//        for (Node son : root.children) {
//            List<Integer> childList = preorder(son);
//            res.addAll(childList);
//        }
//        return res;
//    }

    public List<Integer> preorder(Node root){
        if(root == null) return new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        // 对于每个节点，我都要知道已经遍历到 “哪个孩子”了，才能决定下一个要遍历的孩子是谁
        Node p = root;
        ArrayDeque<Pair<Node,Integer>> nodeStack = new ArrayDeque<>();
        while (p != null || ! nodeStack.isEmpty()){
            while (p != null){
                res.add(p.val);
                if(p.children.size() != 0){
                    nodeStack.push(new Pair<>(p,0));
                    p = p.children.get(0);
                }
                else p = null;
            }
            if(!nodeStack.isEmpty()){
                Pair<Node, Integer> pop = nodeStack.pop();
                Node parent = pop.getKey();
                if(parent.children.size() != pop.getValue() + 1){
                    p = parent.children.get(pop.getValue()+1);
                    nodeStack.push(new Pair<>(parent, pop.getValue()+1));
                }
            }
        }
        return  res;
    }
}