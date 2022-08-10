package DataStructure.LinkedList.lru146;

import java.util.HashMap;
import java.util.Hashtable;

public class Solution {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        int first = lruCache.get(1);
        lruCache.put(3,3);
        lruCache.get(2);
        lruCache.put(4,4);
        int second = lruCache.get(1);
        int third = lruCache.get(1);
        int fourth = lruCache.get(1);
    }
}

/**
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class LRUCache {
    class Node{
        int key;
        int value;
        Node bef;
        Node next;
        Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    final int capacity;
    int contain;
    HashMap<Integer,Node> hashmap;

    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hashmap = new HashMap<>();
        Node head = new Node(-1, -1);
        Node tail = new Node(-1, -1);
        this.contain = 0;
        head.next = tail;
        tail.bef = head;
        this.head = head;
        this.tail = tail;
    }

    public int get(int key) {
        Node node = hashmap.get(key);
        if(node == null) return -1;
        // 注意查询的时候也要改变双向链表
        int value = node.value;
        delete(node);
        pushList(key, value);
        return value;
    }

    public void put(int key, int value) {
        Node node = hashmap.get(key);
        //没在里面
        if(node == null){
            // 删除最后一个
            if(contain == capacity){
                delete(tail.bef);
                //减少数量
                contain--;
            }
            contain++;
        }
        //在里面
        else{
                delete(node);
        }
        pushList(key, value);
    }

    public void pushList(int key, int value){
        //添加链表第一个以及hash表
        Node newNode = new Node(key, value);
        Node oldHead = head.next;
        head.next = newNode;
        newNode.bef = head;
        oldHead.bef = newNode;
        newNode.next = oldHead;
        hashmap.put(key,newNode);
    }

    public void delete(Node node){
        if(node.bef != null){
            node.bef.next = node.next;
            node.next.bef = node.bef;
        }
        hashmap.remove(node.key);
    }

}