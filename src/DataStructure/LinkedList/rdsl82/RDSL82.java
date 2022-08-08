package DataStructure.LinkedList.rdsl82;

public class RDSL82 {

}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode p1 = null, p2 = head,p3,res = null;
        while (p2 != null){
            boolean multiple = false;
            p3 = p2.next;
            while (p3 != null && p2.val == p3.val){
                p3 = p3.next;
                multiple = true;
            }
            if (!multiple) {
                if (p1 == null) {
                    p1 = p2;
                    res = p1;
                } else {
                    p1.next = p2;
                    p1 = p2;
                }
                p1.next = null;
            }
            p2 = p3;
        }
        return res;
    }
}