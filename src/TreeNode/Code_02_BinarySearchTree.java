package TreeNode;


/**
 * 实现二叉搜索树
 * 
 * @author clam
 *
 */
public class Code_02_BinarySearchTree {
	public Node root;

	public static class Node {
		public int val;
		public Node leftNode;
		public Node rightNode;

		public Node(int val) {
			this.val = val;
		}
	}

	/**
	 * 插入节点
	 * 
	 * @param val
	 */
	public void put(int val) {
		Node node = new Node(val);

		if (root == null) {
			root = node;
			return;
		}
		upsert(null, root, node);
	}

	private void upsert(Node parent, Node current, Node node) {
		if (current == null) {
			if (node.val > parent.val) {
				parent.rightNode = node;
			} else if (node.val < parent.val) {
				parent.leftNode = node;
			}
		} else {
			if (node.val > current.val) {
				upsert(current, current.rightNode, node);
			} else if (node.val < current.val) {
				upsert(current, current.leftNode, node);
			}
		}
	}

	/**
	 * 判断是否含有节点
	 * 
	 * @param val
	 * @return
	 */
	public boolean isContain(int val) {
		if (root == null) {
			return false;
		}
		return find(root, val);
	}

	private boolean find(Node current, int val) {
		if (current != null) {
			if (current.val == val) {
				return true;
			} else if (current.val > val) {
				return find(current.leftNode, val);
			} else {
				return find(current.rightNode, val);
			}
		}
		return false;
	}
	/**
	 * 刪除节点
	 * @param val
	 * @return
	 */
	public boolean delete(int val){
		if(root == null){
			return false;
		}
		return deleteNode(null, root, val);
	}
	private boolean deleteNode(Node parent, Node current, int val){
		if(current != null){
			if(current.val == val){
				if(current.leftNode == null && current.rightNode == null){
					if(current.val > parent.val){
						parent.rightNode = null;
					}else{
						parent.leftNode = null;
					}
				}else if(current.leftNode != null && current.rightNode != null){
					del2ChildrenNode(parent, current);
				}else{
					if(current.rightNode == null){
						current.val = current.leftNode.val;
						current.leftNode = null;
					}else{
						current.val = current.rightNode.val;
						current.rightNode = null;
					}
				}
				return true;
			}else if(current.val > val){
				return deleteNode(current, current.leftNode, val);
			}else{
				return deleteNode(current, current.rightNode, val);
			}
		}
		return false;
	}
	private void del2ChildrenNode(Node parent, Node current){
		Node node1 = current;
		Node node2 = current.rightNode;
		while(node2.leftNode != null){
			node1 = node2;
			node2 = node2.leftNode;
		}
		node1.leftNode = null;
		current.val = node2.val;
	}
	
	
}
