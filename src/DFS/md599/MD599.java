package DFS.md599;

import java.util.ArrayDeque;
import java.util.List;

public class MD599 {
}
// Definition for a Node.
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
    public int maxDepth(Node root) {
        if(root == null) return 0;
        int steps = 0;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                Node poll = queue.poll();
                for (Node son : poll.children)
                    queue.offer(son);
            }
            steps++;
        }
        return steps;
    }
}