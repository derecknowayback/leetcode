package DataStructure.StringHash.rds187;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RDS187 {
    public static void main(String[] args) {
        new Solution().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
    }
}
class Solution {
    // A:  00
    // C:  01
    // G: 10
    // T:  11
    public static final int arr[] = new int[]{0,1,2,3};
    public static final int mask = (1 << 20) - 1;


    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        HashSet<String> resSet = new HashSet<>();
        HashSet<Integer> times = new HashSet<>();
        if(s == null || s.length() <= 10) return res;
        int temp = 0;
        for (int i = 0; i < 10; i++)
            temp = addChar(temp, s.charAt(i));
        times.add(temp);
        for (int i = 10; i < s.length(); i++) {
            temp = addChar(temp, s.charAt(i));
            if(times.contains(temp)){
                resSet.add(s.substring(i - 9,i + 1));
            }
            times.add(temp);
        }
        res.addAll(resSet);
        return res;
    }

    public int addChar(int origin, char c){
        int k = 0;
        switch (c){
            case 'A': k = arr[0];break;
            case 'C': k = arr[1];break;
            case 'G': k = arr[2];break;
            case 'T': k = arr[3];break;
        }
        origin = origin << 2;
        origin = origin | k;
        return origin & mask;
    }
}
