package DataStructure.Tries.sc1032;


import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SC1032 {
    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        streamChecker.query('a');
        streamChecker.query('b');
        streamChecker.query('c');
        streamChecker.query('d');
        streamChecker.query('e');
        streamChecker.query('f');
        streamChecker.query('g');
        streamChecker.query('h');
        streamChecker.query('i');
        streamChecker.query('j');
    }
}

class StreamChecker {

    String[] words;
    Trie trie;
    StringBuffer stream;
    HashSet<Integer> size;

    public StreamChecker(String[] words) {
        this.words = words;
        stream = new StringBuffer();
        trie = new Trie();
        size = new HashSet<>();
        for (String s : words){
            trie.insert(s);
            size.add(s.length());
        }
    }

    public boolean query(char letter) {
        stream.append(letter);
        int len = stream.length();
        for (int k : size) {
            if(k > len) continue;
            System.out.println(stream.substring(len - k));
            boolean search = trie.search(stream.substring(len - k));
            if(search) return true;
        }
        return false;
    }

    class Trie {

        TreeNode root;

        public Trie() {
            this.root = new TreeNode();
        }

        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            TreeNode ptr = root;
            int index = 0, len = word.length();
            while (ptr != null && index < len) {
                int c = word.charAt(index) - 'a';
                // 如果当前节点有c
                if (ptr.sons[c] != null) {
                    ptr = ptr.sons[c];
                    index++;
                    if (index == len) ptr.is_end = 1;
                }
                // 如果当前节点没有c
                else {
                    TreeNode son= new TreeNode(index == len - 1 ? 1 : -1, word.charAt(index));
                    ptr.sons[c] = son;
                    index++;
                    ptr = son;
                }
            }
        }

        public boolean search(String word) {
            int len;
            if (word == null || (len = word.length()) == 0) return false;
            TreeNode ptr = root;
            for (int i = 0; i < len; i++) {
                int c = word.charAt(i) - 'a';
                if (ptr.sons[c] == null) return false;
                if (i == len - 1 && ptr.sons[c].is_end == -1) return false;
                ptr = ptr.sons[c];
            }
            return true;
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
