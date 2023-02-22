package DataStructure.Tries.msp677;

import java.util.Arrays;

public class msp677 {
}

class MapSum {
    static int[][] trie = new int[10000][23];
    static int [] values = new int[10000];
    static int index = 0;


    public MapSum() {
        for (int row = index; row >= 0; row--) {
            Arrays.fill(trie[row], 0);
        }
        Arrays.fill(values, 0);
        index = 0;
    }

    public void insert(String key, int val) {
        int p = 0;
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i) - 'a';
            if(trie[p][c] == 0) trie[p][c] = ++index;
            p = trie[p][c];
        }
        values[p] = val;
    }

    public int sum(String prefix) {
        int p = 0;
        for (int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            if(trie[p][c] != 0)
                p = trie[p][c];
            else
                return 0;
        }
        return dfs(p);
    }

    public int dfs(int p){
        int sum = values[p];
        for (int i = 0; i < 26; i++) {
            if(trie[p][i] != 0){
                sum += dfs(trie[p][i]);
            }
        }
        return sum;
    }

}
