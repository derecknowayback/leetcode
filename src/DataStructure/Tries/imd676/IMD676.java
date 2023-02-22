package DataStructure.Tries.imd676;


import java.util.Arrays;

public class IMD676 {
    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        String []dict = {"hello","leetcode"};
        magicDictionary.buildDict(dict);
        magicDictionary.search("hello");
        magicDictionary.search("hhllo");
    }
}
class MagicDictionary {

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

        public Trie(TreeNode treeNode){
            root = new TreeNode();
            int index = treeNode.value - 'a';
            root.sons[index] = treeNode;
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

        public boolean searchWithChangeOne(String word) {
            int len;
            if(word == null || (len = word.length()) == 0) return false;
            TreeNode ptr = root;
            for (int i = 0; i < len; i++) {
                int c = word.charAt(i) - 'a';
                if(ptr.sons[c] == null){
                    boolean res = false;
                    for (int j = 0; j < ptr.sons.length; j++) {
                        if(ptr.sons[j] == null) continue;
                        TreeNode son = ptr.sons[j];
                        String s = son.value + word.substring(i + 1);
                        res = res || (new Trie(son)).search(s);
                    }
                    return res;
                }
                if(i == len - 1 && !ptr.sons[c].is_end) return false;
                ptr = ptr.sons[c];
            }
            return false;
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



    Trie trie;

    public MagicDictionary() {
        trie = new Trie();
    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            trie.insert(s);
        }
    }

    public boolean search(String searchWord) {
        char[] chars = searchWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char origin = searchWord.charAt(i);
            for (char j = 'a'; j <= 'z'; j++) {
                if(j == origin) continue;
                chars[i] = j;
                boolean search = trie.search(new String(chars));
                if(search) return true;
            }
            chars[i] = origin;
        }
        return false;
    }
}