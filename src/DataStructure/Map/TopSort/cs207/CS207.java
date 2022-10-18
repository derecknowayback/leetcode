package DataStructure.Map.TopSort.cs207;

import java.util.ArrayDeque;

public class CS207 {
}
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 先构建一个入度数组
        int[] inDegree = new int[numCourses];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[][] map = new int[numCourses][numCourses];
        int count = 0;
        for (int i = 0; i < prerequisites.length; i++) {
            int []temp = prerequisites[i];
            int a = temp[0] , b = temp[1];
            map[b][a] = 1;
            inDegree[a]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0)
                queue.addLast(i);
        }
        while (!queue.isEmpty()){
            int pop = queue.pop();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if(i == pop) continue;
                if(map[pop][i] == 1){
                    if(--inDegree[i] == 0)
                    queue.addLast(i);
                }
            }
        }
        return count == numCourses;
    }
}
