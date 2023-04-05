//package BDFS.smtrd2045;
//
//import javafx.util.Pair;
//
//import java.util.*;
//
//public class SMTRD2045 {
//    public static void main(String[] args) {
//        int n = 6;
//        int [][]arr = {{1,2},{1,3},{2,4},{3,5},{4,5},{4,6}};
//        int time = 3;
//        int change = 100;
//        System.out.println(new Solution().secondMinimum(n, arr, time, change));
//    }
//
//}
//
//class Solution {
//
//    PriorityQueue<Integer> res;
//
//    HashMap<Integer, List<Integer>> map;
//
//    HashSet<Integer> memory;
//
//    int cost;
//
//    int change;
//
//    public int secondMinimum(int n, int[][] edges, int time, int change) {
//        res = new PriorityQueue<>();
//        map = new HashMap<>(n);
//        memory = new HashSet<>(n+1);
//        Arrays.fill(memoryDfs,Integer.MAX_VALUE);
//        boolean []visited = new   boolean[n+1];
//        cost = time;
//        this.change = change;
//        for (int[] edge : edges) {
//            int a = edge[0], b = edge[1];
//            List<Integer> aList = map.getOrDefault(a, new ArrayList<>());
//            aList.add(b);
//            map.put(a,aList);
//            List<Integer> bList = map.getOrDefault(b, new ArrayList<>());
//            bList.add(a);
//            map.put(b,bList);
//        }
//        // 先正向不回头搜一遍，返回一个sorted-list
//        dfs(visited,1,n,0);
//        if(res.size() == 0) return -1; // 不连通 -1
//        int minTime = res.peek();
//        while (!res.isEmpty() && res.peek() == minTime) res.poll();
//        if(!res.isEmpty())  return res.poll();
//        else {
//            // 回最近的节点, 又因为所有节点距离一样，所以哪个都行；
//            minTime += waitTime(minTime);
//            minTime += cost;
//            minTime += waitTime(minTime);
//            minTime += cost;
//            return minTime;
//        }
//    }
//
//    public void dfs(boolean []visited,int pos,int target,int time){
//        if(visited[pos]) return ;
//        if(target == pos){
//            res.add(time);
//            return;
//        }
//        visited[pos] = true;
//        memory.add(pos * 131 + time);
//        // 检查是否在红灯
//        time += waitTime(time);
//        List<Integer> neighbors = map.get(pos);
//        for (int nxt : neighbors) {
//            if(!visited[nxt] && memory.contains()){
//                dfs(visited, nxt, target,time+cost);
//            }
//        }
//        visited[pos] = false;
//    }
//
//    public int waitTime(int time){
//        if((time / change) % 2 == 1) // shouldWait
//            return change - (time % change);
//        return 0;
//    }
//}
