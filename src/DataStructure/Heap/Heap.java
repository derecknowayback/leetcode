package DataStructure.Heap;

public class Heap {
    int []heap;
    int size;
    int capacity;
    public static final int MaxData = Integer.MAX_VALUE;
    public static final int defaultSize = 10;

    public Heap(int capacity){
        this.capacity = capacity;
        this.heap = new int[capacity + 1];
        this.size = 0;
        heap[0] = MaxData; //哨兵
    }

    public Heap(){
        this(defaultSize);
    }

    public void insert(int a){
        if(isFull()) {
            System.out.println("Heap is full");
            return;
        }
        int i = ++size; // 注意这里是 ++size ，不是size++
        while (a > heap[i/2]){
            heap[i] = heap[i/2];
            i /= 2; // 放心，最后 i==1 时候有哨兵挡着, heap[i] 不会比 heap[0]，所以 i 不会是0，最多到1
        }
        heap[i] = a;
    }

    public Integer deleteMax(){
        if(isEmpty()){
            System.out.println("Heap is empty");
            return null;
        }
        int res = heap[1];
        int last = heap[size--];
        int parent = 1, child;
        for (parent = 1; parent * 2 <= size; parent = child){
            child = parent * 2;
            if(child + 1 <= size && heap[child] < heap[child+1])
                child++; //指向左右儿子中大的那个
            if(heap[child] <= last) break; //比两个儿子都大，不需要动了，直接插入
            heap[parent] =  heap[child]; // 上提
        }
        heap[parent] = last;
        return res;
    }

    public void makeHeap(){
        int lastParent = size/2;
        while (lastParent > 0){
           pull(lastParent);
            lastParent--;
        }
    }

    public void pull(int p){
        int last = heap[p];
        int parent , child;
        for (parent = p; parent * 2 <= size; parent = child){
            child = parent * 2;
            if(child + 1 <= size && heap[child] < heap[child+1])
                child++; //指向左右儿子中大的那个
            if(heap[child] <= last) break; //比两个儿子都大，不需要动了，直接插入
            heap[parent] =  heap[child]; // 上提
        }
        heap[parent] = last;
    }


    public boolean isFull(){
        return size == capacity;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
