package DataStructure.Tries.mxtna421;

public class MXTNA421 {
    public static void main(String[] args) {
        int[] arr = {3,10,5,25,2,8};
        new Solution().findMaximumXOR(arr);
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
            sons = new TreeNode[2];
        }
        public TreeNode(){
            is_end = false;
            value = null;
            sons = new TreeNode[2];
        }
    }

    public Trie() {
        this.root = new TreeNode();
    }

    public void insert(StringBuilder word) {
        if(word == null || word.length() == 0) return;
        TreeNode ptr = root;
        int index = 0, len = word.length();
        while (ptr != null && index < len){
            int c = word.charAt(index) - '0';
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
                    c = word.charAt(index) - '0';
                    son = new TreeNode(index == len-1,word.charAt(index));
                    if(index == len-1) son.is_end = true;
                    ptr.sons[c] = son;
                    index++;
                    ptr = son;
                }
            }
        }
    }

    public int getMaxXOR(StringBuilder xS,int x){
        if(root == null) return 0;
        TreeNode ptr = root;
        int index = 0;
        int bitmap = 0;
        while (ptr != null && index < xS.length()){
            if(xS.charAt(index) == '0'){
                if(ptr.sons[1] != null){
                    bitmap = bitmap | (1 << (31 - index));
                    ptr = ptr.sons[1];
                }else{
                    ptr = ptr.sons[0];
                }
            }
            else{
                if(ptr.sons[0] != null){
                    bitmap = bitmap | (1 << (31 - index));
                    ptr = ptr.sons[0];
                }else{
                    ptr = ptr.sons[1];
                }
            }
            index++;
        }
        return bitmap;
    }

}

class Solution {
    public int findMaximumXOR(int[] nums) {
        int len;
        if(nums == null || (len = nums.length) == 0) return 0;
        Trie trie = new Trie();
        for (int i = 0; i < len; i++) {
            StringBuilder builder = toBString(nums[i]);
            trie.insert(builder);
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            int temp = trie.getMaxXOR(toBString(nums[i]),nums[i]);
            if(temp > max) max = temp;
        }
        return max;
    }
    public StringBuilder toBString(int x){
        int k = 1;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            res.append((x & k) >> i);
            k <<= 1;
        }
        return res.reverse();
    }
}
