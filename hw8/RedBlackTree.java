/**
 * Simple Red-Black tree implementation, where the keys are of type T.
 @author Brian Chiang
 */
public class RedBlackTree<T extends Comparable<T>> {

    /** Root of the tree. */
    private RBTreeNode<T> root;

    /**
     * Empty constructor.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Constructor that builds this from given BTree (2-3-4) TREE.
     *
     * @param tree BTree (2-3-4 tree).
     */
    public RedBlackTree(BTree<T> tree) {
        BTree.Node<T> btreeRoot = tree.root;
        root = buildRedBlackTree(btreeRoot);
    }

    /**
     * Builds a RedBlack tree that has isometry with given 2-3-4 tree rooted at
     * given node r, and returns the root node.
     *
     * @param r root of the 2-3-4 tree.
     * @return root of the Red-Black tree for given 2-3-4 tree.
     */
    RBTreeNode<T> buildRedBlackTree(BTree.Node<T> r) {
        RBTreeNode node = null;
        if (r.getItemCount() == 3) {
            node = new RBTreeNode(true, r.getItemAt(1));
            node.left = new RBTreeNode(false, r.getItemAt(0));
            node.right = new RBTreeNode(false, r.getItemAt(2));
            if (r.getChildrenCount() > 0) {
                node.left.left = buildRedBlackTree(r.getChildAt(0));
                node.left.right = buildRedBlackTree(r.getChildAt(1));
                node.right.left = buildRedBlackTree(r.getChildAt(2));
                node.right.right = buildRedBlackTree(r.getChildAt(3));
            } else if (r.getItemCount() == 2) {
                node = new RBTreeNode(true, r.getItemAt(0));
                node.right = new RBTreeNode(false, r.getItemAt(1));
                if (r.getChildrenCount() > 0) {
                    node.left = buildRedBlackTree(r.getChildAt(0));
                    node.right.left = buildRedBlackTree(r.getChildAt(1));
                    node.right.right = buildRedBlackTree(r.getChildAt(2));
                }
            } else if (r != null) {
                node = new RBTreeNode(true, r.getItemAt(0));
                if (r.getChildrenCount() > 0) {
                    node.left = buildRedBlackTree(r.getChildAt(0));
                    node.right = buildRedBlackTree(r.getChildAt(1));
                }
            }
        }
        return node;
    }

    /**
     * Rotates the (sub)tree rooted at given NODE to the right, and returns the
     * new root of the (sub)tree. If rotation is not possible somehow,
     * immediately return the input NODE.
     *
     * @param node root of the given (sub)tree.
     * @return new root of the (sub)tree.
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        if (node.left == null) {
            return node;
        }
        RBTreeNode<T> left = node.left;
        RBTreeNode<T> leftRight = left.right;
        left.right = node;
        node.left = leftRight;
        left.isBlack = node.isBlack;
        node.isBlack = false;
        return left;
    }

    /**
     * Rotates the (sub)tree rooted at given NODE to the left, and returns the
     * new root of the (sub)tree. If rotation is not possible somehow,
     * immediately return the input NODE.
     *
     * @param node root of the given (sub)tree.
     * @return new root of the (sub)tree.
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        if (node.right == null) {
            return node;
        }
        RBTreeNode<T> right = node.right;
        RBTreeNode<T> rightLeft = right.left;
        right.left = node;
        node.right = rightLeft;
        right.isBlack = node.isBlack;
        node.isBlack = false;
        return right;
    }

    /**
     * Flips the color of NODE and its children. Assume that NODE has
     * both left and right children.
     *
     * @param node tree node
     */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /**
     * Returns whether a given NODE is red. Null nodes (children of leaves) are
     * automatically considered black.
     *
     * @param node node
     * @return node is red.
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Insert given item into this tree.
     *
     * @param item item
     */
    void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Recursively insert ITEM into this tree. Returns the (new) root of the
     * subtree rooted at given NODE after insertion. NODE == null implies that
     * we are inserting a new node at the bottom.
     *
     * @param node node
     * @param item item
     * @return (new) root of the subtree rooted at given node.
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        if (node == null) {
            node = new RBTreeNode<T>(false, item);
        }
        int comp = item.compareTo(node.item);
        if (comp == 0) {
            return node;
        } else if (comp < 0) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    /** Public accesser method for the root of the tree.*/
    public RBTreeNode<T> graderRoot() {
        return root;
    }


    /**
     * RedBlack tree node.
     *
     * @param <T> type of item.
     */
    static class RBTreeNode<T> {

        /** Item. */
        protected final T item;

        /** True if the node is black. */
        protected boolean isBlack;

        /** Pointer to left child. */
        protected RBTreeNode<T> left;

        /** Pointer to right child. */
        protected RBTreeNode<T> right;

        /**
         * A node that is black iff BLACK, containing VALUE, with empty
         * children.
         */
        RBTreeNode(boolean black, T value) {
            this(black, value, null, null);
        }

        /**
         * Node that is black iff BLACK, contains VALUE, and has children
         * LFT AND RGHT.
         */
        RBTreeNode(boolean black, T value,
                   RBTreeNode<T> lft, RBTreeNode<T> rght) {
            isBlack = black;
            item = value;
            left = lft;
            right = rght;
        }
    }

}
