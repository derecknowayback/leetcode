package DFS.mswfl1255;

import java.util.Arrays;

public class MSWFL1255 {
    public static void main(String[] args) {
        String []s = {"leetcode"};
        char[] c = {'l','e','t','c','o','d'};
        int []arr = {0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0};
        new Solution().maxScoreWords(s,c,arr);
    }
}

/*
  * 输入：words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
    输出：23
    解释：
    字母得分为  a=1, c=9, d=5, g=3, o=2
    使用给定的字母表 letters，我们可以拼写单词 "dad" (5+1+5)和 "good" (3+2+2+5)，得分为 23 。
    而单词 "dad" 和 "dog" 只能得到 21 分。
 */

class Solution {

    static int[] cnt = new int[26];
    int[][] dict;
    int[] score;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        Arrays.fill(cnt,0);
        this.score = score;
        // 统计字符
        for (int i = 0; i < letters.length; i++) {
            int index = letters[i] - 'a';
            cnt[index] ++;
        }
        // 预处理一下字符串们,加速dfs效率; 最多进行 14 * 15 次操作
        dict = new int[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                int index = words[i].charAt(j) - 'a';
                dict[i][index]++;
            }
        }
        return dfs(0);
    }

    public int dfs(int index){
        if (index == dict.length) return 0;
        int takeThis = countDown(index);
        int nextWithThis = 0;
        if(takeThis != 0){
            nextWithThis = dfs(index + 1);
            if(nextWithThis != 0) takeThis += nextWithThis;
        }

        if(takeThis != 0)
            recovery(index);


        int noTakeThis = dfs(index + 1);
        return Math.max(takeThis,noTakeThis);
    }

    public int countDown(int i){
        int res = 0;
        for (int j = 0; j < 26; j++) {
            int num = dict[i][j];
            if(cnt[j] < num){
                // recovery and return -1
                for (int k = 0; k < j; k++)
                    cnt[k] += dict[i][k];
                return 0;
            }
            cnt[j] -= num;
            res += num * score[j];
        }
        return res;
    }

    public void recovery(int i){
        for (int j = 0; j < 26; j++) {
            cnt[j] += dict[i][j];
        }
    }

}