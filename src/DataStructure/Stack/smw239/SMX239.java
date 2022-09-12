package DataStructure.Stack.smw239;

import java.util.ArrayDeque;

public class SMX239 {
    public static void main(String[] args) {
        new Solution().maxSlidingWindow(new int[]{1,3,1,2,0,5},3);
    }
}

/**
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int len = nums.length,left = 0 ,right = 0;
        int[] res = new int[len - k + 1];
        while (right < len){
            int temp = nums[right];
            while (! stack.isEmpty() && stack.peek() < temp){
                    stack.pop();
            }
            stack.push(temp);
            if(right+1-k >= 0)
                res[right+1-k] = stack.peekLast();
            right++;
            if(right >= k) left++;
            if(left > 0 && !stack.isEmpty() && stack.peekLast() == nums[left - 1]) stack.pollLast();
        }
        return res;
    }
}
