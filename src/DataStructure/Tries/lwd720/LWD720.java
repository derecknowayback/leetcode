package DataStructure.Tries.lwd720;

import java.util.Arrays;

public class LWD720 {
}
class Solution {

    static int trie[][] = new int[50000][26];
    static String cnt[] = new String[50000];
    static int index = 0;
    static String res = null;
    static int seqHit = 0;

    public String longestWord(String[] words) {
        for (int row = index; row >= 0; row--) Arrays.fill(trie[row], 0);
        Arrays.fill(cnt,null);
        index = 0;
        res = null;
        seqHit = 0;
        for (String word : words) {
            insert(word);
        }
        dfs(0,0);
        return res == null ? "" : res;
    }

    public void insert(String s){
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if(trie[p][c] == 0) trie[p][c] = ++index;
            p = trie[p][c];
        }
        cnt[p] = s;
    }

    public void dfs(int p,int temp){
        for (int i = 0; i < 26; i++) {
            if(trie[p][i] != 0){
                int k = trie[p][i];
                if(cnt[k] != null){
                    if(temp >= seqHit){
                        if(temp > seqHit || res == null) res = cnt[k];
                        else res = cnt[k].compareTo(res) > 0 ? res : cnt[k];
                        seqHit = temp;
                    }
                    // 只要从一个字母开始的连续字符串
                    dfs(k,temp+1);
                }
            }
        }
    }
}