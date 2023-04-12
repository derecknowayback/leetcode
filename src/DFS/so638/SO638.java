package DFS.so638;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SO638 {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(2, 5);
        List<List<Integer>> lists = Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10));
        new Solution().shoppingOffers(integers,lists,Arrays.asList(3,2));
    }
}
class Solution {

    List<Integer> price;
    List<List<Integer>> special;

    public int shoppingOffers(List<Integer> _price, List<List<Integer>> _special, List<Integer> _needs) {
        price = _price;
        special = _special;
        return dfs(_needs);
    }

    public int dfs(List<Integer> needs){
        int cost = Integer.MAX_VALUE;
        for (int i = 0; i < special.size(); i++) {
            List<Integer> packet = special.get(i);
            List<Integer> nxtNeeds = new ArrayList<>();
            boolean isOkToBuyPacket = true;
            for (int j = 0; j < packet.size() - 1; j++) {
                int num = packet.get(j), need = needs.get(j);
                if(num <= need){
                    nxtNeeds.add(need - num);
                }else {
                    isOkToBuyPacket = false;
                    break;
                }
            }
            if(isOkToBuyPacket && packet.get(packet.size() - 1) < cost){
                cost = Math.min(cost,packet.get(packet.size() - 1) + dfs(nxtNeeds));
            }
        }
        int buySingle = 0;
        for (int i = 0; i < needs.size(); i++)
            buySingle += price.get(i) * needs.get(i);
        return Math.min(cost,buySingle);
    }
}
