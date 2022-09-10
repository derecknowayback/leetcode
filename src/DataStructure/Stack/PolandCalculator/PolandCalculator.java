package DataStructure.Stack.PolandCalculator;

import java.util.ArrayDeque;
import java.util.Scanner;

public class PolandCalculator {

    static Scanner scanner = new Scanner(System.in);
    /**
     * 规则:从左到右遍历表达式的每个数字和符号’遇到是数字就进栈’遇至｜」是符号’
     * 就将处干栈顶两个数字出栈’进行运算’运算结果进栈,～直到最终获得结果。
     * @param backForward: 原始字符串翻译后的后缀表达式
     * @return 返回后缀表达式的结果
      */
    private static int getAnswer(String backForward) throws Exception{
        int res = 0 , len = backForward.length();
        boolean isNeg = false;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; ) {
            char c = backForward.charAt(i);
            if(c == ' '){
                i++;
                continue;
            }
            if(isNum(c)){
                int k = i;
                while (k < len && isNum(backForward.charAt(k))) k++;
                int parseInt = Integer.parseInt(backForward.substring(i, k));
                if(isNeg) {
                    parseInt = -parseInt;
                    isNeg = false;
                }
                stack.push(parseInt);
                i = k;
            }
            else {
                if(c == '-' && i + 1 < len && isNum(backForward.charAt(i+1))){
                    isNeg = true;
                }
                else if(stack.size() >= 2 ){
                    int a = stack.pop() , b = stack.pop();
                    int newInt = doubleOperator(b, a, c);
                    stack.push(newInt);
                }
                i++;
            }
        }
        if(!stack.isEmpty())
            return stack.pop();
        return Integer.MIN_VALUE;
    }

    private static boolean isNum(char c){
        if(c >= '0' && c <= '9') return true;
        return false;
    }

    private static int doubleOperator(int a, int  b,char c){
        switch (c){
            case '*' : return a * b;
            case '+':return  a + b;
            case '-':return a - b;
            case '/': return a / b;
            default:throw new RuntimeException();
        }
    }

    /**
     * 规则:从左到右遍历中缀表达式的每个数字和符号,若是数字就输出’即成为后缀
     * 表达式的—部分;若是符号’则判断其与栈顶符号的优先级,是右括号或优先级不高于
     * 栈顶符号（乘除优先加减）｛栈顶元素依次出栈并输出,并将当前符号进栈 直到最
     * 终输出后缀表达式为止
     * @param row 等待翻译的原始字符串
     * @return 后缀表达式
     */
    private static String translateToBack(String row){
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        int len = row.length();
        boolean isNeg = false;
        for (int i = 0; i < len;) {
            char c =row.charAt(i);
            if(c == ' ') {
                i++;
                continue;
            }
            if(isNum(c)){
                int k = i;
                while (k < len && isNum(row.charAt(k)))k++;
                if(isNeg) {
                    builder.append("-");
                    isNeg = false;
                }
                builder.append(row.substring(i,k) + " ");
                i = k;
            }
            else {
                if(c == '-' && i + 1 < len && isNum(row.charAt(i+1))){
                    isNeg = true;
                }else{
                    if(c == ')' ) {
                        char chr = stack.pop();
                        while (chr != '('){
                            builder.append(chr + " ");
                            chr = stack.pop(); //不检查isEmpty了
                        }
                    }
                    while (lowPriority(c) && !stack.isEmpty() && stack.peek() != '('){
                           builder.append(stack.pop() + " ");
                    }
                    if(c != ')')
                       stack.push(c);
                }
                i++;
            }
        }
        while (!stack.isEmpty()){
            builder.append(stack.pop()+" ");
        }
        System.out.println(new String(builder));
        return new String(builder);
    }

    private static boolean lowPriority(char c){
        return (c=='+'||c == '-');
    }

    public static int polandCalculator(String path) throws Exception{
        return getAnswer(translateToBack(path));
    }

    public static void main(String[] args) throws Exception{
        while (true){
            String s = scanner.nextLine();
            int i = PolandCalculator.polandCalculator(s);
            System.out.println(i);
        }
    }
}

