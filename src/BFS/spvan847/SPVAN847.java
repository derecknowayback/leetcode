package BFS.spvan847;

import java.util.*;

public class SPVAN847 {
}
class Solution {



    public int shortestPathLength(int[][] graph) {
        // 上来先排序, 但是这样会丢掉节点的映射关系
        boolean[] visited = new boolean[graph.length];
        int steps = 0;
        int min = Integer.MAX_VALUE, startNode = -1;
        for (int i = 0; i < graph.length; i++) {
            int length = graph[i].length;
            if(min > length){
                min = length;
                startNode = i;
            }
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(startNode);
        while (!stack.isEmpty() && hasRemain(visited)){
            int node = stack.peek();
            int[] neighbors = graph[node];
            for (int i = 0; i < neighbors.length; i++) {
                if(!visited[neighbors[i]]){
                    stack.push(neighbors[i]);
                }
            }

        }
        return steps;
    }

    public boolean hasRemain(boolean[] visited){
        for (boolean b : visited) {
            if(!b) return true;
        }
        return false;
    }

}
