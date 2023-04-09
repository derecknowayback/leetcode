package DFS.eao282;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EAO282 {
    public static void main(String[] args) {
//        int a = 3+456+2*3*7-490;
//        System.out.println(a); // 1
        Solution solution = new Solution();
        System.out.println(solution.calculate("99999*99999",0,true));
    }
}

/**
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 解释: “1*2*3” 和 “1+2+3” 的值都是6。
 * 示例 2:
 *
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 解释: “2*3+2” 和 “2+3*2” 的值都是8。
 * 示例 3:
 *
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 * 解释: 表达式 “3456237490” 无法得到 9191 。
 */
class Solution {

    static long[][] arr = new long[10][10];

    static List<String> res = new ArrayList<>();

    public List<String> addOperators(String num, int target) {
        res.clear();
        preWork(num);
        dfs(num,target,0,new StringBuilder(),true);
        return res;
    }

    public void dfs(String num,int target,int index,StringBuilder now, boolean isFirst){
        if(index == num.length()){
            if(calculate(now.toString(),0,true) == target)
                res.add(now.toString());
            return;
        }
        for (int i = index; i < num.length(); i++) {
            long k = arr[index][i]; // 提取出数值
            int originLen = now.length();
            // 合法操作
            if(k != -1){
                // +
                if(isFirst) now.append(k);
                else now.append('+').append(k);
                dfs(num, target, i + 1, now,false);
                now.setLength(originLen); // truncate
                if(isFirst) continue;

                // -
                now.append('-').append(k);
                dfs(num, target, i + 1, now,false);
                now.setLength(originLen); // truncate

                // *
                now.append('*').append(k);
                dfs(num, target, i + 1, now,false);
                now.setLength(originLen); // truncate
            }
        }
    }

    public void preWork(String num){
        int len = num.length();
        for (long[] a : arr) Arrays.fill(a,-1);
        for (int i = 0; i < len; i++) {
            if(num.charAt(i) == '0'){
                arr[i][i] = 0;
            }else {
                for (int j = i; j < len; j++) {
                    String s = num.substring(i, j + 1);
                    if(!(s.length() >= 10 && s.compareTo("2147483647") > 0))
                        arr[i][j] = Integer.parseInt(s);
                }
            }
        }
    }

    // "1*2*3*4*5-6+78-9"
    public long calculate(String s,int now,boolean isAdd){
        int res = 1, start = 0;
        boolean hasMulti = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) continue;
            int k = Integer.parseInt(s.substring(start,i));
            res *= k;
            if(c == '+'){
                if(isAdd) now += res;
                else now -= res;
                return calculate(s.substring(i+1),now,true);
            } else if (c == '-') {
                if(isAdd) now += res;
                else now -= res;
                return calculate(s.substring(i+1),now,false);
            }else {
                start = i + 1;
                hasMulti = true;
            }
        }

        if(hasMulti){
            if(!isAdd)
                return -res * Integer.parseInt(s.substring(start)) + now;
            else
                return res * Integer.parseInt(s.substring(start)) + now;
        }
        if(!isAdd)
            return -Integer.parseInt(s) + now;
        return Integer.parseInt(s) + now;
    }
}