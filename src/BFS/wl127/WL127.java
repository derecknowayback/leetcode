package BFS.wl127;

import java.util.Arrays;
import java.util.List;

public class WL127 {
    public static void main(String[] args) {
        String []arr  = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
        String a = "qa", b = "sq";
        System.out.println(new Solution().ladderLength(a, b, Arrays.asList(arr)));
    }
}


class Solution {

    class TrieNode{
        TrieNode []child = new TrieNode[26];
        int isEnd = -1;

        public void insert(String s,int mark){
            TrieNode ptr = this;
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if(ptr.child[index] == null)
                    ptr.child[index] = new TrieNode();
                ptr = ptr.child[index];
            }
            ptr.isEnd = mark;
        }

        public int search(char[] s){
            TrieNode ptr = this;
            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';
                if(ptr.child[index] == null)
                    return -1;
                ptr = ptr.child[index];
                if(i == s.length - 1) return ptr.isEnd;
            }
            return -1;
        }
    }

    TrieNode root;

    int res;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        root = new TrieNode();
        res = Integer.MAX_VALUE;
        for (int i = 0; i < wordList.size(); i++)
            root.insert(wordList.get(i),i);
        int[] visit = new int[wordList.size()];
        Arrays.fill(visit,Integer.MAX_VALUE);
        bfs(beginWord.toCharArray(),visit,endWord,1);
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public void  bfs(char[] s, int[] visit,String target,int noff){
        if(target.equals(new String(s))){
            res = Math.min(noff,res);
            return;
        }
        for (int j = 0; j < s.length; j++) {
            char origin = s[j];
            for (int i = 0; i < 26; i++) {
                char c = (char) ('a' + i);
                s[j] = c;
                int search = root.search(s);
                // 如果 词典有 && 没有使用过
                if(search != -1 && visit[search] >= noff){
//                    System.out.println(new String(s));
                    visit[search] = noff;
                    bfs(s,visit,target,noff+1);
//                    visit[search] = false;
                }// 如果 词典里没有 或者 已经用过了, 恢复并跳过
            }
            s[j] = origin;
        }
    }
}