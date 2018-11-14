package clf.learning.test;

/**
 * @author chenlongfei
 */
public class AVLTree {

	private AVLTreeNode root; // 根结点
	
	private AVLTreeNode insert(AVLTreeNode subTree, long insertValue) {
		if (subTree == null) {
			return new AVLTreeNode(insertValue, null, null); 
		}
		
		if (insertValue < subTree.value) { //插入左子树
			
			subTree.left = insert(subTree.left, insertValue);
			if (unbalanceTest(subTree)) {  //插入后造成失衡
				if (insertValue < subTree.left.value) { //LL型失衡
					leftLeftRotation(subTree); 
				} else { //LR型失衡
					leftRightRotation(subTree); 
				}
			}
			
			
		} else if (insertValue > subTree.value) { //插入右子树
			
			subTree.right = insert(subTree.right, insertValue);
			if (unbalanceTest(subTree)) {  //插入后造成失衡
				if (insertValue < subTree.right.value) { //RL型失衡
					rightLeftRotation(subTree); 
				} else { //RR型失衡
					rightRightRotation(subTree); 
				}
			}
			
		} else {
			throw new RuntimeException("duplicate value: " + insertValue);
		}
		
		return subTree;
	}
	
    private static int getDepth(AVLTreeNode treeRoot, int initDeep){
        int leftDeep = initDeep;
        int rightDeep = initDeep;
        if(treeRoot.left != null){ 
               leftDeep = getDepth(treeRoot.left, initDeep++);
        }
        if(treeRoot.right != null){ 
               rightDeep = getDepth(treeRoot.right, initDeep++);
        }
       
        return Math.max(leftDeep, rightDeep);
 }
    
    private boolean unbalanceTest(AVLTreeNode treeRoot) {
    	int leftHeight = getDepth(treeRoot.left, 1);
		int righHeight = getDepth(treeRoot.right, 1);
		int diff = Math.abs(leftHeight - righHeight);
		return diff > 1;
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
