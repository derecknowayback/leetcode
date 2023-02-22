
public class test {
    public static void main(String[] args) {
        int[][] ints = new int[3][];
        ints[0] = new int[]{4, 6};
        ints[1] = new int[]{2, 2};
        ints[2] = new int[]{1, 3};

    }
}
class Solution {
    public boolean areNumbersAscending(String s) {
        if(s == null || s.length() == 0) return false;
        String[] strings = s.split(" ");
        int bef = 0;
        for (int i = 0; i < strings.length; i++) {
            if(check(strings[i])){
                int temp = Integer.valueOf(strings[i]);
                if(temp <= bef) return false;
                bef = temp;
            }
        }
        return true;
    }
    public boolean check(String s){
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!(c >= '0' && c <= '9')) return false;
        }
        return true;
    }
}
