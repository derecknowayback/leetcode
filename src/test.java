import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        int[][] ints = new int[3][];
        ints[0] = new int[]{4, 6};
        ints[1] = new int[]{2, 2};
        ints[2] = new int[]{1, 3};

    }
}
class Solution {
//    public static int[] productQueries(int n, int[][] queries) {
//        //todo 分离出数字
//        ArrayList<Long> list = new ArrayList<>();
//        int pow = 0;
//        while (n != 0){
//            long temp =  ((long) n & 1) << pow;
//            if(temp != 0)
//                list.add(temp);
//            n = n >> 1;
//            pow ++;
//        }
//        long mod = (long)(Math.pow(10, 9) + 7);
//        // todo 构造前缀积数组
//        long[] preMulti = new long[list.size()];
//        preMulti[0] = list.get(0);
//        for (int i = 1; i < preMulti.length; i++) {
//            preMulti[i] = preMulti[i - 1] * list.get(i);
//        }
//        int[] res = new int[queries.length];
//        for (int i = 0; i < queries.length; i++) {
//            int []arr = queries[i];
//            long bef = arr[0] == 0 ? 1 : preMulti[arr[0]-1], after = preMulti[arr[1]];
//            res[i] = (int)(after / bef % mod);
//        }
//        return res;
//    }
}