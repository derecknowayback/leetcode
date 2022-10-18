package SORT;

public class swap {

    public static void bubbleSort(int[] arr){
        int len = arr.length;
        boolean isDown = true;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 1; j < len - i; j++) {
                if(arr[j] > arr[j-1]){
                    swapNum(arr,j,j-1);
                    isDown = false;
                }
            }
            if(isDown) return;
        }
    }

    public static void quickSort(int[] arr){
        int len = arr.length;
        quickSort(arr,0,len-1);
    }

    public static void quickSort(int[] arr,int start,int end){
        if(start >= end) return;
        int pivot = arr[Median3(arr, start, end)] ; // 自带交换首尾中三个位置的功能
        if(end - start == 1) return; // 这句很重要，防止长度小于3的时候还进行交换，那样子会损害已排序的序列
        int low = start  , high = end - 1 ;
        while (true){
            // 就算是 arr[low] == pivot 也会交换
            while (low <= high &&arr[++low] < pivot) ;
            while (high >= low && arr[--high] > pivot) ;
            if(low > high)
                break;
            swapNum(arr, low,high);
        }
        // low的位子就是pivot的正确位置
        swapNum(arr,low,end-1);
        quickSort(arr,start,low-1);
        quickSort(arr,low+1,end);
    }

    public static int Median3(int[]arr,int start,int end){
        int mid = start + (end - start) / 2;
        if(arr[start] > arr[mid])
            swapNum(arr, start, mid);
        if(arr[mid] > arr[end])
            swapNum(arr, mid, end);
        if(arr[start] > arr[mid])
            swapNum(arr, start, mid);
        // arr[start] <= arr[mid] <= arr[end]
        swapNum(arr,mid,end-1); // 将 mid选为pivot，并且将mid放到end-1
        //因为 一定会有left<mid<right，所以我们只要考虑 left+1 ~ right - 1 ，
        // 现在我们将pivot放在right-1 ，那我们只需要考虑 left+1 ~ right - 2（right-1是pivot）
        return end-1; //返回下标
    }


    public static void swapNum(int[] num,int a,int b){
        int k = num[a];
        num[a] = num[b];
        num[b] = k;
    }

}
