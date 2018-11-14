package clf.learning.test;

/**
 * @author chenlongfei
 */
public class AVLTree {

	private AVLTreeNode root; // 根结点
	
	private void insert(AVLTreeNode tree, long insertValue) {
		
	}

	
	public void insert(long insertValue) {
		
		if (root == null) {
			root = new AVLTreeNode(insertValue, null, null);
			return;
		}
		
		if (insertValue < root.getValue()) {
			
		}
		

       if (insertValue < tree.value) {    // 应该将key插入到"tree的左子树"的情况
        tree.left = insert(tree.left, insertValue);
        // 插入节点后，若AVL树失去平衡，则进行相应的调节。
        if (height(tree.left) - height(tree.right) == 2) {
            if (key.compareTo(tree.left.key) < 0)
                tree = leftLeftRotation(tree);
            else
                tree = leftRightRotation(tree);
        }
    } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
        tree.right = insert(tree.right, key);
        // 插入节点后，若AVL树失去平衡，则进行相应的调节。
        if (height(tree.right) - height(tree.left) == 2) {
            if (key.compareTo(tree.right.key) > 0)
                tree = rightRightRotation(tree);
            else
                tree = rightLeftRotation(tree);
        }
    } else {    // cmp==0
        System.out.println("添加失败：不允许添加相同的节点！");
    }

	    tree.height = max( height(tree.left), height(tree.right)) + 1;

	    return tree;
	}

	public void insert(T key) {
	    mRoot = insert(mRoot, key);
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
