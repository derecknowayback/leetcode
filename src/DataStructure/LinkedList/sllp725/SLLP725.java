package DataStructure.LinkedList.sllp725;

public class SLLP725 {

}
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        if (head == null ) return res;
        int len = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            len ++;
        }
        if(k >= len){
            for (int i = 0; i < len; i++) {
                if(head != null){
                    res[i] = head;
                    head = head.next;
                    res[i].next = null;
                }
            }
            return res;
        }
        int divide = len / k , mod = len % k;
        p = head;
        int index = 0,cnt;
        while (p != null && index < k){
            cnt = 0;
            res[index] = p;
            cnt ++;
            while (cnt < divide){
                p = p.next;
                cnt++;
            }
            if(mod > 0){
                p = p.next;
                mod --;
            }
            ListNode next = p.next;
            p.next = null;
            p = next;
            index++;
        }
        return res;
    }
}