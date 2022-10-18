package SORT;

import java.util.Scanner;


public class TestSort {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            int[] ints = readArray();
            swap.quickSort(ints);
            PrintArr(ints);
        }
    }

    public static int[] readArray(){
        String s = scanner.next();
        String[] s1 = s.split(",");
        int[] res = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            res[i] = Integer.parseInt(s1[i]);
        }
        return res;
    }

    public static void PrintArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "   ");
        }
        System.out.println();
    }

}
