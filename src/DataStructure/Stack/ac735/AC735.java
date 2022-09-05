package DataStructure.Stack.ac735;

import java.util.ArrayDeque;
import java.util.Stack;

public class AC735 {
    public static void main(String[] args) {
        new Solution().asteroidCollision(new int[]{5,10,-5});
    }
}

/**
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * 两个stack，越→（or ←）越top，开始碰撞，碰撞结果重新入栈
 */
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> bef = new ArrayDeque<>();
        for (int i = 0; i < asteroids.length; i++) {
            if(asteroids[i] > 0) bef.push(asteroids[i]);
            else{
                while(!bef.isEmpty() && bef.peek() > 0 && bef.peek() < -asteroids[i]) bef.pop();
                if (!bef.isEmpty() && bef.peek() == -asteroids[i]) bef.pop();
                else if(bef.isEmpty() ||bef.peek() < 0) bef.push(asteroids[i]);
            }
        }
        int size = bef.size();
        int[] res = new int[size];
        for (int i = size - 1; i >= 0 ; i--) {
            res[i] = bef.pop();
        }
        return res;
    }
}
