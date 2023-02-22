package DataStructure.Tries.pss745;


import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PSS745 {
}
class WordFilter {

    String[] words;
    Trie prefixTrie;
    HashMap<String,Integer> string2Index;

    
    
    public WordFilter(String[] words) {
        this.words = words;
        prefixTrie = new Trie();
        string2Index = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            string2Index.put(words[i], i);
            prefixTrie.insert(words[i], i);
        }
    }

    public int f(String pref, String suff) {
        int max = -1;
        List<String> strings = prefixTrie.startsWith(pref);
        if(strings == null) return -1;
        for (String word : strings) {
            if(isWithSuff(word,suff)){
                int k = string2Index.get(word);
                max = Math.max(max, k);
            }
        }
        return max;
    }

    public boolean isWithSuff(String s,String suff){
        if(s == null || s.length() < suff.length()) return false;
        return suff.equals(s.substring(s.length() - suff.length()));
    }



    class Trie {

        TreeNode root;

        public Trie() {
            this.root = new TreeNode();
        }

        public void insert(String word,int i) {
            if(word == null || word.length() == 0) return;
            TreeNode ptr = root;
            int index = 0, len = word.length();
            while (ptr != null && index < len){
                int c = word.charAt(index) - 'a';
                // 如果当前节点有c
                if(ptr.sons[c] != null ){
                    ptr = ptr.sons[c];
                    index++;
                    if(index == len) ptr.is_end = i;
                }
                // 如果当前节点没有c
                else{
                    TreeNode son;
                    c = word.charAt(index) - 'a';
                    son = new TreeNode(index == len-1 ? i : -1,word.charAt(index));
                    ptr.sons[c] = son;
                    index++;
                    ptr = son;
                }
            }
        }

        public boolean search(String word) {
            int len;
            if(word == null || (len = word.length()) == 0) return false;
            TreeNode ptr = root;
            for (int i = 0; i < len; i++) {
                int c = word.charAt(i) - 'a';
                if(ptr.sons[c] == null) return false;
                if(i == len - 1 && ptr.sons[c].is_end == -1) return false;
                ptr = ptr.sons[c];
            }
            return true;
        }

        public List<String> startsWith(String prefix) {
            int len;
            if(prefix == null || (len = prefix.length()) == 0) return null;
            TreeNode ptr = root;
            for (int i = 0; i < len; i++) {
                int c = prefix.charAt(i) - 'a';
                if(ptr.sons[c] == null) return null;
                ptr = ptr.sons[c];
            }
            List<String> res = new ArrayList<>();
            bfs(res,ptr);
            return res;
        }

        public void bfs(List<String>res,TreeNode root){
            if(root == null) return;
            if(root.is_end != -1){
                res.add(words[root.is_end]);
            }
            for (int i = 0; i < 26; i++) {
                if (root.sons[i] != null){
                    bfs(res,root.sons[i]);
                }
            }
        }
    }

    class TreeNode{
        int is_end;
        Character value;
        TreeNode[] sons;
        public TreeNode(int is_end, char value) {
            this.is_end = is_end;
            this.value = value;
            sons = new TreeNode[26];
        }
        public TreeNode(){
            is_end = -1;
            value = null;
            sons = new TreeNode[26];
        }
    }
    
    
}
