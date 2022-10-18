package DataStructure.Heap.swm480;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SWM480 {
    public static void main(String[] args) {
        int[] arr = {-2147483648,-2147483648,2147483647,-2147483648,-2147483648, -2147483648,2147483647,2147483647, 2147483647,2147483647,-2147483648,2147483647,-2147483648};
        System.out.println(Arrays.toString(new Solution().medianSlidingWindow(arr, 3)));
    }
}



class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len;
        if(nums == null || (len = nums.length) == 0) return new double[0];
        double[] res = new double[len - (k - 1)];
        int index = 0;
        for (int i = 0; i < k; i++) addNum(nums[i]);
        res[index++] = findMedian();
        for (int i = k; i < len; i++) {
            delete(nums[i - k]);
            addNum(nums[i]);
            res[index++] = findMedian();
        }
        return res;
    }

    /**
     * 注意这里的大坑，我们可能碰到大数的情况：-2147483648,2147483647
     * 所以我们使用 Long 而不是 Integer（其实用Integer可以，存的下，但是运算的时候很不方便）
     * 如果堆还是按照原先lambda "(a,b) -> (a-b)"的方法排序，会产生lambda溢出
     * 所以我们不能使用单纯的减法，要调用Java自带的compare方法
     */
    PriorityQueue<Long> l = new PriorityQueue<>((a, b)-> b.compareTo(a));
    PriorityQueue<Long> r = new PriorityQueue<>((a,b)-> a.compareTo(b));

    public void addNum(long num) {
        int s1 = l.size(), s2 = r.size();
        if (s1 == s2) {
            if (r.isEmpty() || num <= r.peek()) {
                l.add( num);
            } else {
                l.add(r.poll());
                r.add( num);
            }
        } else {
            if (l.peek() <= num) {
                r.add( num);
            } else {
                r.add(l.poll());
                l.add( num);
            }
        }
    }

    public void delete(long num){
        if(!l.isEmpty() && num <= l.peek()){
            l.remove(num);
            if(l.size() < r.size()){
                l.add(r.poll());
            }
        }else{
            r.remove(num);
            if(l.size() - r.size() > 1){
                r.add(l.poll());
            }
        }
    }

    public double findMedian() {
        int s1 = l.size(), s2 = r.size();
        if (s1 == s2) {
            return (l.peek() + r.peek()) / 2.0;
        } else {
            return l.peek();
        }
    }
}

/*[-2147483648,-2147483648,2147483647,-2147483648,-2147483648, -2147483648,2147483647,2147483647, 2147483647,2147483647,-2147483648,2147483647,-2147483648]
3*/