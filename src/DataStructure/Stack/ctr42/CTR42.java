package DataStructure.Stack.ctr42;



public class CTR42 {
    public static void main(String[] args) {
        int []a = {4,2,0,3,2,5};
        new Solution().trap(a);
    }
}
//我们需要一个单调栈，进来的
class Solution {
    public int trap(int[] height) {
        int len = height.length , res = 0;
        int[] rmax = new int[len];
        int[] lmax = new int[len] ;
        for (int i = 0; i < len; i++) {
            if(i==0) lmax[0] = height[i];
            else lmax[i] = Math.max(lmax[i-1],height[i]);
        }
        for (int i = len - 1; i >= 0; i-- ) {
            if(i==len - 1) rmax[len - 1] = height[i];
            else rmax[i] = Math.max(rmax[i+1],height[i]);
        }
        for (int i = 0; i < len; i++) {
            res += Math.min(lmax[i],rmax[i]) - height[i] ;
        }
        return res;
    }
}