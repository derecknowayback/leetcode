package SORT;

public class insert {
    public static void main(String[] args) {

    }

    public static int[] insert_sentinel(int []arr){
        int[] res = new int[arr.length +  1];
        int len = res.length;
        res[0] = 0;
        for (int i = 1; i <= len ; i++) {
            res[i] = arr[i-1];
        }
        for (int i = 2; i < len; i++) {
            if(res[i] > res[i-1]) continue;
            res[0] = res[i];
            for (int j = i-1; j > 0 && res[0] < res[i]; j--) {
                res[j+1] =  res[j];
            res[j+1] = res[0];
            }
        }
        int[] nRes = new int[len];
        for (int i = 0; i < len; i++) {
            nRes[i] = res[i+1];
        }
        return nRes;
    }

    public static void insert_direct(int []arr){
        int len = arr.length;
        for (int i = len - 1; i > 0; i--) {
            int k = arr[i];
            int j;
            for (j = i - 1; j > 0 && k < arr[i]; j--) {
                arr[i + 1] = arr[i];
            }
            arr[j] = k;
        }
        return;
    }

    public static void bInsert(int []arr){
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int left = 0, right = i -1;
            int k = arr[i];
            while (left < right){
                int mid = left + (right - left) / 2;
                if(arr[mid] <= k) left = mid + 1 ;
                else right = mid;
            }
            if(left == i) continue;
            for (int j = i; j > left  ; j--) {
                arr[j] = arr[j-1];
            }
            arr[left] = k;
        }
        return;
    }

    public static final int[] Sedgewick = {1,2, 5,19,41,109};
    public static final int[] Hibbard = {1,2,4,8,16,32,64};
    public static void shellSort(int []arr){
        int len = arr.length;
        for (int i = 3; i >= 0 ; i--) {
            int dk = Sedgewick[i];
            for (int j = dk + 1; j < len; j++) {
                int k = arr[j];
                int index = j - dk;
                while (index >= 0 && arr[index] > k){
                    arr[index + dk] = arr[index];
                    index = index - dk;
                }
                arr[index] = k;
            }
        }
        return;
    }
}
