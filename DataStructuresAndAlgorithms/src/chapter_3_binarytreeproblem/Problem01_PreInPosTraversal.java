package chapter_3_binarytreeproblem;

import java.util.Stack;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem01_PreInPosTraversal.java
 * @Description: TODO
 * @CreatTime: 2016年9月9日 上午11:07:51
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem01_PreInPosTraversal {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: preOrderRecur
	 * @Description: 递归，先序遍历--根>左>右
	 * @param head根节点
	 * @author pen
	 * @CreatTime: 2016年9月9日 上午11:13:17
	 */
	private static void preOrderRecur(Node head) {
		if (head == null)// 递归跳出条件
			return;
		System.out.print(head.value + " ");// 打印根节点
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}

	/**
	 * @MethodName: inOrderRecur
	 * @Description: 递归，中序遍历--左>根>右
	 * @param head根节点
	 * @author pen
	 * @CreatTime: 2016年9月9日 上午11:19:26
	 */
	private static void inOrderRecur(Node head) {
		if (head == null)// 递归跳出条件
			return;
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	/**
	 * @MethodName: posOrderRecur
	 * @Description: 递归，后序遍历--左>右>根
	 * @param head根节点
	 * @author pen
	 * @CreatTime: 2016年9月9日 上午11:20:01
	 */
	private static void posOrderRecur(Node head) {
		if (head == null)// 递归跳出条件
			return;
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");
	}

	/**
	 * @MethodName: preOrderUnRecur
	 * @Description: 非递归，先序遍历
	 * @param head头节点
	 * @author pen
	 * @CreatTime: 2016年9月13日 下午5:09:22
	 */
	private static void preOrderUnRecur(Node head) {
		System.out.print("pre-order:");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(head);
			while (!stack.empty()) {
				head = stack.pop();// 先弹出根节点并打印
				System.out.print(head.value + " ");
				if (head.right != null)// 先压后出
					stack.push(head.right);
				if (head.left != null)// 后压先出
					stack.push(head.left);
			}
		}
		System.out.println();
	}

	/**
	 * @MethodName: inOrderUnRecur
	 * @Description: 非递归，中序遍历
	 * @param head头节点
	 * @author pen
	 * @CreatTime: 2016年9月13日 下午5:12:54
	 */
	private static void inOrderUnRecur(Node head) {
		System.out.print("in-order:");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			while (head != null || !stack.empty()) {
				if (head != null) {// 第一次肯定head不为null，本循环一直搜寻到底层
					stack.push(head);
					head = head.left;
				} else {// 搜寻到底层开始弹出
					head = stack.pop();
					System.out.print(head.value + " ");
					head = head.right;// 弹出左节点后看右节点情况压入/弹出，右节点为空的话说明已经到底了，再弹出一个之前压入的左节点(此处表现为根节点)
				}
			}
		}
		System.out.println();
	}

	/**
	 * @MethodName: posOrderUnRecur1
	 * @Description: 非递归，后序遍历
	 * @param head头节点
	 * @author pen
	 * @CreatTime: 2016年9月13日 下午7:58:29
	 */
	private static void posOrderUnRecur1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<Node>();// 按中左右的顺序压入栈中
			Stack<Node> s2 = new Stack<Node>();// 反转输出顺序
			s1.push(head);
			while (!s1.isEmpty()) {
				head = s1.pop();// s1弹出的顺序是中-右-左
				s2.push(head);// 依次向s2中压入中-右-左
				if (head.left != null)// 先压入左节点
					s1.push(head.left);
				if (head.right != null)// 再压入右节点
					s1.push(head.right);
			}
			while (!s2.empty()) {
				System.out.print(s2.pop().value + " ");// s2弹出的顺序与压入的顺序正好相反
			}
		}
		System.out.println();
	}

	public static void posOrderUnRecur2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderRecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println();
		System.out.print("pos-order: ");
		posOrderRecur(head);
		System.out.println();

		// unrecursive
		System.out.println("============unrecursive=============");
		preOrderUnRecur(head);
		inOrderUnRecur(head);
		posOrderUnRecur1(head);
		// posOrderUnRecur2(head);

	}

}
