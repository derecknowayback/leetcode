package DataStructure.LinkedList.swto35;

import java.util.HashMap;
import java.util.Map;

public class LCOF {

}

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

//class Solution {
//    // 这种方法修改了原来的链表
//    public Node copyRandomList(Node head) {
//        if(head == null) return null;
//        Node ptr = head, newHead = null, pre = null;
//        while (ptr != null){
//            Node newNode = new Node(ptr.val);
//            if(newHead == null) newHead = newNode;
//            Node oldBef = ptr; // 原链表的节点
//            ptr = ptr.next;
//            oldBef.next = newNode;
//            newNode.random = oldBef.random;
//            if(pre != null)
//                pre.next =  newNode; // 串联新的链表
//            pre = newNode;
//        }
//        ptr = newHead;
//        while (ptr != null){
//            if(ptr.random != null)
//                ptr.random  = ptr.random.next;
//            ptr = ptr.next;
//        }
//        return newHead;
//    }
//}
class Solution {
    public Node copyRandomList(Node head) {
        //在每个节点后面新建节点
        Node t = head;
        Node dummy = new Node(-10010), cur = dummy;
        Map<Node, Node> map = new HashMap<>();
        while (head != null) {
            Node node = new Node(head.val);
            map.put(head, node);
            cur.next = node;
            cur = cur.next; head = head.next;
        }
        cur = dummy.next; head = t;
        while (head != null) {
            cur.random = map.get(head.random);
            cur = cur.next; head = head.next;
        }
        return dummy.next;
    }
}