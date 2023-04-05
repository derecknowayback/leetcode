package DFS.ria93;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RIA93 {
    public static void main(String[] args) {
        System.out.println(new Solution().restoreIpAddresses("101023").size());
    }
}
class Solution {

    static Set<String> res = new HashSet<>();
    public List<String> restoreIpAddresses(String s) {
        res.clear();
        dfs(new ArrayDeque<>(),s,0);
        return new ArrayList<>(res);
    }

    public void dfs(ArrayDeque<Integer> arr,String origin,int index){
        // 处理0
        if(arr.size() == 4){
            StringBuilder builder = new StringBuilder();
            for (int k:arr) {
                builder.append(k).append(".");
            }
            if(builder.length() - 1 != origin.length() + 3) return;
            builder.setLength(builder.length()-1); // trunk last dot
            res.add(builder.toString());
            return;
        }
        int len = origin.length();
        if(index > len) return;
        for (int i = 1; i <= 3; i++) {
            int num = abstractNum(origin, i,index);
            if(num != -1){
                arr.offer(num);
                int offset = String.valueOf(num).length();
                dfs(arr, origin, index+offset);
                arr.pollLast();
            }
        }
    }

    public int abstractNum(String builder,int len,int index){
        int res;
        StringBuilder num = new StringBuilder(len);
        for (int i = index; i < builder.length() && num.length() < len; i++) {
            int digit = builder.charAt(i) - '0';
            // 前导0
            if(i == index && digit == 0){
                if(len != 1) return -1;
                return 0;
            }
            num.append(digit);
        }
        if(num.length() != len || (res = Integer.parseInt(num.toString())) > 255) return -1;
        return res;
    }

}