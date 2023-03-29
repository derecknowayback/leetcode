package BDFS.ne1020;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

public class NE1020 {
}
class Solution {

    final static int[][] directions = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1},
    };

    // 多源bfs
    public int numEnclaves(int[][] grid) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        boolean [][]visited = new boolean[m][n];
        addEdgePoint(grid,visited,queue);
        while (!queue.isEmpty()){
            Pair<Integer, Integer> poll = queue.poll();
            int x = poll.getKey(), y = poll.getValue();
            for (int i = 0; i < directions.length; i++) {
                int nxtX = x + directions[i][0], nxtY = y + directions[i][1];
                if(!checkValid(m,n,nxtX,nxtY)) continue;
                if(grid[nxtX][nxtY] == 1 && !visited[nxtX][nxtY]){
                    queue.offer(new Pair<>(nxtX,nxtY));
                    visited[nxtX][nxtY] = true;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j] && grid[i][j] == 1) res++;
            }
        }
        return res;
    }

    public void addEdgePoint(int[][]grid,boolean[][] visited,Queue<Pair<Integer, Integer>> queue){
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < grid[0].length; i++) {
            if(grid[0][i] == 1) {
                queue.add(new Pair<>(0,i));
                visited[0][i] = true;
            }
            if(grid[m-1][i] == 1){
                queue.add(new Pair<>(m-1,i));
                visited[m-1][i] = true;
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if(grid[i][0] == 1) {
                queue.add(new Pair<>(i,0));
                visited[i][0] = true;
            }
            if(grid[i][n-1] == 1) {
                queue.add(new Pair<>(i,n-1));
                visited[i][n-1] = true;
            }
        }
    }

    public boolean checkValid(int m,int n,int x,int y){
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
}