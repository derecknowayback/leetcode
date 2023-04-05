package BFS.br815;

import javafx.util.Pair;

import java.util.*;

public class BR815 {
}

class Solution {

    HashMap<Integer, List<Integer>> stop2routes;

    public int numBusesToDestination(int[][] routes, int source, int target) {
        stop2routes = new HashMap<>();
        int routeNum = routes.length;
        boolean[][] visited = new boolean[routeNum][];
        boolean hasFound = false;
        for (int i = 0; i < routeNum; i++){
            visited[i] = new boolean[routes[i].length];
            for (int j = 0; j < routes[i].length; j++) {
                if(routes[i][j] == target) hasFound = true;
                addStop(routes[i][j],i);
            }
        }
        if(!hasFound) return -1;
        // 开始bfs
        Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(source,0));
        while (!queue.isEmpty()){
            Pair<Integer, Integer> pair = queue.poll();
            int stop = pair.getKey(), step = pair.getValue();
            if(stop == target){
                return step;
            }
            List<Integer> relatedRoutes = stop2routes.get(stop);
            for (int route : relatedRoutes) {
                for (int i = 0; i < routes[route].length; i++) {
                    if(!visited[route][i]){
                        visited[route][i] = true;
                        queue.offer(new Pair<>(routes[route][i],step+1));
                    }
                }
            }
        }
        return -1;
    }

    public void addStop(int stop,int route){
        List<Integer> routes = stop2routes.computeIfAbsent(stop, k -> new ArrayList<>());
        routes.add(route);
    }
}
