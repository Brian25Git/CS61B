import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Implementation of a BST based String Set.
 * @author Brian Chiang
 */
public class BSTStringSet implements StringSet, Iterable<String> {
    /** Creates a new empty set. */
    public BSTStringSet() {
        _root = null;
    }

    @Override
    public void put(String s) {
        _root = putt(s, _root);
    }

    /** Helper for put. Adds a node with S to a BST starting with _ROOT. */
    private Node putt(String s, Node n) {
        if (n == null) {
            return new Node(s);
        }
        if (s.compareTo(n.s) < 0) {
            if (n.left == null) {
                n.left = new Node(s);
            } else {
                n.left = putt(s, n.left);
            }
        }
        if (s.compareTo(n.s) > 0) {
            if (n.right == null) {
                n.right = new Node(s);
            } else {
                n.right = putt(s, n.right);
            }
        }
        return n;
    }

    @Override
    public boolean contains(String s) {
        return contain(s, _root);
    }

    /** Helper for contains. Returns true iff S is found in BST. */
    private boolean contain(String s, Node n) {
        if (n != null) {
            if (s.compareTo(n.s) == 0) {
                return true;
            } else if (s.compareTo(n.s) < 0 && n.left != null) {
                return contain(s, n.left);
            } else if (s.compareTo(n.s) > 0 && n.right != null) {
                return contain(s, n.right);
            }
        }
        return false;
    }

    @Override
    public List<String> asList() {
        ArrayList<String> result = new ArrayList<>();
        for (String label : this) {
            result.add(label);
        }
        return result;
    }


    /** Represents a single Node of the tree. */
    private static class Node {
        /** String stored in this Node. */
        private String s;
        /** Left child of this Node. */
        private Node left;
        /** Right child of this Node. */
        private Node right;

        /** Creates a Node containing SP. */
        Node(String sp) {
            s = sp;
        }
    }

    /** An iterator over BSTs. */
    private static class BSTIterator implements Iterator<String> {
        /** Stack of nodes to be delivered.  The values to be delivered
         *  are (a) the label of the top of the stack, then (b)
         *  the labels of the right child of the top of the stack inorder,
         *  then (c) the nodes in the rest of the stack (i.e., the result
         *  of recursively applying this rule to the result of popping
         *  the stack. */
        private Stack<Node> _toDo = new Stack<>();

        /** A new iterator over the labels in NODE. */
        BSTIterator(Node node) {
            addTree(node);
        }

        @Override
        public boolean hasNext() {
            return !_toDo.empty();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node node = _toDo.pop();
            addTree(node.right);
            return node.s;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /** Add the relevant subtrees of the tree rooted at NODE. */
        private void addTree(Node node) {
            while (node != null) {
                _toDo.push(node);
                node = node.left;
            }
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new BSTIterator(_root);
    }

    // @Override
    public Iterator<String> iterator(String low, String high) {
        return null;  // FIXME: PART B (OPTIONAL)
    }

    /** Root node of the tree. */
    private Node _root;
}
