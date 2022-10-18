package DFS.lipm329;

public class LIPM329 {
}
class Solution {

    public static int res = 0;

    public int longestIncreasingPath(int[][] matrix) {
        res = 0;
        dfs(matrix,0,0);
        return 0;
    }
    public void dfs(int[][] matrix, int r,int c){
        if(outOfBound(matrix, r, c)) return;

    }

    public boolean outOfBound(int[][] matrix, int r,int c){
        int m = matrix.length,  n = matrix[0].length;
        boolean b1 = r < 0 || r >= m;
        boolean b2 = c < 0 || c >= n;
        return !(b1 || b2);
    }

}
