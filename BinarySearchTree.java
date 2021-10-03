import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

    public BinarySearchTree() {
        super();
    }
    public BinarySearchTree(E val) {
        super(val);
    }

    // returns true if BST has val else false.
    public boolean contains (E val) { // Since I don't need to remember the previous node, a prev node
        if (root == null) {
            return val == null;
        }
        Node<E> node = root;
        while (!(node.getLeft() == null && node.getRight() == null)) { // if not a leaf
            E value = node.getInfo();
            if (value.equals(val)) {
                return true;
            }
            if (value.compareTo(val) > 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
            if (node.getLeft() == null && node.getRight() == null) {
                value = node.getInfo();
                if (value.equals(val)) {
                    return true;
                }
            }
        }
        return false;
    }
    // inserts val at the right place satisfying search tree properties, should handle if the tree is empty
    // if value is already there then don't insert it again
    public void insert(E val) {
        Node<E> node = new Node<>();
        node.setInfo(val);
        if (root == null) {
            root = node;
            return;
        }
        Node<E> temp = root;
        Node<E> prev = null;
        while (temp != null) { // Goes on until temp is finally an "imaginary child" of the lowest node of the tree.
            if (val.compareTo(temp.getInfo()) < 0) { // if the value is less than the current node
                prev = temp;
                temp = temp.getLeft();
            } else if (val.compareTo(temp.getInfo()) > 0) { // if the value is greater than
                prev = temp;
                temp = temp.getRight();
            }
        }
        if (val.compareTo(prev.getInfo()) < 0) { // if the val is less
            prev.left = node;
        } else {
            prev.right = node;
        }
    }

    // returns the minimum value stored in the tree
    public E findMin() {
        if (root == null) {
            throw new NoSuchElementException("There are no values in the tree");
        }
        Node<E> node = root;
        E information = root.getInfo();
        while(node != null) {
            node = node.getLeft();
            if (node != null) {
                information = node.getInfo();
            }
        }
        return information;
    }

    // returns the maximum value stored in the tree
    public E findMax() {
        if (root == null) {
            throw new NoSuchElementException("There are no values in the tree");
        }
        Node<E> node = root;
        E information = node.getInfo();
        while (node != null) {
            node = node.getRight();
            if (node != null) {
                information = node.getInfo();
            }
        }
        return information;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bt= new BinarySearchTree<>();
        bt.insert(5);
        bt.insert(10);
        bt.insert(3);
        bt.insert(20);
        bt.insert(8);
        bt.insert(4);
    }

}