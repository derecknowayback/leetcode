package DataStructure.Heap.fsth1606;


import javafx.util.Pair;

import java.util.*;

public class FSTH1606 {

}
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // 一个计分表
         int[] score = new int[k];
         int max = 0;
         // 一个状态表
        TreeSet<Integer> freeSet = new TreeSet<>();
        for (int i = 0; i < k ; i++) freeSet.add(i);
        // 一个最小堆，用于存过期时间
        PriorityQueue<Pair<Integer,Integer>> ttl_heap = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));
        // 一个时间帧
        int time = 0;
        int len = arrival.length;
        for (int i = 0; i < len; i++) {
            // 改变时间
            time = arrival[i];
            // 释放机子
            while (!ttl_heap.isEmpty()){
                if(ttl_heap.peek().getValue() <= time){
                    int to_free = ttl_heap.poll().getKey();
                    freeSet.add(to_free);
                }
                else break;
            }
            // 选机器
            Integer j = freeSet.ceiling(i % k);
            if(j == null) j = freeSet.ceiling(0);
            if(j == null) continue;
            freeSet.remove(j);
            score[j]++;
            if(score[j] > max) max = score[j];
            ttl_heap.offer(new Pair<>(j,time + load[i]));
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if(score[i] == max)
                res.add(i);
        }
        return res;
    }
}