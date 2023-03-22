package BDFS.ol752;

import java.util.*;

public class OL752 {
    public static void main(String[] args) {
        String []arr = {"0201","0101","0102","1212","2002"};
        String []arr1 = {"0000"};
        String s = "0202";
        System.out.println(new Solution().openLock(arr1, s));
    }
}
class Solution {
    HashMap<String,Integer> string2dis;

    // 建图，碰到deadend节点不要加入图中;
    public int openLock(String[] deadends, String target) {
        string2dis = new HashMap<>();
        HashSet<String>  banned = new HashSet<>(Arrays.asList(deadends));
        if(banned.contains(target) || banned.contains("0000")) return -1;
        Queue<StringBuffer> queue = new LinkedList<>();
        queue.offer(new StringBuffer("0000"));
        string2dis.put("0000",0);
        while (!queue.isEmpty()) {
            StringBuffer poll = queue.poll();
            int temp = string2dis.get(poll.toString());
            if (target.equals(poll.toString()))
                return string2dis.get(target);
            for (int i = 0; i < poll.length(); i++) {
                int origin = poll.charAt(i) - '0';
                // -1
                char minus = (char) ((origin - 1 + 10) % 10 + '0');
                poll.setCharAt(i, minus);
                String mstr = poll.toString();
                if (!banned.contains(mstr) && !string2dis.containsKey(mstr)) {
                    queue.offer(new StringBuffer(poll));
                    string2dis.put(mstr,temp+1);
                }
                // +1
                char plus = (char) ((origin + 1) % 10 + '0');
                poll.setCharAt(i, plus);
                String pstr = poll.toString();
                if (!banned.contains(poll.toString()) && !string2dis.containsKey(pstr)) {
                    queue.offer(new StringBuffer(poll));
                    string2dis.put(pstr,temp+1);
                }
                poll.setCharAt(i, (char) (origin + '0'));
            }
        }
        return -1;
    }
}