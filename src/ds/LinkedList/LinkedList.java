package ds.LinkedList;

public class LinkedList<K extends Comparable> {

    private LinkedList<K> next;
    private K data;

    public LinkedList(K data){
        this.data = data;
    }
    public LinkedList<K> getNext() {
        return next;
    }

    public void setNext(LinkedList<K> next) {
        this.next = next;
    }

    public K getData() {
        return data;
    }

    public int compareTo(K d){
        if(d == null) return -1;
        return this.data.compareTo(d);
    }
    public String toString(){
        return data.toString();
    }

    public boolean equals(Object that) {
        if(that == null) return false;
        if(that instanceof LinkedList) {

        }
        return false;
    }

}
