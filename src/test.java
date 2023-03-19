
public class test {
    public static void main(String[] args) {
        int []arr = new int[]{2,3,1,2,3,3,3,3};
        new Solution().movesToMakeZigzag(arr);
    }
}

class Solution {

    public int movesToMakeZigzag(int[] nums) {
        int len = nums.length;
        int odd = 0, even = 0;
        for (int i = 0; i < len; i ++) {
            int left = i == 0 ? 0 : (nums[i - 1] > nums[i] ? 0 : nums[i] - nums[i-1] + 1);
            int right = i == len - 1 ? 0 : (nums[i+1] > nums[i] ? 0 : nums[i] - nums[i+1] + 1);
            if(i % 2 == 0){
                odd += Math.max(left,right);
            }else{
                even += Math.max(left,right);
            }
        }
        return Math.min(odd,even);
    }
}
