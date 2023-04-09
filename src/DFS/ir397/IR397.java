package DFS.ir397;

import java.util.ArrayDeque;
import java.util.HashSet;

public class IR397 {
    public static void main(String[] args) {
        System.out.println(new Solution().integerReplacement(Integer.MAX_VALUE));
    }
}
class Solution {
    public int integerReplacement(int n) {
        HashSet<Long> visited = new HashSet<>();
        ArrayDeque<Long> queue = new ArrayDeque<>();
        int steps = 0;
        queue.offer((long) n);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0) {
                long k = queue.poll();
                if(k == 1) return steps;
                if(k % 2 == 0 && !visited.contains(k / 2)){
                    queue.offer(k / 2);
                    visited.add(k/2);
                }else if(k % 2 == 1){
                    if(!visited.contains(k+1)){
                        queue.offer(k + 1);
                        visited.add(k + 1);
                    }
                    if(!visited.contains(k - 1)){
                        queue.offer(k - 1);
                        visited.add(k - 1);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}