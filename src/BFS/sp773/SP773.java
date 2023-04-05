package BFS.sp773;

import java.util.*;

public class SP773 {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{5,4,0}};
        System.out.println(new Solution().slidingPuzzle(arr));
    }
}
class Solution {

    static final int[][] dict = {
            {1,3}, // 0
            {0,2,4}, // 1
            {1,5}, // 2
            {0,4}, // 3
            {1,3,5}, // 4
            {2,4} // 5
    };

    final String target = "123450";

    public int slidingPuzzle(int[][] board) {
        HashMap<String, Integer> dis = new HashMap<>();
        // 转换为字符串;
        final StringBuffer srcBuffer = new StringBuffer();
        for (int[] row : board) {
            for (int t : row)
                srcBuffer.append(t);
        }
        final String src = srcBuffer.toString();
        Queue<StringBuffer> queue = new LinkedList<>();
        queue.offer(new StringBuffer(target));
        dis.put(target,0);
        while (!queue.isEmpty()){
            StringBuffer poll = queue.poll();
            String str = poll.toString();
            int tempDis = dis.get(str);
            System.out.println("数组："+poll + " ||   步数: " + tempDis);
            if(src.equals(str))
                return tempDis;
            // 遍历棋盘
            int index = str.indexOf("0");
            for (int j = 0; j < dict[index].length; j++) {
                swap(poll,index,dict[index][j]);
                StringBuffer next = new StringBuffer(poll);
                String nextStr = next.toString();
                if(!dis.containsKey(nextStr)){
                    dis.put(nextStr,tempDis+1);
                    queue.offer(next);
                }
                swap(poll,dict[index][j],index);
            }
        }
        return -1;
    }

    public void swap(StringBuffer buf,int a,int b){
        char a1 = buf.charAt(a),b1 = buf.charAt(b);
        buf.setCharAt(a,b1);
        buf.setCharAt(b,a1);
    }

}
