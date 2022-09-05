package DataStructure.Stack.vss946;

import java.util.ArrayDeque;

public class VSS946 {

}
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && popped[index] == stack.peek()){
                stack.pop();
                index++;
            }
        }
        return index == popped.length;
    }
}
