/**
 * This is my implementation to find a predecessor for the given node.
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */
package algorithm.tree;

import ds.tree.TreeNode;

public class FindSuccessor<K extends Comparable> {

    public TreeNode<K> findSuccessorForGivenNode(TreeNode<K> root, TreeNode<K> x){
        if(root == null && x == null) return null;
        if(x.getRight() != null) {
            TreeNode temp = x.getRight();
            while(temp != null && temp.getLeft() != null) {
                temp = temp.getLeft();
            }
            return temp;
        }
        TreeNode<K> ancestor = root;
        return findSuccessor(ancestor, x.getData() );
    }


    public TreeNode<K> findSuccessor(TreeNode<K> ancestor, K x) {
         TreeNode<K> successor = ancestor;
         while(ancestor != null && ancestor.getData().compareTo(x) != 0) {
             if(ancestor.getData().compareTo(x) > 0) {
                 //since successor has to be greater than x so if ancestor is greater than the x then it is potentially
                 //a successor so save it. now since we are interested in the minimum greater so keep moving towards left
                 successor = ancestor;
                 ancestor = ancestor.getLeft();
             } else {
                 ancestor = ancestor.getRight();
             }
         }
         return successor;
    }

}
