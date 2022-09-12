package DataStructure.LinkedList.rnkg25;

public class RNKG25 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        new Solution().reverseKGroup(n1,2);
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int count = 0;
        ListNode ptr = head, nex = null , start = head;
        while (ptr != null){
            count ++;
            if(count == k){
                nex = ptr.next;
                ListNode p1 = null, p2 = start, p3 = start.next;
                while (p3 != nex){
                    //链表反转容易出现死循环，小心！
                    p2.next = p1;
                    p1 = p2;
                    p2 = p3;
                    p3 = p2.next;
                    p2.next = p1;
                }
                start.next = reverseKGroup(nex,k); // 分治递归处理
                return p2; // p2是新的头
            }
            ptr = ptr.next;
        }
        return head; // 长度不够直接反转
    }
}