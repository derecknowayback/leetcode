package DataStructure.LinkedList.ll22;

public class DS22 {

}

class ListNode {
     int val;
    ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p1 = head,p2 = head.next, p3 = head.next.next,pre = null;
        ListNode res = p2;
        while (true){
            p1.next = p3;
            p2.next = p1;
            if(pre != null)
                pre.next = p2;
            pre = p1;
            p1 = p3;
            if(p1 == null)
                break;
            p2 = p3.next;
            p3 = p3.next.next;
        }
        return res;
    }
}