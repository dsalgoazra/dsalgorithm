/**
 * This is my implementation of a question found at https://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.tree;

import ds.tree.TreeNode;
import java.util.Stack;

public class ConstructBinaryTreeFromOrderTraversedArray<K extends Comparable>{

    //20, 10, 5, 1, 7, 15, 30, 25, 35, 32, 40
    public TreeNode<K> ConstructTreeFromPreOrder(K[] array){
        if(array == null || array.length == 0) return null;
        Stack<TreeNode<K>> s = new Stack<>();
        TreeNode<K> root = new TreeNode<>(array[0]);
        s.push(root);
        TreeNode<K>  n;
        for(int i = 0; i < array.length; i++) {
            n = null;
            while(!s.isEmpty() && array[i].compareTo(s.peek().getData()) > 0) {
                n = s.pop();
            }
            if(n != null) { //next element is greater than element in the stack
                TreeNode<K> right = new TreeNode<>(array[i]) ;
                n.setRight(right);
                s.push(right);
            } else {
                TreeNode<K> left = new TreeNode<>(array[i]) ;
                s.peek().setLeft(left);
                s.push(left);
            }
        }
        return root;
    }

    //10, 5, 1, 7, 15, 30, 25, 35, 32, 40, 20
    public TreeNode<K> ConstructTreeFromPostOrder(K[] array){
        if(array == null || array.length == 0) return null;
        Stack<TreeNode<K>> s = new Stack<>();
        TreeNode<K> root = new TreeNode<>(array[array.length -1]);//root is at the end
        s.push(root);
        TreeNode<K>  n;
        for(int i = 0; i < array.length - 1; i++) {
            n = null;
            while(!s.isEmpty() && array[i].compareTo(s.peek().getData()) > 0) {
                n = s.pop();
            }
            if(n != null) { //next element is greater than element in the stack
                TreeNode<K> right = new TreeNode<>(array[i]) ;
                n.setRight(right);
                s.push(right);
            } else {
                TreeNode<K> left = new TreeNode<>(array[i]);
                s.peek().setLeft(left);
                s.push(left);
            }
        }
        return root;
    }
}
