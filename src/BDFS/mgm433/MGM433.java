package BDFS.mgm433;

import java.util.*;

public class MGM433 {
    public static void main(String[] args) {
        String s1 = "AACCTTGG";
        String s2 = "AATTCCGG";
        String []arr = {"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"};
        System.out.println(new Solution().minMutation(s1, s2, arr));
    }
}
class Solution {

    HashMap<String,Integer> string2id;
    List<List<Integer>> edges;
    int edgeId;

    public int minMutation(String startGene, String endGene, String[] bank) {
        // init global
        string2id = new HashMap<>();
        edges = new ArrayList<>();
        edgeId = 0;
        // 建图
        addWord(startGene);
        boolean flag = false;
        for (String s : bank){
            if(s.equals(endGene)) flag = true;
            addWord(s);
        }
        if(!flag) return -1; // 如果目标基因不在基因库里面;
        if(startGene.equals(endGene)) return 0; // 特殊情况，相同

        // DEBUG
        HashMap<Integer, String> debugMap = new HashMap<>();
        for (String s : string2id.keySet()) {
            debugMap.put(string2id.get(s),s);
        }
        // ENDDEBUG

        // bfs
        Queue<Integer> queue = new LinkedList<>();
        int[] dis = new int[edges.size()];
        Arrays.fill(dis,-1);
        int targetId = string2id.get(endGene),startId = string2id.get(startGene);
        queue.offer(startId);
        dis[startId] = 0;
        while (!queue.isEmpty()){
            int index = queue.poll();
            System.out.println(debugMap.get(index));
            if(index == targetId) break;
            for (int i : edges.get(index)){
                if(dis[i] == -1){
                    queue.offer(i);
                    dis[i] = dis[index] + 1;
                }
            }
        }
        return dis[targetId] / 2;
    }

    public void addWord(String s){
        addEdge(s);
        int sId = string2id.get(s);
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char origin = charArray[i];
            charArray[i] = '*';
            String temp = new String(charArray);
            addEdge(temp);
            int tempId = string2id.get(temp);
            edges.get(tempId).add(sId);
            edges.get(sId).add(tempId);
            charArray[i] = origin;
        }
    }

    public void addEdge(String s){
        if(!string2id.containsKey(s)){
            string2id.put(s,edgeId++);
            edges.add(new ArrayList<>());
        }
    }
}
