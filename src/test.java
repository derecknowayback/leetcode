import java.util.ArrayDeque;

public class test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String a = "abbcccdd", b = "de";
        System.out.println(solution.maxSequnce(a,b));

        a = "ddddddd";
        b = "dddddddddddddd";
        System.out.println(solution.maxSequnce(a,b));


        a = "c";
        b = "d";
        System.out.println(solution.maxSequnce(a,b));


        a = "abcueidheiudeoaededevdb";
        b = "okkkdednbvsifvuhed";
        System.out.println(solution.maxSequnce(a,b));
    }
}

class Solution {

    String res;

    public String maxSequnce(String a,String b) {
        res = "";
        if(a.length() > b.length()) return maxSequnce(b,a);
        int left = 0, right = a.length();
        while (left < right){
            int mid = (left + right) >> 1 ;
            if(check(a,b,mid))
                left = mid + 1;
            else
                right = mid;
        }
        return res;
    }
    public boolean check(String a,String b,int len){
        for (int i = 0; i < len; i++) {
            String substring = a.substring(i, i + len);
            if(b.contains(substring)){
                res = substring;
                return true;
            }
        }
        return false;
    }
}
