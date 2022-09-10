package DataStructure.Huffman;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCode {

    private Node[] nodes;
    private String[] library;

    static class Node<T>{
        T object;
        double weight;
        int parent;
        int leftChild;
        int rightChild;

        public Node(T object, double weight) {
            this.object = object;
            this.weight = weight;
            this.parent = 0;
            this.leftChild = 0;
            this.rightChild = 0;
        }
    }

    /**
     * 创建一个权值表,并初始化
     */
     public HuffmanCode(){
         Node[] nodes = new Node[14];
         nodes[1] = new Node('a',0.4);
         nodes[2] = new Node('b',0.3);
         nodes[3] = new Node('c',0.15);
         nodes[4] = new Node('d',0.05);
         nodes[5] = new Node('e',0.04);
         nodes[6] = new Node('f',0.03);
         nodes[7] = new Node('g',0.03);
         this.nodes = nodes;
     }

     public void makeTree(){
         PriorityQueue<double[]> heap = new PriorityQueue<>(new Comparator<double[]>() {
             @Override
             public int compare(double[] o1, double[] o2) {
                 if(o1[1] > o2[1]) return 1;
                 if(o1[1] == o2[1]) return 0;
                 return -1;
             }
         });
         for (int i = 1; i < 8; i++) {
            heap.add(new double[]{i,nodes[i].weight});
         }
         for (int i = 8; i < 14; i++) {
             //选出两个树
             double[] left = heap.poll();
             double[] right = heap.poll();
             Node newNode = new Node(null, left[1] + right[1]);
             newNode.leftChild = (int) left[0];
             newNode.rightChild = (int) right[0];
             //添加到树中
             nodes[i] = newNode;
             //修改父母
             nodes[(int) left[0]].parent = i;
             nodes[(int) right[0]].parent = i;
             heap.add(new double[]{i,newNode.weight});
         }
     }

     public void makeLibrary(){
         library = new String[8];
         for (int i = 1; i < 8; i++) {
             int index = 0;
             char[] chars = new char[6];
             Node p = nodes[i];
             while (p.parent != 0){
                 Node parent = nodes[p.parent];
                 if(p.equals(nodes[parent.leftChild]))
                     chars[index++] = '0';
                 else
                     chars[index++] = '1';
                 p = parent;
             }
             StringBuffer buffer = new StringBuffer(nodes[i].object + ":");
             for (int j = index-1; j >= 0 ; j--) {
                 buffer.append(chars[j]);
             }
             library[i] = new String(buffer);
         }
     }

     public void showLibrary(){
         for (int i = 0; i < library.length; i++) {
             System.out.println(library[i]);
         }
     }

     public void encodes(String path,String destination) throws IOException {
         BufferedReader reader = new BufferedReader(new FileReader(path));
         BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
         char[] buffer = new char[1024];
         int len = 0;
         while (( len = reader.read(buffer)) != 0){
             String s = new String(buffer, 0, len);
             StringBuilder encodes = new StringBuilder();
             for (int i = 0; i < s.length(); i++) {
                 char c = s.charAt(i);
                 if(inTheTable(c)) {
                     int offset = c - 'a';
                     encodes.append(library[offset]);
                 }
             }
             writer.write(encodes.toString());
             writer.flush();
         }
         reader.close();
         writer.close();
         return ;
     }

     private boolean inTheTable(char c){
         if(c >= 'a' && c <= 'g') return true;
         return false;
     }

     public String decode(String message){
         int len = message.length(), i = 0;
         StringBuilder builder = new StringBuilder();
         while (i  < len){
             Node p = nodes[0];
             while (i < len && p.object == null){
                 char c = message.charAt(i);
                 if(c == '0')
                    p = nodes[p.leftChild];
                 else
                     p = nodes[p.rightChild];
                 i++;
             }
             if(p != null)
                builder.append(p.object);
             i++;
         }
         return new String(builder);
     }

    public static void main(String[] args) {
        HuffmanCode huffmanCode = new HuffmanCode();
        huffmanCode.makeTree();
        huffmanCode.makeLibrary();
        huffmanCode.showLibrary();
    }
}

