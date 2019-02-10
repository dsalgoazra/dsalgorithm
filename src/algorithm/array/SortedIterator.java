package algorithm.array;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of sorted lists each of size maximum size M, implement an iterator (maintain the order of items as in
 * the original list of lists).

 I had a solution requiring extra space using minHeap; However, the interviewer was looking for a constant space solution.

 */
public class SortedIterator<K extends Comparable>  implements Iterator<K>{

    private List<K> sortedList;
    private int currentIdx = 0;

    public SortedIterator(K[][] sortedArrays){
        iterateListOfLists(sortedArrays);
    }

    @Override
    public boolean hasNext() {
        return (currentIdx  < sortedList.size());
    }

    @Override
    public K next() {
        K item = null;
        if(currentIdx < sortedList.size()){
            item = sortedList.get(currentIdx);
            currentIdx++;
        }
        return item;
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

    private List<K> iterateListOfLists(K[][] arr){
        PriorityQueue<SortedNode> q = new PriorityQueue<SortedNode>((a, b) -> {return a.compareTo(b);});
        int n = arr.length;
        int size = 0;
        for(int i=0; i < n; i++){ //O(n)
            int m = arr[i].length;
            if(m > 0 && arr[i][0] != null) {
                q.offer(new SortedNode(i,arr[i][0],1, m));
            }
            size += m;
        }
        sortedList = new ArrayList<>(size);
        while(!q.isEmpty()) { //O(n + m)
            SortedNode next = q.poll();
            sortedList.add(next.val);
            int arrNum = next.arrNum;
            if(next.nextIdx < next.size && arr[arrNum][next.nextIdx] != null) {
                q.offer(new SortedNode(arrNum, arr[arrNum][next.nextIdx],next.nextIdx+1, next.size ));
            }
        }
        return sortedList;
    }

    public static void main(String[] args) {
        Integer[][] arr = {{1 ,2, 8}, {6,9,10}, {1, 4, 5 , 12 }};
        SortedIterator<Integer> it = new SortedIterator(arr);
        while(it.hasNext()) {
            System.out.println(it.next()+"");
        }
    }
}
