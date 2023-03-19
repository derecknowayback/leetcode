package weekly.w337;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] arr = {{0,11,16,5,20},{17,4,19,10,15},{12,1,8,21,6},{3,18,23,14,9},{24,13,2,7,22}};
        int[] arr1 = {2,4,6};
        System.out.println(new Solution().beautifulSubsets(arr1,2));
    }
//    输入：n = 17
//    输出：[2,0]
//    解释：17 的二进制形式是 10001 。
//    下标 0 和 下标 4 对应的值为 1 。
//    共有 2 个偶数下标，0 个奇数下标。
    public int[] evenOddBit(int n) {
        int even = 0, odd = 0;
        for (int i = 0; i < 12; i++) {
            int mask = 1 << i;
            if((mask & n) != 0){
                if(i % 2 == 1)
                    odd++;
                else
                    even++;
            }
        }
        return new int[]{even,odd};
    }


    // 模拟题
    int [][]direction = new int[][]{
            {1,2},
            {2,1},
            {2,-1},
            {1,-2},
            {-1,-2},
            {-2,-1},
            {-2,1},
            {-1,2}
    };
    public boolean checkValidGrid(int[][] grid) {
        int last = grid.length * grid.length - 1;
        int num = 0, row = 0, col = 0;
        // 从左上角开始
        while (true){
            int hasFound = 0;
            for (int i = 0; i < direction.length; i++) {
                if(checkValid(direction[i],row,col,grid.length) && grid[row + direction[i][0]][col + direction[i][1]] == num + 1){
                    row = row + direction[i][0];
                    col = col + direction[i][1];
                    num = grid[row][col];
                    hasFound = 1;
                    if(num == last) return true;
                }
            }
            if(hasFound == 0) return false;
        }
    }

    boolean checkValid(int[] dir, int row,int col,int n){
        return (dir[0] + row >= 0 && dir[0] + row < n)
                && (dir[1] + col >= 0 && dir[1] + col < n);
    }


    int res = 0;
    List<Integer> list;

    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        list = new ArrayList<>();
        bfs(0,nums,k);
        return res;
    }

    // 从前往后遍历，每个位置判断要不要带
    public void bfs(int index,int[] nums,int diff){
        if(index == nums.length) return;
        // 不带
        bfs(index+1,nums,diff);
        int k = nums[index];
        boolean flag = true;
        for (int i = list.size() - 1 ; i >= 0 ; i--) {
            if (k - list.get(i) == diff){
                flag = false;
                break;
            }
        }
        // 带
        if(flag){
            list.add(k);
            res ++;
            bfs(index+1,nums,diff);
        }
        list.remove((Integer) k);
    }





    public int findSmallestInteger(int[] nums, int value) {
        List<Deque<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < value; i++) buckets.add(new ArrayDeque<>());
        for (int j : nums) {
            int hash = Math.abs(j % value);
            if(j < 0) hash = value - hash;
            buckets.get(hash).add(j);
        }
        int num = 0;
        while (true){
            int bucketNum = Math.abs(num % value);
            Deque<Integer> bucket = buckets.get(bucketNum);
            if(bucket.size() == 0) break;
            bucket.pop(); // pop一下
            num ++;
        }
        return num;
    }
}
