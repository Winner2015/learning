package clf.learning.test;

/**
 * @author chenlongfei
 */
public class AVLTree {

	private AVLTreeNode root; // 根结点
	
	/**
	 * 插入操作的入口
	 * @author chenlongfei
	 * @param insertValue
	 */
	public void insert(long insertValue) {
		root = insert(root, insertValue);
	}

	/**
	 * 插入的地递归实现
	 * @author chenlongfei
	 * @param subTree
	 * @param insertValue
	 * @return
	 */
	private AVLTreeNode insert(AVLTreeNode subTree, long insertValue) {
		if (subTree == null) {
			return new AVLTreeNode(insertValue, null, null);
		}

		if (insertValue < subTree.value) { // 插入左子树

			subTree.left = insert(subTree.left, insertValue);
			if (unbalanceTest(subTree)) { // 插入后造成失衡
				if (insertValue < subTree.left.value) { // LL型失衡
					subTree = leftLeftRotation(subTree);
				} else { // LR型失衡
					subTree = leftRightRotation(subTree);
				}
			}

		} else if (insertValue > subTree.value) { // 插入右子树

			subTree.right = insert(subTree.right, insertValue);
			if (unbalanceTest(subTree)) { // 插入后造成失衡
				if (insertValue < subTree.right.value) { // RL型失衡
					subTree = rightLeftRotation(subTree);
				} else { // RR型失衡
					subTree = rightRightRotation(subTree);
				}
			}

		} else {
			throw new RuntimeException("duplicate value: " + insertValue);
		}

		return subTree;
	}

	/**
	 * RL型旋转
	 * @author chenlongfei
	 * @param k1 子树根节点
	 * @return
	 */
	private AVLTreeNode rightLeftRotation(AVLTreeNode k1) {
		k1.right = leftLeftRotation(k1.right);

		return rightRightRotation(k1);
	}

	/**
	 * RR型旋转
	 * @author chenlongfei
	 * @param k1 k1 子树根节点
	 * @return
	 */
	private AVLTreeNode rightRightRotation(AVLTreeNode k1) {
		AVLTreeNode k2;

		k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;

		return k2;
	}

	/**
	 * LR型旋转
	 * @author chenlongfei
	 * @param k3
	 * @return
	 */
	private AVLTreeNode leftRightRotation(AVLTreeNode k3) {
		k3.left = rightRightRotation(k3.left);

		return leftLeftRotation(k3);
	}

	/**
	 * LL型旋转
	 * @author chenlongfei
	 * @param k2
	 * @return
	 */
	private AVLTreeNode leftLeftRotation(AVLTreeNode k2) {
		AVLTreeNode k1;

		k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;

		return k1;
	}

	/**
	 * 获取树的深度
	 * @author chenlongfei
	 * @param treeRoot 根节点
	 * @param initDeep 初始深度
	 * @return
	 */
	private static int getDepth(AVLTreeNode treeRoot, int initDeep) {
		int leftDeep = initDeep;
		int rightDeep = initDeep;
		if (treeRoot.left != null) {
			leftDeep = getDepth(treeRoot.left, initDeep++);
		}
		if (treeRoot.right != null) {
			rightDeep = getDepth(treeRoot.right, initDeep++);
		}

		return Math.max(leftDeep, rightDeep);
	}

	/**
	 * 判断是否失衡
	 * @author chenlongfei
	 * @param treeRoot
	 * @return
	 */
	private boolean unbalanceTest(AVLTreeNode treeRoot) {
		int leftHeight = getDepth(treeRoot.left, 1);
		int righHeight = getDepth(treeRoot.right, 1);
		int diff = Math.abs(leftHeight - righHeight);
		return diff > 1;
	}
	
	
	
	
	private AVLTreeNode remove(AVLTreeNode tree, AVLTreeNode z) {
	    // 根为空 或者 没有要删除的节点，直接返回null。
	    if (tree==null || z==null)
	        return null;

	    int cmp = z.key.compareTo(tree.key);
	    if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
	        tree.left = remove(tree.left, z);
	        // 删除节点后，若AVL树失去平衡，则进行相应的调节。
	        if (height(tree.right) - height(tree.left) == 2) {
	            AVLTreeNode<T> r =  tree.right;
	            if (height(r.left) > height(r.right))
	                tree = rightLeftRotation(tree);
	            else
	                tree = rightRightRotation(tree);
	        }
	    } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
	        tree.right = remove(tree.right, z);
	        // 删除节点后，若AVL树失去平衡，则进行相应的调节。
	        if (height(tree.left) - height(tree.right) == 2) {
	            AVLTreeNode<T> l =  tree.left;
	            if (height(l.right) > height(l.left))
	                tree = leftRightRotation(tree);
	            else
	                tree = leftLeftRotation(tree);
	        }
	    } else {    // tree是对应要删除的节点。
	        // tree的左右孩子都非空
	        if ((tree.left!=null) && (tree.right!=null)) {
	            if (height(tree.left) > height(tree.right)) {
	                // 如果tree的左子树比右子树高；
	                // 则(01)找出tree的左子树中的最大节点
	                //   (02)将该最大节点的值赋值给tree。
	                //   (03)删除该最大节点。
	                // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
	                // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
	                AVLTreeNode<T> max = maximum(tree.left);
	                tree.key = max.key;
	                tree.left = remove(tree.left, max);
	            } else {
	                // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
	                // 则(01)找出tree的右子树中的最小节点
	                //   (02)将该最小节点的值赋值给tree。
	                //   (03)删除该最小节点。
	                // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
	                // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
	                AVLTreeNode<T> min = maximum(tree.right);
	                tree.key = min.key;
	                tree.right = remove(tree.right, min);
	            }
	        } else {
	            AVLTreeNode<T> tmp = tree;
	            tree = (tree.left!=null) ? tree.left : tree.right;
	            tmp = null;
	        }
	    }

	    return tree;
	}

	public void remove(T key) {
	    AVLTreeNode<T> z; 

	    if ((z = search(mRoot, key)) != null)
	        mRoot = remove(mRoot, z);
	}
	
	
	
	
	
	
	
	
	

	// AVL树的节点
	class AVLTreeNode {
		long value; // 节点存储的数值
		AVLTreeNode left; // 左孩子
		AVLTreeNode right; // 右孩子

		public AVLTreeNode(long value, AVLTreeNode left, AVLTreeNode right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		public long getValue() {
			return this.value;
		}

		public void setValue(long value) {
			this.value = value;
		}

		public AVLTreeNode getLeft() {
			return this.left;
		}

		public void setLeft(AVLTreeNode left) {
			this.left = left;
		}

		public AVLTreeNode getRight() {
			return this.right;
		}

		public void setRight(AVLTreeNode right) {
			this.right = right;
		}
	}
}
