package DFS.zg488;

import java.util.HashMap;
import javafx.util.Pair;

public class ZG488 {
    public static void main(String[] args) {
        HashMap<Pair<Long,Long>,Boolean> memory = new HashMap<>();

    }
}

/**
 * 输入：board = "WRRBBW", hand = "RB"
 * 输出：-1
 * 解释：无法移除桌面上的所有球。可以得到的最好局面是：
 * - 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
 * - 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
 * 桌面上还剩着球，没有其他球可以插入。
 */
class Solution {

    static HashMap<Pair<Long,Long>,Integer> memory = new HashMap<>();


    public int findMinStep(String board, String hand) {
        memory.clear();
        StringBuilder boardBuilder = new StringBuilder(board), handBuilder = new StringBuilder(hand);



        return 0;
    }

    public void insert(StringBuilder zuma, StringBuilder balls){
        if(!check(zuma, balls)) return; // 没有可用的了,返回
        Integer res = memory.get(new Pair<>(hashStr(zuma), hashStr(balls)));
        if(res != null) return;

        //
        for (int i = 0; i < balls.length(); i++) {
            char ball = balls.charAt(i);
            for (int j = 0; j < zuma.length(); j++) {
                
            }
        }


    }

    public boolean check(StringBuilder a,StringBuilder b){
        // 红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W'
        boolean[] colors = new boolean[5];
        for (int i = 0; i < a.length(); i++) {
            char c =a.charAt(i);
            switch (c){
                case 'R': colors[0] = true; break;
                case 'Y': colors[1] = true; break;
                case 'B': colors[2] = true; break;
                case 'G': colors[3] = true; break;
                case 'W': colors[4] = true; break;
            }
        }
        for (int i = 0; i < b.length(); i++) {
            char c =b.charAt(i);
            switch (c){
                case 'R': if(colors[0]) return true;
                case 'Y': if(colors[1]) return true;
                case 'B': if(colors[2]) return true;
                case 'G': if(colors[3]) return true;
                case 'W': if(colors[4]) return true;
            }
        }
        return false;
    }


    public long hashStr(StringBuilder s){
        long sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c =s.charAt(i);
            int k = 7; // R
            switch (c){
                case 'Y': k = 11; break;
                case 'B': k = 13; break;
                case 'G': k = 17; break;
                case 'W': k = 19; break;
            }
            sum += sum * 131 + k;
        }
        return sum;
    }


}
