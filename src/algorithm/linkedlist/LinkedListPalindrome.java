/**
 * This is my implementation of finding plaindromic linkedlist
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.linkedlist;

import ds.LinkedList.LinkedList;

public class LinkedListPalindrome<K extends Comparable> {

    private LinkedList<K> root;

    public LinkedListPalindrome(K[] arr){
        init(arr);
    }

    private void init(K[] arr){
        if(arr == null || arr.length == 0) return;
        root = new LinkedList<>(arr[0]);
        LinkedList temp = root;
        LinkedList next;
        for(int i = 1 ; i < arr.length; i++){
            next = new LinkedList(arr[i]);
            temp.setNext(next);
            temp = next;
        }
    }

    public boolean isPlindrome(){
        LinkedList slow = this.root;
        LinkedList slowPrev = this.root;
        LinkedList fast = this.root;
        int halfSize = 0;
        if(this.root != null) {
            while (fast != null && fast.getNext() != null) {
                slowPrev = slow;
                slow = slow.getNext();
                fast = fast.getNext().getNext();
                halfSize++;
            }
            if (fast != null) {//odd size array
                slow = slow.getNext();//now slow contains the second half after mid
            }

            slowPrev.setNext(null);
            LinkedList secondHalf = reverse(slow);
            int i = 0;
            LinkedList firstHalf = root;
            while (i < halfSize && firstHalf != null && secondHalf != null) {
                if (firstHalf.getData().compareTo(secondHalf.getData()) != 0) {
                    return false;
                }
                firstHalf = firstHalf.getNext();
                secondHalf = secondHalf.getNext();
            }
            return true;
        }
        return false;
    }

    private LinkedList reverse(LinkedList node){
        LinkedList curr = node;
        LinkedList next = null;
        LinkedList prev = null;
        while(curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args){
        Integer[] arr = {4,2,4,1,5,1,4,2};
        LinkedListPalindrome<Integer> l = new LinkedListPalindrome<>(arr);
        System.out.println("isPlaindrome : "+ l.isPlindrome());

    }
}
