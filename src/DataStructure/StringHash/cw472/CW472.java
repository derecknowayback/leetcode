package DataStructure.StringHash.cw472;

import java.util.*;
import java.util.stream.Collectors;

public class CW472 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String []arr = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
       solution.findAllConcatenatedWordsInADict2(arr);
    }
}


class Solution {

    class TrieNode{
        TrieNode[] children;
        boolean isEnd;

        public TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    public void insert(String s){
        TrieNode ptr = root;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if(ptr.children[index] == null)
                ptr.children[index] = new TrieNode();
            if(i == s.length() - 1)
                ptr.children[index].isEnd = true;
            ptr = ptr.children[index];
        }
    }

    public boolean search(String s){
        TrieNode ptr = root;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (ptr.children[index] == null) return false;
            ptr = ptr.children[index];
        }
        return ptr.isEnd;
    }

    TrieNode root = new TrieNode();
    HashSet<String> set = new HashSet<>();

    int minLen = Integer.MAX_VALUE;

    // 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
    //连接词 定义为：一个完全由给定数组中的至少两个较短单词（不一定是不同的两个单词）组成的字符串。
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (String s : words) {
            if(s.length() == 0) continue; // 过滤空串;
            minLen = Math.min(s.length(),minLen);
        }
        for (String s : words) {
            if(dfs(s,0))
                set.add(s);
            else
                insert(s);
        }
        return new ArrayList<>(set);
    }

    public boolean dfs(String s,int index){
        if(index >= s.length() || s.length() - index < minLen) return false;
        // 记忆化搜索;
        if(set.contains(s.substring(index))) return true;
        for (int i = index + 1; i <= s.length(); i++) {
            if(!search(s.substring(index,i))) {
                if(i == s.length()) return false;
                continue; // 没找到就下一个;
            }
            // 找到了就递归处理
            boolean child = dfs(s, i);
            if(child) return true;
            // 如果没找到继续找下去;
        }
        return index != 0;
    }


    Set <Long> hashset = new HashSet<>();

    int prime = 131, offset = 128;
    public List<String> findAllConcatenatedWordsInADict2(String[] words){
        List<String> res = new ArrayList<>();
        for (String word : words) hashset.add(hashString(word));
        for (String s : words)
            if(check(s)) res.add(s);
        return res;
    }

    public long hashString(String s){
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            int k = s.charAt(i) - 'a';
            hash += hash * prime + k + offset;
        }
        return hash;
    }

    public boolean check(String s){
        int n = s.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, -1);
        f[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (f[i] == -1) continue;
            long cur = 0;
            // 从前往后推，否则太费时了
            for (int j = i + 1; j <= n; j++) {
                cur = cur * prime + (s.charAt(j - 1) - 'a') + offset;
                if (hashset.contains(cur)) f[j] = Math.max(f[j], f[i] + 1);
            }
        }
        return f[n] > 1;
    }
}
