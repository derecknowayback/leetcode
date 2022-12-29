package DataStructure.Heap.adp954;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ADP954 {
    public static void main(String[] args) {
        new Solution().canReorderDoubled(new int[]{-8,-4,-2,-1,0,0,1,2,4,8});
    }
}

/**
 * 示例 1：
 *
 * 输入：arr = [3,1,3,6]
 * 输出：false
 * 示例 2：
 *
 * 输入：arr = [2,1,2,6]
 * 输出：false
 * 示例 3：
 *
 * 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 *
 */
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        int len;
        if(arr == null || (len = arr.length) == 0) return false;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 0,count_0 = 0;
        for (int i = 0; i < len; i++) {
            if(arr[i] != 0){
                int old = hashMap.getOrDefault(arr[i], 0);
                hashMap.put(arr[i],old + 1);
            }
            else{
                count_0++;
                if(count_0 % 2 == 0) count++;
            }
        }
        Arrays.sort(arr);
        for (int i = len - 1; i >=0; i--) {
            int temp = arr[i];
            int a1 = hashMap.getOrDefault(temp,0);
            int a2 = hashMap.getOrDefault(2 * temp,0);
            if(temp != 0 && a1 != 0 && a2 != 0 ){
                count++;
                hashMap.put(temp,a1-1);
                hashMap.put(temp*2,a2-1);
            }
        }
        return count == len/2;
    }
}