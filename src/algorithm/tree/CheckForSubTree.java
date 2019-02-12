/**
 *
 * This is my implementation of a question found at https://www.careercup.com/question?id=5756619099471872
     Given 2 trees T1 & T2 (both can have > 2 childs), write an algorithm to find if T2 is a subtree of T1.
     Follow up question, for any branch in T1
     a->b->c->d
     the following is a valid branch in tree T2(i.e. the isSubTree() algorithm mush evaluate to true in below circumstances)
     a->d
     a->c->d
     c->d

 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.tree;

import java.util.*;


public class CheckForSubTree<K extends Comparable> {
    private static class MultiChildTreeNode<K> {
        List<MultiChildTreeNode> children;
        K data;
        MultiChildTreeNode(K data){
            this.data = data;
            children = new ArrayList<>();
        }
    }

    private boolean isSubTree(MultiChildTreeNode<K> t1, MultiChildTreeNode<K> t2){
        if(t2 == null || t1 == null ) return true;
        Stack<MultiChildTreeNode> n1 = new Stack<>();
        n1.push(t1);//main tree traversal -> depth first search
        Queue<MultiChildTreeNode> matchingQ = new LinkedList<>();//if matches found then convert main tree traversal to breadth first search and move on
        Queue<MultiChildTreeNode> n2 = new LinkedList<>();//Breadth First search
        n2.add(t2);
        boolean found = false;
        while(!n2.isEmpty() && !n1.isEmpty()){
            MultiChildTreeNode<K> nn2 = n2.poll();
            MultiChildTreeNode<K> nn1;
            if(found && !matchingQ.isEmpty()){
                nn1 = matchingQ.poll();
            } else {
                nn1 = n1.pop();
            }
            if(nn2.data.compareTo(nn1.data) != 0) { //on mismatch reset
                n2 = new LinkedList<>(); //start over again
                matchingQ = new LinkedList<>();
                n2.add(t2);
                found = false;
            } else {
                found = true;
                for (int i = 0; i < nn2.children.size(); i++) {
                    n2.add(nn2.children.get(i));
                }
                for (int i = 0; i < nn1.children.size(); i++) {
                    matchingQ.add(nn1.children.get(i));
                }
            }
            for (int i = 0; i < nn1.children.size(); i++) {//always add main tree childrenn
                n1.push(nn1.children.get(i));
            }
        }
        return found;
    }

    public static void main(String[] args) {
        /**
         *      F
         *    /  \
         *   I    K
         */

        MultiChildTreeNode<String> ff = new MultiChildTreeNode<>("F");
        MultiChildTreeNode<String> iff = new MultiChildTreeNode<>("I");
        ff.children.add(iff);
        MultiChildTreeNode<String> kff = new MultiChildTreeNode<>("K");
        ff.children.add(kff);
        MultiChildTreeNode<String> t2 =  ff;//Subtree to find

        //Main tree
        MultiChildTreeNode<String> t1 = new MultiChildTreeNode<>("A");
        MultiChildTreeNode<String> b = new MultiChildTreeNode<>("B");
        MultiChildTreeNode<String> c = new MultiChildTreeNode<>("C");
        MultiChildTreeNode<String> d = new MultiChildTreeNode<>("D");
        t1.children.add(b);
        t1.children.add(c);
        t1.children.add(d);

        MultiChildTreeNode<String> e = new MultiChildTreeNode<>("E");
        MultiChildTreeNode<String> h = new MultiChildTreeNode<>("H");
        e.children.add(h);

        MultiChildTreeNode<String> f = new MultiChildTreeNode<>("F");
        b.children.add(f);
        MultiChildTreeNode<String> i = new MultiChildTreeNode<>("I");
        f.children.add(i);
        MultiChildTreeNode<String> j = new MultiChildTreeNode<>("J");
        f.children.add(j);


        /**
         *            A
         *       /    |    \
         *      B     C..  D...
         * /    |
         *E..   F
         *    /  \
         *   I    J
         *        |
         *        F
         *      /  \
         *     I    K
         */
        j.children.add(ff); //commenting out this line should give false otherwise true

        MultiChildTreeNode<String> k = new MultiChildTreeNode<>("K");
        MultiChildTreeNode<String> g = new MultiChildTreeNode<>("G");
        b.children.add(e);

        b.children.add(g);
        MultiChildTreeNode<String> l = new MultiChildTreeNode<>("L");
        c.children.add(k);
        c.children.add(l);

        MultiChildTreeNode<String> m = new MultiChildTreeNode<>("M");
        MultiChildTreeNode<String> n = new MultiChildTreeNode<>("N");
        MultiChildTreeNode<String> o = new MultiChildTreeNode<>("O");
        d.children.add(m);
        d.children.add(n);
        d.children.add(o);
        CheckForSubTree<String> checker = new CheckForSubTree<>();
        System.out.println (checker.isSubTree(t1, t2));
    }

}
