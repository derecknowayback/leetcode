package DataStructure.Stack.ssm907;

import java.util.ArrayDeque;


public class SSM907 {
    public static void main(String[] args) {
        new Solution().sumSubarrayMins(new int[]{11,81,94,43,3});
    }
}

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int len = arr.length;
        int[][] helper = new int[len][2];
        int mod = 1_000_000_007;
        // todo 处理右边
        ArrayDeque<Integer> rStack = new ArrayDeque<>();
        for (int i = len - 1; i >= 0; i--) {
            while (!rStack.isEmpty() && arr[rStack.peek()] > arr[i]) {
                rStack.pop();
            }
            if (rStack.isEmpty())
                helper[i][1] = len;
            else
                helper[i][1] = rStack.peek();
            rStack.push(i);
        }

        // todo 处理左边
        ArrayDeque<Integer> lStack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!lStack.isEmpty() && arr[lStack.peek()] >= arr[i]) {
                lStack.pop();
            }
            if (lStack.isEmpty())
                helper[i][0] = -1;
            else
                helper[i][0] = lStack.peek();
            lStack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < len; ++i) {
            // 注意：乘法可能越界，要先转成 long 类型
            int i1 = i - helper[i][0];
            int i2 = helper[i][1] - i;
            long l = (long) i1 * i2  % mod * arr[i] % mod;
            ans += l;
            ans %= mod;
        }
        return (int) ans;
    }
}
