package DataStructure.Stack.nbe496;

import java.util.ArrayDeque;
import java.util.HashMap;

public class NBE496 {
}
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int len = nums2.length;
        for (int i = len; i >= 0  ; i--) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]){
                stack.pop();
            }
            if(stack.isEmpty()) hashMap.put(nums2[i],-1);
            else hashMap.put(nums2[i],stack.peek());
            stack.push(nums2[i]);
        }
        int size = nums1.length;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            int nextBig = hashMap.get(nums1[i]);
            res[i] = nextBig;
        }
        return res;
    }
}