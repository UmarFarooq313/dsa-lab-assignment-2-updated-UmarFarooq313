/***
 * @author write your name here!!!
 * 
 */
 
public class RedBlackTree<T extends Comparable<T>> {
	/*
	 * a single sentinel leaf node to be used by all nodes having leaves as their
	 * children the sentinel node does not need to store a parent reference
	 */
	final RBTNode sentinelLeaf = new RBTNode();
	// the root of out red black tree
	RBTNode root;

	public RedBlackTree() {
		root = null;
	}

	// TODO implement right->left rotation
	public RBTNode rightLeftRotate(RBTNode current) {
		return null;
	}

	// TODO implement right rotation
	public RBTNode rotateRight(RBTNode current) {
		return null;
	}

	// TODO implement the insert method iteratively as discussed in the lecture,
	// assume no duplicates allowed
	// DO NOT PROVIDE A RECURSIVE IMPLEMENTATION!!!!
	public boolean insert(T key) {
		return false;
	}

	// TODO implement the search method iteratively as discussed in the lecture,
	// DO NOT PROVIDE A RECURSIVE IMPLEMENTATION!!!!
	public RBTNode search(T key) {
		return null;
	}

	// TODO declare any other method you may need in your implementations.

	/*
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

	/*
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

	// is the tree empty?
	public boolean isEmpty() {
		return root == null;
	}

	// is the current node a left child?
	public boolean isLeftChild(RBTNode current) {
		if (current.parent.left == current) {
			return true;
		}
		return false;
	}

	// Does there exist a double red violation from the current node
	public boolean hasDoubleRed(RBTNode current) {
		if (isBlack(current)) {
			return false;
		} else if (current.parent == null) {
			// no parent, hence red-red sequence cannot occur
			return false;
		} else if (isBlack(current.parent)) {
			return false;
		}
		return true;
	}

	// Recolors the current node to red
	public void recolorRed(RBTNode current) {
		current.color = Color.red;
	}

	// Recolors the current node to red
	public void recolorBlack(RBTNode current) {
		current.color = Color.black;
	}

	// is the current node a black node?
	public boolean isBlack(RBTNode current) {
		return current.color == Color.black;
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

	}

	// an enumeration providing the possible color values
	enum Color {
		red, black
	}
}
