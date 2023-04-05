package DFS.dasw211;

public class DASW211 {
    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("a");
        dictionary.addWord("ab");
        System.out.println(dictionary.search("a."));
        System.out.println(dictionary.search(".."));
        System.out.println(dictionary.search("."));
    }
}
class WordDictionary {

    class TrieNode{
        boolean isEnd;
        TrieNode []trieNodes = new TrieNode[26];
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode ptr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(ptr.trieNodes[index] == null){
                ptr.trieNodes[index] = new TrieNode();
            }
            ptr = ptr.trieNodes[index];
        }
        ptr.isEnd = true;
    }

    public boolean search(String word) {
        return search(word,root);
    }

    public boolean search(String word,TrieNode root){
        TrieNode ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c == '.'){
                boolean res = false;
                for (int j = 0; j < 26; j++) {
                    if(ptr.trieNodes[j] != null)
                        res |= search(word.substring(i+1),ptr.trieNodes[j]);
                }
                return res;
            }else{
                int index = c - 'a';
                if(ptr.trieNodes[index] == null) return false;
                ptr = ptr.trieNodes[index];
            }
        }
        return ptr.isEnd;
    }
}