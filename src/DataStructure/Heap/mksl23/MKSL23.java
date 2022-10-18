package DataStructure.Heap.mksl23;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MKSL23 {
}
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> listNodes = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        int len = lists.length;
        for (ListNode list : lists) {
            if (list != null)
                listNodes.add(list);
        }
        ListNode newHead = new ListNode(), tail = null;
        while (! listNodes.isEmpty()){
            ListNode min = listNodes.poll();
            if(tail == null)
                newHead.next = min;
            else
                tail.next = min;
            tail = min;
            if(min.next != null)
                listNodes.add(min.next);
            min.next = null;
        }
        return newHead.next;
    }
}