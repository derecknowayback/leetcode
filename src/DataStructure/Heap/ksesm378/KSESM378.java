package DataStructure.Heap.ksesm378;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KSESM378 {
}
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> matrix[a[0]][a[1]]));
        int len  = matrix.length;
        for (int i = 0; i < len; i++) {
            heap.add(new int[]{i,0});
        }
        int count = 0, res = 0;
        while (count < k && !heap.isEmpty()){
            int[] nowMin = heap.poll();
            res = matrix[nowMin[0]][nowMin[1]];
            count++;
            if(nowMin[1]+1 < len) continue;
            int[] next = new int[2];
            next[0] = nowMin[0];
            next[1] = nowMin[1]+1;
            heap.add(next);
        }
        return res;
    }
}