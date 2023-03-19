package DataStructure.StringHash.lds1044;

import java.util.HashSet;
import java.util.Set;

public class LDS1044 {
    public static void main(String[] args) {
        new Solution().longestDupSubstring("banana");
    }
}
class Solution {

    int PRIME = 31;
    String res;

    public String longestDupSubstring(String s) {
        res = "";
        int l = 0, r = s.length() - 1;
        while (l <= r) {
            // mid 是要找的长度
            int mid = l + r + 1 >> 1;
            // 看是否能找到这么长的重复子串
            String k = check(s, mid);
            if (!"".equals(k)){
                res = k;
                l = mid + 1;
            }
            else
                r = mid - 1;
        }
        return res;
    }


    private String check(String s, int len) {
        Set<Long> set = new HashSet<>();
        long hash = 0;
        long power = 1;
        // 先计算前len个字符组成的子串的hash
        // 与java的String的hashCode()方法一样
        for (int i = 0; i < len; i++) {
            hash = hash * PRIME + s.charAt(i);
            power *= PRIME;
        }
        set.add(hash);
        // 滑动窗口向后移，每次移动要把移出窗口的减去，再加上新的
        for (int i = len; i < s.length(); i++) {
            if(!(power == (long)Math.pow(PRIME,len))){
                System.out.println("power  == pow ? :" + (power == (long)Math.pow(PRIME,len)));
                System.out.println("power: " + power);
                System.out.println("pow: " + (long)Math.pow(PRIME,len));

            }
            hash = hash * PRIME + s.charAt(i) - power * s.charAt(i - len);
            if (set.contains(hash)) {
                return s.substring(i - len + 1, i + 1);
            }
            set.add(hash);
        }

        return "";
    }
}