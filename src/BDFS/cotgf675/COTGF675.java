package BDFS.cotgf675;

import javafx.util.Pair;

import java.util.*;

public class COTGF675 {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{0,0,4},{7,6,5}};
        ArrayList<List<Integer>> lists = new ArrayList<>();
        for (int[] k : arr) {
            ArrayList<Integer> integers = new ArrayList<>();
            for (int temp : k) integers.add(temp);
            lists.add(integers);
        }
        System.out.println(new Solution().cutOffTree(lists));
    }
}
class Solution {


    static final int[][]directions ={
            {-1,0},// 上
            {1,0},// 下
            {0,-1},// 左
            {0,1}// 右
    };

    public int cutOffTree(List<List<Integer>> forest) {
        // 利好的信息是我们不会碰到一样的树高
        int m = forest.size(), n = forest.get(0).size();
        int [][]forestArr = new int[m][n];
        int maxx = 0, maxy = 0;
        Queue<Integer> trees = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int  k = forest.get(i).get(j);
                forestArr[i][j] = k;
                if(k != 0 && k != 1) {
                    if(forest.get(maxx).get(maxy) < forest.get(i).get(j)){
                        maxx = i;
                        maxy = j;
                    }
                    trees.add(k);
                }
            }
        }
        int [][]dis = new int[m][n];
        for (int[] row : dis) Arrays.fill(row,-1);
        // bfs
        dis[0][0] = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(0,0));
        int time = 0;
        while (!queue.isEmpty() && !trees.isEmpty()){
            Pair<Integer, Integer> pos = queue.poll();
            int x = pos.getKey(), y = pos.getValue();
            if(forestArr[x][y] == trees.peek()){
                trees.poll();
                forestArr[x][y] = 1;
                // make clear
                for (int[] row : dis) Arrays.fill(row,-1);
                while(!queue.isEmpty()) queue.poll();
            }
            for (int i = 0; i < directions.length; i++) {
                int x2 = x+directions[i][0], y2 = y+directions[i][1];
                if(check(x2,y2,m,n) && forestArr[x2][y2] != 0 && dis[x2][y2] != -1){
                    // 加的只会是树或者路
                    queue.offer(new Pair<>(x2,y2));
                    dis[x2][y2] = dis[x][y] + 1;
                }
            }
        }
        return !trees.isEmpty() ? -1 : dis[maxx][maxy];
    }

    public boolean check(int x,int y,int m,int n){
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
}
