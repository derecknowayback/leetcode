package BFS.mocn2059;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class MOCN2059 {
    public static void main(String[] args) {
        System.out.println((10 + 20) ^ 13 - 20 );
        System.out.println((10 - 20) ^ 13 + 20 );
    }
}
class Solution {

    int[] nums;
    static boolean[] aVisited = new boolean[1001], bVisited = new boolean[1001];
    public int minimumOperations(int[] _nums, int start, int goal) {
        nums = _nums;
        // 剪枝，只要 0~1000之内某个数访问过了，接下来就不需要访问了
        Arrays.fill(aVisited,false);
        Arrays.fill(bVisited,false);
        Queue<Integer> aQueue = new ArrayDeque<>();
        Queue<Integer> bQueue = new ArrayDeque<>();
        aQueue.offer(start);
        bQueue.offer(goal);
        return 0;
    }

    public void update(){

    }



}