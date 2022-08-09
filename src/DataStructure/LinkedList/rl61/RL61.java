package DataStructure.LinkedList.rl61;

public class RL61 {

}
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) return head;
        ListNode p1 = head, p2 = head , tail = null , newTail = null;
        int nums = 0;
        while (p2.next != null){
            p2 = p2.next;
            nums ++;
        }
        nums++;
        tail = p2;
        p2 = p1;
        int rotate = k % nums;
        while (p2 != null){
            p2 = p2.next;
            rotate--;
            if(rotate < 0)
                p1 = p1.next;
            if(p2 == tail)
                 newTail = p1;
        }
        newTail.next = null;
        tail.next = head;
        return p1;
    }
}