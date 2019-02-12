/**
 * This is my lazy implementation of a question found at Career cup https://www.careercup.com/question?id=5647438614888448
 * It is fail safe, and it will not reflect any changes done to the array after creating the SortedIterator.
 *
 * Q: Given a list of sorted lists each of size maximum size M, implement an iterator (maintain the order of items as in
 * the original list of lists).
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */
package algorithm.array;

import java.util.Iterator;
import java.util.PriorityQueue;

public class SortedIterator<K extends Comparable>  implements Iterator<K> {
    private int currentIdx = 0;
    private K[][] sortedArrays;
    private int size = 0;
    private PriorityQueue<SortedNode> q = new PriorityQueue<SortedNode>((a, b) -> {return a.compareTo(b);});

    public SortedIterator(K[][] sortedArrays){
        this.sortedArrays = sortedArrays;
        initIterator();
    }

    @Override
    public boolean hasNext() {
        return (currentIdx  < size);
    }

    @Override
    public K next() {
        K item = null;
        if(this.sortedArrays != null && hasNext()) {
            SortedNode next = q.poll();
            item = next.val;
            int arrNum = next.arrNum;
            if (next.nextIdx < next.size && this.sortedArrays[arrNum][next.nextIdx] != null) {
                q.offer(new SortedNode(arrNum, this.sortedArrays[arrNum][next.nextIdx], next.nextIdx + 1, next.size));
            }
            currentIdx++;
        }
        return item;
    }

    private void initIterator() {
        if(this.sortedArrays != null) {
            int n = this.sortedArrays.length;
            for (int i = 0; i < n; i++) { //O(n)
                int m = this.sortedArrays[i].length;
                if (m > 0 && this.sortedArrays[i][0] != null) {
                    q.offer(new SortedNode(i, this.sortedArrays[i][0], 1, m));
                }
                size += m;
            }
        }
    }

    public static void main(String[] args) {
        Integer[][] arr = {{1 ,2, 8}, {6,9,10}, {1, 4, 5 , 12 }};
        SortedIterator<Integer> it = new SortedIterator(arr);
        while(it.hasNext()) {
            System.out.println(it.next()+"");
        }
    }

    private class SortedNode implements Comparable<SortedNode>{
        K val;
        int size;
        int nextIdx;
        int arrNum;

        SortedNode(int arrNum, K val, int nextIdx, int size){
            this.val = val;
            this.nextIdx = nextIdx;
            this.size = size;
            this.arrNum = arrNum;
        }

        public int compareTo(SortedNode n){
            if(n == null) return -1;
            if(n.val == null && this.val == null) return 0;
            if(n.val == null && this.val != null) return -1;
            if(n.val != null && this.val == null) return 1;
            return this.val.compareTo(n.val);
        }
    }
}
