package BFS.pawf417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PAWF417 {
    public static void main(String[] args) {
        int [][]arr = {{1,2,3,4,5},{16,17,18,19,6},{15,24,25,20,7},{14,23,22,21,8},{13,12,11,10,9}};
        int [][]arr1 = {{1,1},{1,1}};
        System.out.println(new Solution().pacificAtlantic(arr).size());
    }
}
class Solution {
    // 记忆化 DFS ?
    List<List<Integer>> res;
    Boolean[][] Pacific;
    Boolean[][] Atlantic;

    boolean[][] visited;

    static final int [][]directions1 = new int[][]{
            {-1,0}, // 上
            {0,-1}, // 左
            {1,0}, // 下
            {0,1}, // 右
    };

    static final int [][]directions2 = new int[][]{
            {1,0}, // 下
            {0,1}, // 右
            {0,-1}, // 左
            {-1,0}, // 上
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        res = new ArrayList<>();
        Pacific = new Boolean[m][n];
        Atlantic = new Boolean[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfsPacific(i,j,heights);
            }
        }
        for (boolean[] k : visited) Arrays.fill(k,false);
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0 ; j--) {
                dfsAtlantic(i,j,heights);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(Pacific[i][j] && Atlantic[i][j]){
                    res.add(Arrays.asList(i,j));
                }
            }
        }
        return res;
    }


    private boolean dfsPacific(int x,int y,int[][] arr){
        if(Pacific[x][y] != null) return Pacific[x][y];
        if(visited[x][y]) return false;
        visited[x][y] = true;
        boolean res = false;
        int m = arr.length, n = arr[0].length;
        for (int i = 0; i < directions1.length; i++) {
            int newx = x + directions1[i][0], newy = y + directions1[i][1];
            // 列小于0，行小于0
            if(checkPacific(newx,newy,m,n)) {
                Pacific[x][y] = true;
                return true;
            }
            // !(i == 0 && Pacific[newx][newy] == null) && !(i == 1 && Pacific[newx][newy] == null)
            // 如果是个合法范围:
            if(checkValid(newx,newy,m,n) && arr[x][y] >= arr[newx][newy]){
                res |= dfsPacific(newx,newy,arr);
                if(res) break;
            }
        }
        Pacific[x][y] = res;
        return res;
    }

    private boolean dfsAtlantic(int x, int y,int[][] arr) {
        if(Atlantic[x][y] != null) return Atlantic[x][y];
        if(visited[x][y]) return false;
        visited[x][y] = true;
        boolean res = false;
        int m = arr.length, n = arr[0].length;
        for (int i = 0; i < directions2.length; i++) {
            int newx = x + directions2[i][0], newy = y + directions2[i][1];
            // 列小于0，行小于0
            if(checkAtlantic(newx,newy,m,n)) {
                Atlantic[x][y] = true;
                return true;
            }
            // 如果是个合法范围:
            if(checkValid(newx,newy,m,n) && arr[x][y] >= arr[newx][newy]
                    && !(i == 0 && Atlantic[newx][newy] == null) && !(i == 1 && Atlantic[newx][newy] == null)){
                res |= dfsAtlantic(newx,newy,arr);
                if(res) break;
            }
        }
        Atlantic[x][y] = res;
        return res;
    }

    public boolean checkPacific(int x,int y,int m,int n){
        return (y == -1 && x >= 0 && x < m)
                || (x == -1 && y >= -1 && y <= n);
    }

    public boolean checkAtlantic(int x,int y,int m,int n){
        return (y == n && x >= 0 && x < m)
                || (x == m && y >= -1 && y <= n);
    }

    public boolean checkValid(int x,int y,int m,int n){
        return (x >=0 && x < m) && (y >=0 && y <n);
    }
}
