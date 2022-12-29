package DataStructure.Heap.tkfw692;

import javafx.util.Pair;

import java.util.*;

public class TKFW692 {

}

/**
 *  给定一个单词列表words和一个整数 k ，返回前k个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        int len;
        if(words == null || (len = words.length) == 0) return new ArrayList<>();
        // 一个堆，存储frequency
        PriorityQueue<Pair<String,Integer>> heap = new PriorityQueue<>((a,b)->{
            if( a.getValue() == b.getValue()) return a.getKey().compareTo(b.getKey());
            return Integer.compare(a.getValue(), b.getValue());
        });
        // 一个哈希表用来遍历
        HashMap<String,Integer> hashtable = new HashMap<>();
        for (int i = 0; i < len; i++) {
            hashtable.put(words[i],hashtable.getOrDefault(words[i],0)+1 );
        }
        hashtable.forEach((a,b)->{
            System.out.println("word: " + a);
            System.out.println("count: "+ b);
        });
        Set<Map.Entry<String, Integer>> entrySet = hashtable.entrySet();
        for (Map.Entry<String, Integer> temp:entrySet){
            heap.add(new Pair<>(temp.getKey(), temp.getValue()));
        }
        ArrayList<String> res = new ArrayList<>();
        while (! heap.isEmpty() || k >0){
            res.add(heap.poll().getKey());
            k--;
        }
        return res;
    }
}
