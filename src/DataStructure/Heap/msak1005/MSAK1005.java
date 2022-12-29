package DataStructure.Heap.msak1005;

import java.util.PriorityQueue;

public class MSAK1005 {
}
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 两个堆，负数堆，绝对值堆，优先负数，再是绝对值堆
        PriorityQueue<Integer> negative = new PriorityQueue<>(Integer::compareTo);
        PriorityQueue<Integer> absolute = new PriorityQueue<>(Integer::compareTo);
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (num < 0) negative.offer(num);
            absolute.offer(Math.abs(num));
        }
        while (k > 0){
            if(!negative.isEmpty()){
                sum += negative.poll() * (-2);
            }else{
                if(k % 2 == 1) sum -= absolute.poll() * 2;
                break;
            }
            k--;
        }
        return sum;
    }
}
