package BFS.cab1034;

import javafx.util.Pair;

import java.util.*;

public class CAB1034 {
    public static void main(String[] args) {
        int [][]arr = {{1,1},{1,2}};
        int[][] ints = new Solution().colorBorder(arr, 0, 0, 3);
        System.out.println(Arrays.toString(ints));
    }
}
class Solution {

    final static int[][] directions = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1},
    };


    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        List<Pair<Integer, Integer>> toColor = new ArrayList<>();
        boolean [][] visited = new boolean[m][n];
        // bfs
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(row,col));
        visited[row][col] = true;
        int self = grid[row][col];
        while (!queue.isEmpty()){
            Pair<Integer, Integer> poll = queue.poll();
            int x = poll.getKey(), y = poll.getValue();
            if(checkEdge(grid,x,y)) toColor.add(new Pair<>(x,y));
            for (int i = 0; i < directions.length; i++) {
                 int nxtX = x + directions[i][0], ntxY = y + directions[i][1];
                 if(checkValid(m,n,nxtX,ntxY) && grid[nxtX][ntxY] == self && !visited[nxtX][ntxY]){
                     queue.offer(new Pair<>(nxtX,ntxY));
                     visited[nxtX][ntxY] = true;
                 }
            }
        }
        System.out.println();
        for (Pair<Integer, Integer> pos : toColor)
            grid[pos.getKey()][pos.getValue()] = color;
        return grid;
    }


    public boolean checkValid(int m,int n,int x,int y){
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }

    public boolean checkEdge(int[][] grid,int x,int y){
        int m = grid.length, n = grid[0].length, self = grid[x][y];
        boolean around = false;
        for (int i = 0; i < directions.length; i++) {
            int nxtX = x + directions[i][0], nxtY = y + directions[i][1];
            if(!checkValid(m,n,nxtX,nxtY)) return true;
            if(grid[nxtX][nxtY] != self) around = true;
        }
        return around;
    }
}
