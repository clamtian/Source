package TreeNode;

import TreeNode.Code_02_BinarySearchTree.Node;

/**
 * 实现红黑树
 *@author clam
 *
 */
public class Code_03_RedBlackTree <K extends Comparable<K>, V>{
	private static final byte RED = 1;
	private static final byte BLACK = 0; 
	
	private class Node{
		private byte color;
		private K key; // 存储的key
		private V value; // 存储的值
		private Node leftNode;  // 左节点
		private Node rightNode; // 右节点
		private Node parentNode; // 父节点
		
		public Node(K key, V value, Node leftNode, Node rightNode, byte color, Node parentNode) {
			super();
			this.key = key;
			this.value = value;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			this.color = color;
			this.parentNode = parentNode;
		}
		
		@Override
		public String toString(){
			return "{"
					+ "\"key\":" + this.key + ", "
					+ "\"value\":" + "\"" + this.value + "\"" + ", "
					+ "\"color\":" + this.color + ", "
					+ "\"leftNode\":" + this.leftNode + ","
					+ "\"rightNode\":" + this.rightNode + "}";
		}
	}
	
	public Node root;
	
	/**
	 * 插入操作
	 * @param key
	 * @param value
	 */
	public void put(K key, V value){
		Node node = new Node(key, value, null, null, RED, null);
		
		if(root == null){
			root = node;
			root.color = BLACK;
		}else{
			upsert(null, root, node);
		}
	}
	
	
	private void upsert(Node parent, Node current, Node node){
		if(current == null){
			if(node.key.compareTo(parent.key) > 0){
				parent.rightNode = node;
			}else{
				parent.leftNode = node;
			}
			node.parentNode = parent;
			upsertFix(node);
		}else{
			int result = node.key.compareTo(current.key);
			if(result == 0){
				current.value = node.value;
			}else if(result > 0){
				upsert(current, current.rightNode, node);
			}else{
				upsert(current, current.leftNode, node);
			}
		}
	}
	

	private void upsertFix(Node node){
		Node parent = node.parentNode;
		if(parent.color != BLACK){
			Node grandfather = parent.parentNode;
			if(parent == grandfather.leftNode){
				Node uncle = grandfather.rightNode;
				if(uncle != null && uncle.color == RED){
					uncleRedFix(node);
				}else{
					if(node.key.compareTo(parent.key) < 0){
						leftNodeFix(grandfather, parent);
					}else{
						leftRotation(parent);
						leftNodeFix(grandfather, node);
					}
				}
			}else{
				Node uncle = grandfather.leftNode;
				if(uncle != null && uncle.color == RED){
					uncleRedFix(node);
				}else{
					if(node.key.compareTo(parent.key) > 0){
						rightNodeFix(grandfather, parent);
					}else{
						rightRotation(parent);
						rightNodeFix(grandfather, node);
					}
				}
			}
		}
	}
	
	private void uncleRedFix(Node node){
		Node parent = node.parentNode;
		if(parent != null && parent.color == RED){
			parent.color = BLACK;
			Node grandfather = parent.parentNode;
			if(parent == grandfather.leftNode){
				grandfather.rightNode.color = BLACK;
			}else{
				grandfather.leftNode.color = BLACK;
			}
			if(grandfather != root){
				grandfather.color = RED;
				upsertFix(grandfather);
			}
		}
	}
	private void leftNodeFix(Node grandfather, Node parent){
		grandfather.color = RED;
		parent.color = BLACK;
		rightRotation(grandfather);
	}
	private void rightNodeFix(Node grandfather, Node parent){
		grandfather.color = RED;
		parent.color = BLACK;
		leftRotation(grandfather);
	}
	/**
	 * 左旋转
	 * @param h
	 */
	private void leftRotation(Node h){
		Node m = h.rightNode;
		h.rightNode = m.leftNode;
		if(m.leftNode != null){
			m.leftNode.parentNode = h;
		}
		
		m.parentNode = h.parentNode;
		if(m.parentNode == null){
			root = m;
		}else{
			if(h.key.compareTo(h.parentNode.key) > 0){
				h.parentNode.rightNode = m;
			}else{
				h.parentNode.leftNode = m;
			}
		}
		
		m.leftNode = h;
		h.parentNode = m;
	}
	
	/**
	 * 右旋转
	 * @param h
	 */
	private void rightRotation(Node m){
		Node h = m.leftNode;
		m.leftNode = h.rightNode;
		if(m.leftNode != null){
			m.leftNode.parentNode = m;
		}
		
		h.parentNode = m.parentNode;
		if(m.parentNode == null){
			root = h;
		}else{
			if(m.key.compareTo(m.parentNode.key) > 0){
				m.parentNode.rightNode = h;
			}else{
				m.parentNode.leftNode = h;
			}
		}
		
		m.parentNode = h;
		h.rightNode = m;
	}
	
	///////////////////////////////////////////
	public void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.rightNode, height + 1, "v", len);
		String val = to + head.value + "." + head.color + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.leftNode, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}
	///////////////////////////////
	
public static void main(String[] args) {
		
		Code_03_RedBlackTree<Integer, String> bst = new Code_03_RedBlackTree<Integer, String>();
		
		bst.put(100, "100");
		bst.printTree(bst.root);
		bst.put(50, "50");
		bst.printTree(bst.root);
		bst.put(150, "150");
		bst.printTree(bst.root);
		bst.put(20, "20");
		bst.printTree(bst.root);
		bst.put(85, "85");
		bst.printTree(bst.root);
		bst.put(10, "10");
		bst.printTree(bst.root);
		bst.put(15, "15");
		bst.printTree(bst.root);
		bst.put(75, "75");
		bst.printTree(bst.root);
		bst.put(95, "95");
		bst.printTree(bst.root);
		bst.put(65, "65");
		bst.printTree(bst.root);
		bst.put(76, "76");
		bst.printTree(bst.root);
		bst.put(60, "60");
		bst.printTree(bst.root);
		bst.put(66, "66");
		bst.printTree(bst.root);
		bst.put(61, "61");
		
		
		bst.printTree(bst.root);
	}
}
