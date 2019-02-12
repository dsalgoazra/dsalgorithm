package ds.tree;

public class TreeNode<K extends Comparable> {
    private K data;
    private TreeNode left;
    private TreeNode right;
    private int level;
    private int hd = Integer.MAX_VALUE;
    public TreeNode(K i) {
        this.data = i;
    }

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }


}
