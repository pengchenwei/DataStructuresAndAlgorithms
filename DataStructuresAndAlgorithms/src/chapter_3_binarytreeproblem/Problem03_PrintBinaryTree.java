package chapter_3_binarytreeproblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem03_PrintBinaryTree.java
 * @Description: 打印二叉树
 * @CreatTime: 2016年9月21日 上午10:15:12
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem03_PrintBinaryTree {
	public static class Node {
		public Node right;
		public Node left;
		public int value;

		public Node(int data) {
			this.value = data;
		}

	}

	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	private static void printInOrder(Node head, int height, String to, int len) {
		if (head == null)// 递归出口
			return;
		printInOrder(head.right, height + 1, "v", len);// 打印右子树
		// 打印中间节点
		String val = to + head.value + to;// 内容
		int lenM = val.length();// 调定距离
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);

		printInOrder(head.left, height + 1, "^", len);// 打印左子树

	}

	private static String getSpace(int size) {
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < size; i++) {
			buf.append(" ");
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(-222222222);
		head.right = new Node(3);
		head.left.left = new Node(Integer.MIN_VALUE);
		head.right.left = new Node(55555555);
		head.right.right = new Node(66);
		head.left.left.right = new Node(777);
		printTree(head);

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.left.left.right = new Node(7);
		printTree(head);

		head = new Node(1);
		head.left = new Node(1);
		head.right = new Node(1);
		head.left.left = new Node(1);
		head.right.left = new Node(1);
		head.right.right = new Node(1);
		head.left.left.right = new Node(1);
		printTree(head);

	}
}
