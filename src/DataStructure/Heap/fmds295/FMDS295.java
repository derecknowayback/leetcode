package DataStructure.Heap.fmds295;


import java.util.PriorityQueue;

public class FMDS295 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        int a = 0;
    }
}
class MedianFinder {
    PriorityQueue<Integer> l = new PriorityQueue<>((a,b)->b-a);
    PriorityQueue<Integer> r = new PriorityQueue<>((a,b)->a-b);
    public void addNum(int num) {
        int s1 = l.size(), s2 = r.size();
        //每次都要考虑 加入的数 和 两个堆顶数 的关系
        if(s1 == s2){
            // 先和右堆比较，如果num >= 右堆顶（等于可以不写）：
            if(!r.isEmpty() && r.peek() <= num){
                l.add(r.poll());
                r.add(num);
            }
            //如果 num < 右堆顶
            else {
                l.add(num);
            }
        }else{
            // 左边比右边多了1个元素，所以加入到右边维持平衡
            // 和左堆顶比较，如果左堆顶 <= num（等于可以不写）
            if(l.peek() <= num)
                r.add(num);
            else {
                r.add(l.poll());
                l.add(num);
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
