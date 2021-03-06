package TreeNode;


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
	 * 删除节点
	 * @param node
	 */
	public boolean delete(K key){
		if(key != null){
			if(root != null){
				return deleteNode(key, root, null);
			}
		}
		return false;
	}
	
	private boolean deleteNode(K key, Node current, Node parent){
		if(current != null){
			if(current.key.compareTo(key) > 0){
				return deleteNode(key, current.leftNode, current);
			}else if(current.key.compareTo(key) < 0){
				return deleteNode(key, current.rightNode, current);
			}else{
				if(current.leftNode != null && current.rightNode != null){
					del2ChildrenNode(current);
				}else if(current.leftNode == null && current.rightNode == null){
					deleteLeafFix(current);
					if(current.key.compareTo(parent.key) > 0){
						parent.rightNode = null;
					}else{
						parent.leftNode = null;
					}
				}else{
					delOneChildNode(current);
				}
				return true;
			}
		}
		return false;
	}
	
	private void delOneChildNode(Node delNode){
		Node replaceNode = (null == delNode.leftNode) ? delNode.rightNode : delNode.leftNode;
		deltetLeafNode(delNode, replaceNode);
	}
	
	private void del2ChildrenNode(Node target){
		Node replaceNode = successor(target);
		if((null == replaceNode.rightNode) && (null == replaceNode.leftNode)){
			deltetLeafNode(target, replaceNode);
		}else{
			target.key = replaceNode.key;
			target.value = replaceNode.value;
			delOneChildNode(replaceNode);
		}
	}
	
	private void deltetLeafNode(Node target, Node replaceNode){
		target.key = replaceNode.key;
		target.value = replaceNode.value;
		deleteLeafFix(replaceNode);
		if(replaceNode == replaceNode.parentNode.rightNode){
			replaceNode.parentNode.rightNode = null;
		}else{
			replaceNode.parentNode.leftNode = null;
		}
	}
	
	//找后继结点。即查找"红黑树中数据值大于该结点的最小结点"
		private Node successor(Node node) {
	        if (node == null){
	        	return null;
	        }
	        if (null != node.rightNode) { // 获取 后继节点
	        	Node p = node.rightNode;
	            while (null != p.leftNode){
	            	 p = p.leftNode;
	            }
	            return p;
	        } else {
	        	Node p = node.parentNode;
	        	Node ch = node;
	            while (p != null && ch == p.rightNode) {
	                ch = p;
	                p = p.parentNode;
	            }
	            return p;
	        }
	    }
	
	
	private void deleteLeafFix(Node deleteNode){
		while(deleteNode != root && deleteNode.color == BLACK){
			Node parent = deleteNode.parentNode;
			Node brother = getBrother(deleteNode);
			if(deleteNode.key.compareTo(parent.key) > 0){//删除的是右叶子节点
				if(RED == brother.color){ //case5：该兄弟节点是红色的
					brother.color = BLACK;
					brother.rightNode.color = RED;
					rightRotation(parent);
					break;
				}else{
					if(brother.rightNode == null && brother.leftNode == null){
						//兄弟节点 是黑色 且没有子节点
						brother.color = RED;
						deleteNode = parent;
					}else{
						if(brother.leftNode != null && brother.leftNode.color == RED){
							brother.color = parent.color;
							parent.color = BLACK;
							rightRotation(parent);
							break;
						}else{
							brother.rightNode.color = BLACK;
							brother.color = RED;
							leftRotation(brother);
						}
					}
				}
			}else{
				if(brother.color == RED){
					brother.color = BLACK;
					brother.leftNode.color = RED;
					leftRotation(parent);
					break;
				}else{
					if((null == brother.leftNode) && (null == brother.rightNode)){ // case4: 兄弟节点是黑色的，且没有子节点
						brother.color = RED; // 将兄弟节点设为红色，将父节点设为当前节点递归， 直到根节点，或遇到红色节点，
						deleteNode = parent;
					}else{
						if((null != brother.rightNode) && (RED == brother.rightNode.color)){ // case1 : 兄弟节点是黑色的，且有一个右节点（可以断定 右节点是红色的）
							// case3 : 兄弟节点是黑色的，且有两个节点（可以断定 左右节点都是红色的） 这个和情况 1 是一样的
							brother.color = parent.color;
							parent.color = BLACK;
							brother.rightNode.color = BLACK;
							leftRotation(parent);
							break;
						}else{ // case2: 兄弟节点是黑色的，且有一个左节点（可以断定 左节点是红色的）
							brother.leftNode.color = BLACK;
							brother.color = RED;
							rightRotation(brother);
						}
					}
				}
			}
		}
		deleteNode.color = BLACK;
	}
	private Node getBrother(Node node){
		if(null == node){
			return null;
		}
		Node parent = node.parentNode;
		if(null == parent){
			return null;
		}
		if(node.key.compareTo(parent.key) > 0){
			return parent.leftNode;
		}else{
			return parent.rightNode;
		}
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
		
		
		// 当前节点是左节点 的 5中情况
		bst.delete(15); // 1. 兄弟节点是黑色的，且有一个右节点（可以断定 右节点是红色的）

		bst.printTree(bst.root);
		// 2. 兄弟节点是黑色的，且有一个左节点（可以断定 左节点是红色的
		bst.put(140, "v140");

		bst.printTree(bst.root);
		
		bst.delete(95); 

		bst.printTree(bst.root);
		// 4. 兄弟节点是黑色的，且没有子节点
		bst.delete(66); 

		bst.printTree(bst.root);
		//5. 如果该兄弟节点是红色的，那么根据红黑树的特性可以得出它的一定有两个黑色的子节点
		bst.delete(95);
		bst.printTree(bst.root);
		bst.delete(15);
		bst.printTree(bst.root);
	}
}
