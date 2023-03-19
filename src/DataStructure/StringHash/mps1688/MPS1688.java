package DataStructure.StringHash.mps1688;

public class MPS1688 {
}
class Solution {
    public int maxRepeating(String sequence, String word) {
        int res = 0;
        String origin = word;
        while (word.length() < sequence.length()){
            int i = sequence.indexOf(word);
            if(i == -1) break;
            word += origin;
            res++;
        }
        return res;
    }

}