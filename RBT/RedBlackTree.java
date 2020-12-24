/***
 * @author Umar Farooq!!!
 *
 */

public class RedBlackTree<T extends Comparable<T>> {

    // the root of out red black tree

    /**
     * a single sentinel leaf node to be used by all nodes having leaves as their
     * children the sentinel node does not need to store a parent reference
     */

    final RBTNode sentinelLeaf;
    RBTNode root;
    int nodeCount = 0;

    public RedBlackTree() {
        root = null;
        sentinelLeaf = new RBTNode(Color.black, null);
        sentinelLeaf.left = sentinelLeaf;
        sentinelLeaf.right = sentinelLeaf;
        sentinelLeaf.parent = sentinelLeaf;
        nodeCount = 0;
        root = sentinelLeaf;
    }

    public static void main(String[] args) {

        /**    THIS METHOD IS FOR TESTING PURPOSE     */

        RedBlackTree<Integer> integerRedBlackTree = new RedBlackTree<>();

        integerRedBlackTree.insert(112);
        integerRedBlackTree.insert(213);
        integerRedBlackTree.insert(23);
        integerRedBlackTree.insert(12);
        integerRedBlackTree.insert(34);
        integerRedBlackTree.insert(456);
        integerRedBlackTree.insert(43);

//        integerRedBlackTree.rightLeftRotate(integerRedBlackTree.search(34));
        System.out.println(integerRedBlackTree.search(456).element);

    }

    public RBTNode rightLeftRotate(RBTNode current) {
        // TODO implement right->left rotation
        RBTNode result = rotateLeft(current.right);
        current.right = result;
        result.parent = current;
        return rotateLeft(current);

    }

    // assume no duplicates allowed
    // DO NOT PROVIDE A RECURSIVE IMPLEMENTATION!!!!

    public RBTNode rotateRight(RBTNode current) {
        // TODO implement right rotation
        RBTNode temp = current.left;

        current.left = temp.right;

        if (temp.right != sentinelLeaf) {
            temp.right.parent = current;
        }

        if (current.parent == sentinelLeaf) {
            root = temp;
        }

        if (current == current.parent.left) {
            current.parent.left = temp;
        } else {
            current.parent.right = temp;
        }

        temp.parent = current.parent;

        temp.right = current;
        current.parent = temp;
        return temp;
    }

    public boolean insert(T key) {
        // TODO implement the insert method iteratively as discussed in the lecture,
        RBTNode base = root, temp = sentinelLeaf;

        while (base != sentinelLeaf) {
            temp = base;
            if (base.element.compareTo(key) > 0) {
                base = base.left;

            } else if (base.element.compareTo(key) < 0) {
                base = base.right;

            } else {
                return false;

            }
        }

        RBTNode insertionNode = new RBTNode(key, Color.red, temp, sentinelLeaf, sentinelLeaf);

        if (temp == sentinelLeaf) {
            root = insertionNode;

        } else if (insertionNode.element.compareTo(temp.element) < 0) {
            temp.left = insertionNode;

        } else {
            temp.right = insertionNode;

        }

        RBTNode temp2;

        while (insertionNode.parent.color == Color.red) {

            if (insertionNode.parent != insertionNode.parent.parent.left) {

                temp2 = insertionNode.parent.parent.left;

                if (temp2.color != Color.red) {

                    if (insertionNode == insertionNode.parent.left) {

                        insertionNode = insertionNode.parent;
                        rotateRight(insertionNode);
                    }

                    insertionNode.parent.color = Color.black;
                    insertionNode.parent.parent.color = Color.red;
                    rotateRight(insertionNode.parent.parent);

                } else {

                    insertionNode.parent.color = Color.black;
                    temp2.color = Color.black;
                    insertionNode.parent.parent.color = Color.red;
                    insertionNode = insertionNode.parent.parent;

                }
            } else {

                temp2 = insertionNode.parent.parent.right;

                if (temp2.color != Color.red) {

                    if (insertionNode == insertionNode.parent.right) {
                        insertionNode = insertionNode.parent;
                        rotateLeft(insertionNode);

                    }
                    insertionNode.parent.color = Color.black;
                    insertionNode.parent.parent.color = Color.red;
                    rotateRight(insertionNode.parent.parent);

                } else {

                    insertionNode.parent.color = Color.black;
                    temp2.color = Color.black;
                    insertionNode.parent.parent.color = Color.black;
                    insertionNode = insertionNode.parent.parent;

                }
            }
        }

        root.color = Color.black;
        sentinelLeaf.parent = null;
        nodeCount++;

        return true;
    }

    // TODO declare any other method you may need in your implementations.

    // DO NOT PROVIDE A RECURSIVE IMPLEMENTATION!!!!
    public RBTNode search(T key) {
        // TODO implement the search method iteratively as discussed in the lecture,
        /**     THIS IS NOT RECURSION      */
        boolean loop = true;
        RBTNode curr = root;
        while (loop) {
            if (curr == sentinelLeaf) {
                loop = false;
            } else if (curr.element.equals(key)) {
                loop = false;
            } else if (curr.element.compareTo(key) < 0) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return curr;
    }

    /**
     * rotates around the current node the subtree rooted by it, towards the left.
     * returns the root of the rotated subtree.
     */
    public RBTNode rotateLeft(RBTNode current) {
        RBTNode temp = current.right;
        current.right = temp.left;
        if (temp.left != null) {
            temp.left.parent = current;
        }
        temp.left = current;
        current.parent = temp;
        return temp;
    }

    // is the tree empty?

    /**
     * double rotation, first rotating the subtree rooted at the parent of the
     * node causing the violation to the left, then rotating the subtree rooted at the current
     * node to the right
     */

    public RBTNode leftRightRotate(RBTNode current) {
        RBTNode result = rotateLeft(current.left);
        current.left = result;
        result.parent = current;
        return rotateRight(current);
    }

    // is the current node a left child?

    public boolean isEmpty() {
        return root == null;
    }

    // Does there exist a double red violation from the current node

    public boolean isLeftChild(RBTNode current) {
        return current.parent.left == current;
    }

    // Recolors the current node to red

    public boolean hasDoubleRed(RBTNode current) {
        if (isBlack(current)) {
            return false;
        } else if (current.parent == null) {
            // no parent, hence red-red sequence cannot occur
            return false;
        } else return !isBlack(current.parent);
    }

    // Recolors the current node to red

    public void recolorRed(RBTNode current) {
        current.color = Color.red;
    }

    // is the current node a black node?

    public void recolorBlack(RBTNode current) {
        current.color = Color.black;
    }

    // an enumeration providing the possible color values

    public boolean isBlack(RBTNode current) {
        return current.color == Color.black;
    }

    enum Color {
        red, black
    }

    class RBTNode {
        T element;
        RBTNode parent, left, right;
        // using the enum type to declare a variable to hold the node color
        Color color;

        // default constructor for creating the sentinel leaf
        public RBTNode() {
            // sentinel node is colored black by default
            color = Color.black;
        }

        /*
         * parameterized constructor for creating an internal node storing some given
         * key, both the left and right child references point to the sentinel leaf node
         */
        public RBTNode(T key) {
            element = key;
            left = right = sentinelLeaf;
        }

        public RBTNode(Color color, T value) {
            this.color = color;
            this.element = value;
        }

        RBTNode(T key, Color color, RBTNode parent, RBTNode left, RBTNode right) {
            this.element = key;
            this.color = color;

            if (parent == null && left == null && right == null) {
                parent = this;
                left = this;
                right = this;
            }

            this.parent = parent;
            this.left = left;
            this.right = right;
        }

    }
}
