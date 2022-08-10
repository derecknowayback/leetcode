package DataStructure.LinkedList.fmdll430;

public class FMDLL430 {

}
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}
class Solution {
    public Node flatten(Node head) {
        recur(head);
        return head;
    }
    public Node recur(Node head){
        if(head == null) return null;
        Node p = head, last = null;
        while (p !=null){
            if(p.next == null) last = p;
            if(p.child != null){
                Node next = p.next;
                Node lastChild = recur(p.child);
                p.next = p.child;
                p.child.prev = p;
                lastChild.next = next;
                if(next != null)
                    next.prev = lastChild;
                p.child = null;
            }
            p = p.next;
        }
        return last;
    }
}
