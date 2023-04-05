package BFS.wl127;

import java.util.*;

public class WL127 {
    public static void main(String[] args) {
        String []arr  = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
        String a = "qa", b = "sq";
        System.out.println(new Solution().ladderLength(a, b, Arrays.asList(arr)));
    }
}


class Solution {

    HashMap<String,Integer> string2id = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int numString = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        // 建图
        for (String s : wordList) addEdge(s);
        addEdge(beginWord); // 将出发点加入图

        int[] dis = new int[numString];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = string2id.get(beginWord), endId = string2id.get(endWord);
        dis[beginId] = 0;

        // 开始bfs
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(beginId);
        while (!queue.isEmpty()){
            int temp = queue.poll();
            if(temp == endId){
                return dis[endId] / 2 + 1; // 除2是因为虚拟节点出去和进来是两次；
            }
            for (int k : edge.get(temp)) {
                // 没有访问过
                if(dis[k] == Integer.MAX_VALUE){
                    dis[k] = dis[temp] + 1;
                    queue.offer(k);
                }
            }
        }
        return 0;
    }

    public void addEdge(String s){
        addWord(s);
        int id1 = string2id.get(s);
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char origin = chars[i];
            chars[i] = '*';
            String k = new String(chars);
            addWord(k);
            chars[i] = origin;
            int id2 = string2id.get(k);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
        }
    }

    public void addWord(String s){
        if(!string2id.containsKey(s)){
            string2id.put(s,numString++);
            edge.add(new ArrayList<>()); // id和edge中的位置是一一对应的;
        }
    }




}