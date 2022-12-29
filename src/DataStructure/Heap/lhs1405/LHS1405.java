package DataStructure.Heap.lhs1405;





public class LHS1405 {
    public static void main(String[] args) {
        new Solution().longestDiverseString(1,1,7);
    }
}
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        boolean change = false;
        while (a > 0 || b > 0 || c > 0){
            char character = 'a';
            if(!change){
                if(b > a || c > a){
                    if(b > c) character = 'b';
                    else character = 'c';
                }
            }
            else{
                if(res.charAt(res.length()-1) == 'a'){
                    character = 'b';
                    if(c > b) character = 'c';
                    if (c == 0 && b == 0) return res.toString();
                }
                if(res.charAt(res.length()-1) == 'b'){
                    character = 'a';
                    if(c > a) character = 'c';
                    if (c == 0 && a == 0) return res.toString();
                }
                if(res.charAt(res.length()-1) == 'c'){
                    character = 'b';
                    if(a > b) character = 'a';
                    if (a == 0 && b == 0) return res.toString();
                }
            }
            switch (character){
                case 'a': a--;break;
                case 'b': b--;break;
                case 'c': c--;break;
            }
            if(res.length() > 0 && character == res.charAt(res.length()-1)) change = true;
            else change = false;
            res.append(character);
        }
        return res.toString();
    }
}