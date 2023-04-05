package BFS.spgask864;

import java.util.*;

public class SPGASK864 {
    public static void main(String[] args) {
        String []arr = {"@...a",".###A","b.BCc"};
        System.out.println(new Solution().shortestPathAllKeys(arr));
    }
}
class Solution {

    public final static int[][] directions = new int[][]{
            {-1,0},// 上
            {1,0},// 下
            {0,-1},// 左
            {0,1},// 右
    };

    int k;

    class State{
        int state;
        int x;
        int y;

        public State(int x,int y,int state){
            this.x = x;
            this.y = y;
            this.state = state;
        }
        
        public boolean isKeyHold(char c){
            int index = c - 'a';
            return (state & (1 << index)) != 0;
        }
        
        public boolean isFull(){
            for (int i = 0; i < k; i++) {
                if((state & (1 << i)) == 0){
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return Integer.hashCode(x) + Integer.hashCode(y) + Integer.hashCode(state);
        }

        @Override
        public boolean equals(Object obj) {
            if(! (obj instanceof  State)) return false;
            State another = (State) obj;
            return  x == another.x && y == another.y && state == another.state;
        }
    }

    public int setKey(char c,int originState){
        int index = c - 'a';
        return  originState | (1 << index);
    }
    
    public int shortestPathAllKeys(String[] grid) {
        char[][] map = makeCharArray(grid);
        int startX = -1, startY = -1;
        k = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == '@'){
                    startX = i;
                    startY = j;
                }else if(isKey(map[i][j])){
                    k++;
                }
            }
        }
        return bfs(map,startX,startY);
    }

    public char[][] makeCharArray(String[] grid){
        char[][] res = new char[grid.length][];
        for (int i = 0; i < grid.length; i++)
            res[i] = grid[i].toCharArray();
        return res;
    }


    public int  bfs(char[][] map, int startX, int startY){
        int step = 0, m = map.length, n = map[0].length;
        Queue<State> queue = new ArrayDeque<>();
        HashSet<State> visited = new HashSet<>();
        State first = new State(startX, startY, 0);
        queue.offer(first);
        visited.add(first);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                State poll = queue.poll();
                int x = poll.x, y = poll.y, originState = poll.state;
                System.out.println("x: " + x + "  y: " + y + "  step:" + step);
                if(poll.isFull()) return step;
                // do something
                for (int i = 0; i < directions.length; i++) {
                    int nextX = x + directions[i][0], nextY = y + directions[i][1];
                    if(checkValid(nextX,nextY,m,n)){
                        if(isKey(map[nextX][nextY])){
                            int state = setKey(map[nextX][nextY], originState);
                            State nxtState = new State(nextX, nextY, state);
                            if(!visited.contains(nxtState)){
                                visited.add(nxtState);
                                queue.offer(nxtState);
                            }
                        }else if(isLock(map[nextX][nextY])){
                            char lower = (char) ('a' + (map[nextX][nextY] - 'A'));
                            if(poll.isKeyHold(lower)){
                                State nxtState = new State(nextX, nextY, originState);
                                if(!visited.contains(nxtState)){
                                    visited.add(nxtState);
                                    queue.offer(nxtState);
                                }
                            }
                        }else if(map[nextX][nextY] == '.' || map[nextX][nextY] == '@'){
                            State nxtState = new State(nextX, nextY,originState);
                            if(!visited.contains(nxtState)){
                                visited.add(nxtState);
                                queue.offer(nxtState);
                            }
                        }
                    }
                }
                size--;
            }
            step++;
        }
        return -1;
    }

    private boolean isLock(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isKey(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean checkValid(int nextX, int nextY, int m, int n) {
        return (nextX >= 0 && nextX < m) && (nextY >= 0 && nextY < n);
    }

}
