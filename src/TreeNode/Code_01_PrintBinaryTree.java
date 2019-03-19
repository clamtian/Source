package TreeNode;


import TreeNode.Code_02_BinarySearchTree.Node;

public class Code_01_PrintBinaryTree {

//	public static class Node {
//		public int val;
//		public Node leftNode;
//		public Node rightNode;
//
//		public Node(int data) {
//			this.val = data;
//		}
//	}

	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.rightNode, height + 1, "v", len);
		String val = to + head.val + to;
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
		Code_02_BinarySearchTree b = new Code_02_BinarySearchTree();
		b.put(10);
		b.put(-5);
		printTree(b.root);
		b.put(-8);
		printTree(b.root);
		b.put(4);
		printTree(b.root);
		b.put(81);
		printTree(b.root);
		b.put(7);
		printTree(b.root);
		b.put(93);
		printTree(b.root);
		b.put(7);
		printTree(b.root);
		b.put(-1);
		printTree(b.root);
		b.delete(93);
		printTree(b.root);
		b.delete(-5);
		printTree(b.root);
		b.delete(4);
		printTree(b.root);
	}

}
