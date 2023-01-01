package DataStructure.Tries.tries208;

import java.util.List;

public class Tries208 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");
    }
}
class Trie {

    TreeNode root;

    class TreeNode{
        boolean is_end;
        Character value;
        TreeNode[] sons;
        public TreeNode(boolean is_end, char value) {
            this.is_end = is_end;
            this.value = value;
            sons = new TreeNode[26];
        }
        public TreeNode(){
            is_end = false;
            value = null;
            sons = new TreeNode[26];
        }
    }

    public Trie() {
        this.root = new TreeNode();
    }

    public void insert(String word) {
        if(word == null || word.length() == 0) return;
        TreeNode ptr = root;
        int index = 0, len = word.length();
        while (ptr != null && index < len){
            int c = word.charAt(index) - 'a';
            // 如果当前节点有c
            if(ptr.sons[c] != null ){
                ptr = ptr.sons[c];
                index++;
                if(index == len) ptr.is_end = true;
            }
            // 如果当前节点没有c
            else{
                TreeNode son;
                while ( index < len) {
                    c = word.charAt(index) - 'a';
                    son = new TreeNode(index == len-1,word.charAt(index));
                    if(index == len-1) son.is_end = true;
                    ptr.sons[c] = son;
                    index++;
                    ptr = son;
                }
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
            if(i == len - 1 && !ptr.sons[c].is_end) return false;
            ptr = ptr.sons[c];
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        int len;
        if(prefix == null || (len = prefix.length()) == 0) return true;
        TreeNode ptr = root;
        for (int i = 0; i < len; i++) {
            int c = prefix.charAt(i) - 'a';
            if(ptr.sons[c] == null) return false;
            ptr = ptr.sons[c];
        }
        return true;
    }
}

