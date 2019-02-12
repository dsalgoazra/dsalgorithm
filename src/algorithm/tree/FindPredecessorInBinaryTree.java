/**
 * This is my implementation to find a predecessor for the given node.
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.tree;

import ds.tree.TreeNode;

public class FindPredecessor<K extends Comparable> {

    public TreeNode<K> findPredecessorOfGivenNode(TreeNode<K> root, TreeNode<K> x){
        if(root == null && x == null) {
            return null;
        }
        if(x.getLeft() != null ) {
            TreeNode temp = x.getLeft();
            while(temp != null && temp.getRight() != null) {
                temp = temp.getRight();
            }
            return temp;
        }
        TreeNode<K> ancestor = root;
        return findPredecessor(ancestor, x.getData());
    }

    //O(logn)
    public TreeNode<K> findPredecessor(TreeNode<K> ancestor, K x) {
        TreeNode<K> predecessor = ancestor;
        while(ancestor != null && ancestor.getData().compareTo(x) != 0 ){
            if(ancestor.getData().compareTo(x) > 0) {
                ancestor = ancestor.getLeft();
            } else {
                //since predecessor has to be smaller than x so if ancestor is smaller than x then it is potentially
                //a predecessor so save it. now since we are interested in the maximum small so keep moving towards right
                ancestor = ancestor.getRight();
                predecessor = ancestor;
            }
        }
        return predecessor;
    }
}
