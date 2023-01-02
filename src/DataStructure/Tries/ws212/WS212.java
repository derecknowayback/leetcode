package DataStructure.Tries.ws212;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WS212 {
    public static void main(String[] args) {

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

class Solution {
    HashSet<String> res;

    static final int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

    public List<String> findWords(char[][] board, String[] words) {
        int len;
        if(board == null || (len = board.length) == 0) return new ArrayList<>();
        res = new HashSet<>();
        Trie trie = new Trie();
        // 建树
        for (String word : words) {
            trie.insert(word);
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < board[i].length; j++) {
                StringBuilder  builder = new StringBuilder();
                dfs(trie,builder,board,i,j);
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(Trie root,StringBuilder builder,char[][]board,int row,int col){
        // 检查下标
        if(root == null || row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '#') return;
        char c = board[row][col];
        builder.append(board[row][col]);
        board[row][col] = '#';
        String toString = builder.toString();
        if(!root.startsWith(toString)) {
            builder.deleteCharAt(builder.length()-1); // 回溯
            board[row][col] = c;
            return;
        }
        if(root.search(toString)) res.add(toString);
        for (int i = 0; i < directions.length; i++) {
            int a = directions[i][0], b = directions[i][1];
            dfs(root,builder,board,row + a,col + b);
        }
        builder.deleteCharAt(builder.length()-1); // 回溯
        board[row][col] = c;
    }
}